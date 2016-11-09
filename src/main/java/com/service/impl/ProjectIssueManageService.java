package com.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bean.Attachment;
import com.bean.Implementation;
import com.bean.InstanceContext;
import com.bean.Post;
import com.bean.ProjectIssueManage;
import com.bean.ProjectState;
import com.bean.User;
import com.bean.WorkItem;
import com.bean.Wtdata;
import com.mapper.InstanceContextMapper;
import com.mapper.PostMapper;
import com.mapper.ProjectIssueManageMapper;
import com.mapper.UserMapper;
import com.mapper.WorkItemMapper;
import com.mapper.WtdataMapper;
import com.qq.weixin.mp.aes.WXService;
import com.qq.weixin.mp.aes.bean.Articles;
import com.qq.weixin.mp.aes.bean.NewsMessage;
import com.service.IProjectIssueManageService;
import com.util.StringUtil;
import com.util.bean.Common;
import com.util.bean.Result;

@Service
public class ProjectIssueManageService implements IProjectIssueManageService {

	@Autowired
	private WorkItemMapper workItemMapper;
	@Autowired
	private InstanceContextMapper instanceMapper;
	@Autowired
	private PostMapper postMapper;
	@Autowired
	private ProjectIssueManageMapper projectMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private WtdataMapper wtdataMapper;
	//问题提出
	@Override
	public Result<ProjectIssueManage> insert(ProjectIssueManage project, User user) {
		Result<ProjectIssueManage> res = new Result<ProjectIssueManage>();
		Date now = new Date();
		String ymd = new SimpleDateFormat("yyyyMMdd").format(now);
		ymd = ymd.substring(2, 4);
		try {
			// 存储问题明细
			String id = instanceMapper.getID();
			project.setObjectid(id);
			project.setCreateby(user.getObjectid());
			project.setCreatetime(now);
			project.setState(ProjectState.START);
			project.setDeleteflag(0);
			//存储文件
			String filepaths = project.getWttp();
			if(!StringUtil.isNullOrEmpty(filepaths)){
				String[] files = filepaths.split(";");
				List<Attachment> wttps = new ArrayList<Attachment>();
				for(int i = 0 ; i < files.length; i++){
					Attachment a = new Attachment();
					String aid = instanceMapper.getID();
					a.setObjectid(aid);
					a.setBizschemacode(ProjectState.schemaCode);
					a.setCreateby(user.getObjectid());
					a.setCreatetime(now);
					a.setDatafield("wttp");
					a.setDownloadurl(files[i]);
					a.setParentobjectid(id);
					a.setStoragepath(files[i]);
					wttps.add(a);
				}
				project.setWttps(wttps);
			}
			projectMapper.insert(project);
			// 流程实例生成instancecontext
			String instanceid = instanceMapper.getID();
			InstanceContext instanceContext = createInstance(project, user, now, ymd, id, instanceid, 0);
			instanceMapper.insertSelective(instanceContext);
			// 流程节点生成workitem(问题提出)
			WorkItem workItem = createWorkItem(user.getObjectid(), user.getObjectid(), user.getObjectid(), now, id,
					instanceid, 0, ProjectState.DOWN, ProjectState.WTTC, Common.wttc, ProjectState.schemaCode);
			workItemMapper.insertSelective(workItem);
			// 流程节点生成workitem(问题判定)
			List<Post> posts = postMapper.getPostByCode(ProjectState.WTPD);
			for (Post p : posts) {
				WorkItem wi = createWorkItem(user.getObjectid(), p.getOwner(), null, now, id, instanceid, 0, ProjectState.START, ProjectState.WTPD,
						Common.wtsl, "XMWTGL");
				workItemMapper.insertSelective(wi);
				String zh = p.getUser().getZh();
				String fqrxm = user.getXm();
				String xmbh = project.getXmbh();
				String cpmc = project.getCpmc();
				String seqno = instanceContext.getSequnceno();
				String workitemid = wi.getObjectid();
				// 给流程接收人发微信
				NewsMessage nm = new NewsMessage();
				Articles articles = new Articles();
				articles.title = "您有新的待判定问题";
				articles.description = "[项目编号:" + xmbh + "][产品名称:" + cpmc + "][流水号:"
						+ seqno + "][发起人:" + fqrxm + "]";
				articles.url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx46be78f8feff44a2&redirect_uri=http://bpm.mesnac.com:8087/Feedback/wtsl/wtsl.do?objectid="
						+ id + "&workitemid=" + workitemid + "&response_type=code&scope=snsapi_base&state=DefaultEngine#wechat_redirect";
				nm.news.articles.add(articles);
				nm.touser = zh;
				nm.msgtype = "news";
				nm.agentid = 27;
				WXService.sendNewsMessage(nm);
			}
			res.setFlag(true);
			res.setData(project);
			// System.out.println(id);
		} catch (Exception e) {
			res.setFlag(false);
			res.setMessage(e.getMessage());
		}
		return res;
	}

	//问题判定
	@Override
	public Result<ProjectIssueManage> wtpd(ProjectIssueManage project, User user) {
		Result<ProjectIssueManage> res = new Result<>();
		try{
			Date now = new Date();
			//查询当前【问题判定】的活动节点，如果只要一个人处理，则将所有的当前活动节点通过,否则只结束当前处理人的任务
			WorkItem active = new WorkItem();
			active.setActivitycode(ProjectState.WTPD);
			active.setReceiver(user.getObjectid());
			active.setState(ProjectState.START);
			active.setBizobjectid(project.getObjectid());
			List<WorkItem> wis = workItemMapper.selectBySelective(active);
			if(wis != null && wis.size() > 0){
				WorkItem wtpd = new WorkItem();
				wtpd.setActivitycode(ProjectState.WTPD);
				wtpd.setBizobjectid(project.getObjectid());
				wtpd.setFinisher(user.getObjectid());
				wtpd.setFinishtime(now);
				wtpd.setState(ProjectState.DOWN);
				wtpd.setObjectid(user.getWorkItemId());
				if(wis.get(0).getEitherorall() == 0){
					workItemMapper.updateByBizCode(wtpd);
				}else{
					wtpd.setObjectid(user.getWorkItemId());
					workItemMapper.updateByPrimaryKeySelective(wtpd);
				}
				
			}
			//更新问题内容
			projectMapper.updateByPrimaryKeySelective(project);
			//获取问题全部内容
			project = projectMapper.selectByPrimaryKey(project.getObjectid());
			InstanceContext instance = instanceMapper.selectByBizobjectid(project.getObjectid());
			//开始问题分析节点
			WorkItem wtfx = createWorkItem(project.getCreateby(), project.getZrr(), null, now, project.getObjectid(), instance.getObjectid(), 1, ProjectState.START, ProjectState.WTFX, Common.wtfx, ProjectState.schemaCode);
			workItemMapper.insertSelective(wtfx);
			User uwtfx = userMapper.selectByPrimaryKey(project.getZrr());
			User fqr = userMapper.selectByPrimaryKey(project.getCreateby());
			//给问题分析接收人发微信
			NewsMessage nm = new NewsMessage();
			Articles articles = new Articles();
			articles.title = "您有新的待分析问题";
			articles.description = "[项目编号:" + project.getXmbh() + "][产品名称:" + project.getCpmc() + "][流水号:"
					+ instance.getSequnceno() + "][发起人:" + fqr.getXm() + "]";
			articles.url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx46be78f8feff44a2&redirect_uri=http://bpm.mesnac.com:8087/Feedback/wtfx/wtfx.do?objectid="
					+ project.getObjectid() + "&workitemid=" + wtfx.getObjectid() + "&response_type=code&scope=snsapi_base&state=DefaultEngine#wechat_redirect";
			nm.news.articles.add(articles);
			nm.touser = uwtfx.getZh();
			nm.msgtype = "news";
			nm.agentid = 27;
			WXService.sendNewsMessage(nm);
		}catch(Exception e){
			res.setFlag(false);
			res.setMessage(e.getMessage());
		}
		return res;
	}
	
	//问题分析
	@Override
	public Result<ProjectIssueManage> wtfx(ProjectIssueManage project, User user) {
		Result<ProjectIssueManage> res = new Result<>();
		try{
			Date now = new Date();
			//查询当前【问题分析】的活动节点，如果只要一个人处理，则将所有的当前活动节点通过,否则只结束当前处理人的任务
			WorkItem active = new WorkItem();
			active.setActivitycode(ProjectState.WTFX);
			active.setReceiver(user.getObjectid());
			active.setState(ProjectState.START);
			active.setBizobjectid(project.getObjectid());
			List<WorkItem> wis = workItemMapper.selectBySelective(active);
			if(wis != null && wis.size() > 0){
				WorkItem wtpd = new WorkItem();
				wtpd.setActivitycode(ProjectState.WTFX);
				wtpd.setBizobjectid(project.getObjectid());
				wtpd.setFinisher(user.getObjectid());
				wtpd.setFinishtime(now);
				wtpd.setState(ProjectState.DOWN);
				wtpd.setObjectid(user.getWorkItemId());
				if(wis.get(0).getEitherorall() == 0){
					workItemMapper.updateByBizCode(wtpd);
				}else{
					wtpd.setObjectid(user.getWorkItemId());
					workItemMapper.updateByPrimaryKeySelective(wtpd);
				}
			}
			//更新问题内容
			projectMapper.updateByPrimaryKeySelective(project);
			//获取问题全部内容
			project = projectMapper.selectByPrimaryKey(project.getObjectid());
			InstanceContext instance = instanceMapper.selectByBizobjectid(project.getObjectid());
			//开始问题解决节点
			WorkItem wtjj = createWorkItem(project.getCreateby(), project.getFazxr(), null, now, project.getObjectid(), instance.getObjectid(), 1, ProjectState.START, ProjectState.WTJJ, Common.wtjj, ProjectState.schemaCode);
			workItemMapper.insertSelective(wtjj);
			User uwtjj = userMapper.selectByPrimaryKey(project.getFazxr());
			User fqr = userMapper.selectByPrimaryKey(project.getCreateby());
			//给问题解决接收人发微信
			NewsMessage nm = new NewsMessage();
			Articles articles = new Articles();
			articles.title = "您有新的待解决问题";
			articles.description = "[项目编号:" + project.getXmbh() + "][产品名称:" + project.getCpmc() + "][流水号:"
					+ instance.getSequnceno() + "][发起人:" + fqr.getXm() + "]";
			articles.url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx46be78f8feff44a2&redirect_uri=http://bpm.mesnac.com:8087/Feedback/wtjj/wtjj.do?objectid="
					+ project.getObjectid() + "&workitemid=" + wtjj.getObjectid() + "&response_type=code&scope=snsapi_base&state=DefaultEngine#wechat_redirect";
			nm.news.articles.add(articles);
			nm.touser = uwtjj.getZh();
			nm.msgtype = "news";
			nm.agentid = 27;
			WXService.sendNewsMessage(nm);
		}catch(Exception e){
			res.setFlag(false);
			res.setMessage(e.getMessage());
		}
		return res;
	}


	//问题分派
	//当前节点可以直接解决问题，也可以再分派任务给其他人一起解决
	//直接解决问题则下一节点为问题关闭，否则下一节点为问题解决
	@Override
	public Result<ProjectIssueManage> wtfp(ProjectIssueManage project, User user) {
		Result<ProjectIssueManage> res = new Result<>();
		Date now = new Date();
		try{
			InstanceContext instance = instanceMapper.selectByBizobjectid(project.getObjectid());
			User fqr = userMapper.selectByPrimaryKey(project.getCreateby());
			//去除无效的解决措施
			Iterator<Implementation> impls = project.getImplementations().iterator(); 
			Implementation imp1 = impls.next();
			imp1.setCreatetime(now);
			imp1.setCreateby(user.getObjectid());
			imp1.setDeteleteflag(0);
			imp1.setParentobjectid(project.getObjectid());
			imp1.setState(ProjectState.DOING);
			imp1.setJjcszrr(user.getObjectid());
			while(impls.hasNext()){
				Implementation imp = impls.next();
				if(StringUtil.isNullOrEmpty(imp.getJjcs())){
					impls.remove();
				}else{
					imp1.setCreatetime(now);
					imp1.setCreateby(user.getObjectid());
					imp1.setDeteleteflag(0);
					imp1.setParentobjectid(project.getObjectid());
					imp1.setState(ProjectState.DOING);
				}
			}
			//插入解决措施
		}catch(Exception e){
			res.setFlag(false);
			res.setMessage(e.getMessage());
		}
		
		return null;
	}
	
	//问题解决
	@Override
	public Result<ProjectIssueManage> wtjj(ProjectIssueManage project, User user) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Result<ProjectIssueManage> wtgb(ProjectIssueManage project, User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result<ProjectIssueManage> wtjs(ProjectIssueManage project, User user) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Result<List<ProjectIssueManage>> getBySelective(ProjectIssueManage project) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result<ProjectIssueManage> updateByObjectId(ProjectIssueManage project) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

	private InstanceContext createInstance(ProjectIssueManage project, User user, Date now, String ymd, String id,
			String instanceid, int state) {
		InstanceContext instanceContext = new InstanceContext();
		// 流水号生成
		Integer order = instanceMapper.getOrderNumber(ProjectState.schemaCode, ymd);
		if (order == null) {
			order = 1;
		} else {
			order++;
		}
		instanceMapper.updateOrderNumber(ProjectState.schemaCode, ymd, order);
		String orders = order + "";
		while (orders.length() < 4) {
			orders = "0" + orders;
		}
		String cpm = project.getXmbh();
		if (cpm != null && cpm.length() >= 8) {
			cpm = cpm.substring(6, 8);
		} else {
			cpm = "00";
		}
		orders = cpm + ymd + orders;
		instanceContext.setBizobjectid(id);
		instanceContext.setObjectid(instanceid);
		instanceContext.setCreatedtime(now);
		instanceContext.setOrgunit(user.getSzbm());
		instanceContext.setOriginator(user.getObjectid());
		instanceContext.setSequnceno(orders);
		instanceContext.setStarttime(now);
		instanceContext.setState(state);
		return instanceContext;
	}

	private WorkItem createWorkItem(String originator, String receiver, String finisher, Date now, String id,
			String instanceid, int eithororall, int state, String activitycode, String activityname, String schema) {
		String wiObjectId = instanceMapper.getID();
		WorkItem workItem = new WorkItem();
		workItem.setActivitycode(activitycode);
		workItem.setActivityname(activityname);
		workItem.setBizobjectid(id);
		workItem.setBizobjectschemacode(schema);
		workItem.setCreatetime(now);
		workItem.setEitherorall(eithororall);
		workItem.setFinisher(finisher);
		workItem.setFinishtime(now);
		workItem.setInstanceid(instanceid);
		workItem.setObjectid(wiObjectId);
		workItem.setOriginator(originator);
		workItem.setReceiver(receiver);
		workItem.setReceivetime(now);
		workItem.setStarttime(now);
		workItem.setState(state);
		return workItem;
	}

	@Override
	public Result<ProjectIssueManage> selectByObjectId(String objectid) {
		Result<ProjectIssueManage> res = new Result<>();
		if(StringUtil.isNullOrEmpty(objectid)){
			res.setFlag(false);
			res.setMessage("输入不能为空");
			return res;
		}
		try{
			ProjectIssueManage project = projectMapper.selectByPrimaryKey(objectid);
			
			if(project == null){
				res.setFlag(false);
				res.setMessage("查找的问题不存在");
			}else{
				if(!StringUtil.isNullOrEmpty(project.getWtlb())){
					Wtdata wtlb = wtdataMapper.selectByFlagValue("问题类别", project.getWtlb());
					project.setWtlb(wtlb.getText());
				}
				if(!StringUtil.isNullOrEmpty(project.getZrlb())){
					Wtdata zrlb = wtdataMapper.selectByFlagValue("责任类别", project.getZrlb());
					project.setZrlb(zrlb.getText());
				}
				res.setFlag(true);
				res.setData(project);
			}
		}catch(Exception e){
			e.printStackTrace();
			res.setFlag(false);
			res.setMessage(e.getMessage());
		}
		return res;
	}

	
}
