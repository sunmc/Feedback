package com.service.impl;

import java.net.URLEncoder;
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
import com.bean.ProjectListItem;
import com.bean.ProjectState;
import com.bean.User;
import com.bean.WorkItem;
import com.bean.Wtdata;
import com.mapper.ImplementationMapper;
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
import com.util.PathUtil;
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
	@Autowired
	private ImplementationMapper implementationMapper;
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
			project.setGzjd(0);
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
			// 流程实例生成instancecontext
			String instanceid = instanceMapper.getID();
			InstanceContext instanceContext = createInstance(project, user, now, ymd, id, instanceid, 0);
			instanceMapper.insertSelective(instanceContext);
			project.setLsh(instanceContext.getSequnceno());
			projectMapper.insert(project);
			// 流程节点生成workitem(问题提出)
			WorkItem workItem = createWorkItem(user.getObjectid(), user.getObjectid(), user.getObjectid(), now, id,
					instanceid, 0, ProjectState.DOWN, ProjectState.WTTC, Common.wttc, ProjectState.WTTC);
			workItemMapper.insertSelective(workItem);
			// 流程节点生成workitem(问题判定)
			List<Post> posts = postMapper.getPostByCode(ProjectState.WTPD);
			for (Post p : posts) {
				WorkItem wi = createWorkItem(user.getObjectid(), p.getOwner(), null, now, id, instanceid, 0, ProjectState.START, ProjectState.WTPD,
						Common.wtpd, ProjectState.WTPD);
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
				String url = "http://bpm.mesnac.com:8087/Feedback/wtpd/wtpd.do?objectid="
						+ id + "&workitemid=" + workitemid ;
				articles.url = PathUtil.getWXUrl(url);
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
			WorkItem wtfx = createWorkItem(project.getCreateby(), project.getZrr(), null, now, project.getObjectid(), instance.getObjectid(), 1, ProjectState.START, ProjectState.WTFX, Common.wtfx, ProjectState.WTFX);
			workItemMapper.insertSelective(wtfx);
			User uwtfx = userMapper.selectByPrimaryKey(project.getZrr());
			User fqr = userMapper.selectByPrimaryKey(project.getCreateby());
			//给问题分析接收人发微信
			NewsMessage nm = new NewsMessage();
			Articles articles = new Articles();
			articles.title = "您有新的待分析问题";
			articles.description = "[项目编号:" + project.getXmbh() + "][产品名称:" + project.getCpmc() + "][流水号:"
					+ instance.getSequnceno() + "][发起人:" + fqr.getXm() + "]";
			String url = "http://bpm.mesnac.com:8087/Feedback/wtfx/wtfx.do?objectid="
					+ project.getObjectid() + "&workitemid=" + wtfx.getObjectid();
			articles.url = PathUtil.getWXUrl(url);
			nm.news.articles.add(articles);
			nm.touser = uwtfx.getZh();
			nm.msgtype = "news";
			nm.agentid = 27;
			WXService.sendNewsMessage(nm);
			res.setFlag(true);
			res.setData(project);
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
			//开始问题整改(可分派)节点
			WorkItem wtzg = createWorkItem(project.getCreateby(), project.getFazxr(), null, now, project.getObjectid(), instance.getObjectid(), 1, ProjectState.START, ProjectState.WTZG, Common.wtqr, ProjectState.WTFP);
			workItemMapper.insertSelective(wtzg);
			User uwtjj = userMapper.selectByPrimaryKey(project.getFazxr());
			User fqr = userMapper.selectByPrimaryKey(project.getCreateby());
			//给问题解决接收人发微信
			NewsMessage nm = new NewsMessage();
			Articles articles = new Articles();
			articles.title = "您有新的待整改问题";
			articles.description = "[项目编号:" + project.getXmbh() + "][产品名称:" + project.getCpmc() + "][流水号:"
					+ instance.getSequnceno() + "][发起人:" + fqr.getXm() + "]";
			String url = "http://bpm.mesnac.com:8087/Feedback/wtzg/wtzg.do?objectid="
					+ project.getObjectid() + "&workitemid=" + wtzg.getObjectid();
			articles.url = PathUtil.getWXUrl(url);
			nm.news.articles.add(articles);
			nm.touser = uwtjj.getZh();
			nm.msgtype = "news";
			nm.agentid = 27;
			WXService.sendNewsMessage(nm);
			res.setFlag(true);
			res.setData(project);
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
			//去除无效的解决措施
			Iterator<Implementation> impls = project.getImplementations().iterator(); 
			Implementation imp1 = impls.next();
			imp1.setCreatetime(now);
			imp1.setCreateby(user.getObjectid());
			imp1.setDeleteflag(0);
			imp1.setParentobjectid(project.getObjectid());
			imp1.setState(ProjectState.DOING);
			imp1.setJjcszrr(user.getObjectid());
			imp1.setGzjd(project.getGzjd());
			imp1.setJjcs(imp1.getJjcsjjwt());
			while(impls.hasNext()){
				Implementation imp = impls.next();
				if(StringUtil.isNullOrEmpty(imp.getJjcs())){
					impls.remove();
				}else{
					imp.setCreatetime(now);
					imp.setCreateby(user.getObjectid());
					imp.setDeleteflag(0);
					imp.setParentobjectid(project.getObjectid());
					imp.setState(ProjectState.DOING);
					imp.setGzjd(0);
				}
			}
			//插入解决措施
			projectMapper.insertImplementation(project);
			//更新问题信息数据
			projectMapper.updateByPrimaryKeySelective(project);
			//获取问题全部内容
			project = projectMapper.selectByPrimaryKey(project.getObjectid());
			InstanceContext instance = instanceMapper.selectByBizobjectid(project.getObjectid());
			User fqr = userMapper.selectByPrimaryKey(project.getCreateby());
			//结束本环节
			WorkItem wtfp = new WorkItem();
			wtfp.setObjectid(user.getWorkItemId());
			wtfp.setFinisher(user.getObjectid());
			wtfp.setFinishtime(now);
			wtfp.setState(ProjectState.DOWN);
			workItemMapper.updateByPrimaryKeySelective(wtfp);
			//如果只有一条解决措施，说明没有分派任务，如果进度为100，则结束本环节,开启问题确认环节
			if(project.getImplementations().size() == 1 && project.getGzjd() == 100){
				WorkItem wtgb = createWorkItem(fqr.getObjectid(), fqr.getObjectid(), null, now, project.getObjectid(), instance.getObjectid(), 0, ProjectState.START, ProjectState.WTQR, Common.wtqr, ProjectState.WTQR);
				workItemMapper.insertSelective(wtgb);
				//给问题关闭人员发微信
				NewsMessage nm = new NewsMessage();
				Articles articles = new Articles();
				articles.title = "您有新的待确认问题";
				articles.description = "[项目编号:" + project.getXmbh() + "][产品名称:" + project.getCpmc() + "][流水号:"
						+ instance.getSequnceno() + "][发起人:" + fqr.getXm() + "]";
				String url = "http://bpm.mesnac.com:8087/Feedback/wtqr/wtqr.do?objectid="
						+ project.getObjectid() + "&workitemid=" + wtgb.getObjectid();
				url = PathUtil.getWXUrl(url);
				articles.url = url;
				nm.news.articles.add(articles);
				nm.touser = fqr.getZh();
				nm.msgtype = "news";
				nm.agentid = 27;
				WXService.sendNewsMessage(nm);
			}
			//如果有两条以上的解决措施，说明有分派任务，分别给各负责人生成问题解决节点，发微信
			List<String> userids = new ArrayList<>();
			for(int i = 0; i < project.getImplementations().size(); i++){
				String uid = project.getImplementations().get(i).getJjcszrr();
				if(!userids.contains(uid)){
					userids.add(uid);
				}
			}
			for(String uid : userids){
				User u = userMapper.selectByPrimaryKey(uid);
				WorkItem wi = createWorkItem(project.getCreateby(), uid, null, now, project.getObjectid(), instance.getObjectid(), 1, ProjectState.START, ProjectState.WTZG, Common.wtzg, ProjectState.WTZG);
				workItemMapper.insertSelective(wi);
				//发微信
				NewsMessage nm = new NewsMessage();
				Articles articles = new Articles();
				articles.title = "您有新的待整改问题";
				articles.description = "[项目编号:" + project.getXmbh() + "][产品名称:" + project.getCpmc() + "][流水号:"
						+ instance.getSequnceno() + "][发起人:" + fqr.getXm() + "]";
				String url = "http://bpm.mesnac.com:8087/Feedback/wtzg/wtzg.do?objectid="
						+ project.getObjectid() + "&workitemid=" + wi.getObjectid();
				url = PathUtil.getWXUrl(url);
				articles.url = url;
				nm.news.articles.add(articles);
				nm.touser = u.getZh();
				nm.msgtype = "news";
				nm.agentid = 27;
				WXService.sendNewsMessage(nm);
			}
			res.setFlag(true);
			res.setData(project);
		}catch(Exception e){
			res.setFlag(false);
			res.setMessage(e.getMessage());
		}
		
		return res;
	}
	
	//问题整改
	@Override
	public Result<ProjectIssueManage> wtzg(ProjectIssueManage project, User user) {
		Result<ProjectIssueManage> res = new Result<>();
		try{
			Date now = new Date();
			int wcjd = 0;
			boolean wcflag = true;
			//更新解决措施信息
 			List<Implementation> ims = project.getImplementations();
			for(Implementation im : ims){
				implementationMapper.updateByPrimaryKeySelective(im);
				if(im.getGzjd() != 100){
					wcflag = false;
				}
			}
			//如果完成自己的任务，则结束自己的任务节点
			if(wcflag){
				WorkItem wi = new WorkItem();
				wi.setObjectid(user.getWorkItemId());
				wi.setFinisher(user.getObjectid());
				wi.setFinishtime(now);
				wi.setState(ProjectState.DOWN);
				workItemMapper.updateByPrimaryKeySelective(wi);
			}
			//获取问题全部内容
			project = projectMapper.selectByPrimaryKey(project.getObjectid());
			InstanceContext instance = instanceMapper.selectByBizobjectid(project.getObjectid());
			User fqr = userMapper.selectByPrimaryKey(project.getCreateby());
			ims = project.getImplementations();
			for(Implementation im : ims){
				wcjd += im.getGzjd();
			}
			wcjd = wcjd/ims.size();
			//开始问题确认环节
			if(wcjd == 100){
				WorkItem wi = createWorkItem(fqr.getObjectid(), fqr.getObjectid(), null, now, project.getObjectid(), instance.getObjectid(), 0, ProjectState.START, ProjectState.WTQR, Common.wtqr, ProjectState.WTQR);
				workItemMapper.insertSelective(wi);
				//给问题确认人员发送微信
				NewsMessage nm = new NewsMessage();
				Articles articles = new Articles();
				articles.title = "您有新的待确认问题";
				articles.description = "[项目编号:" + project.getXmbh() + "][产品名称:" + project.getCpmc() + "][流水号:"
						+ instance.getSequnceno() + "][发起人:" + fqr.getXm() + "]";
				String url = "http://bpm.mesnac.com:8087/Feedback/wtqr/wtqr.do?objectid="
						+ project.getObjectid() + "&workitemid=" + wi.getObjectid();
				url = PathUtil.getWXUrl(url);
				articles.url = url;
				nm.news.articles.add(articles);
				nm.touser = fqr.getZh();
				nm.msgtype = "news";
				nm.agentid = 27;
				WXService.sendNewsMessage(nm);
				//获取问题判定人
				List<User> users = userMapper.selectByWorkItem(project.getObjectid(), ProjectState.WTPD);
				if(users == null || users.size() < 1){
					List<Post> posts = postMapper.getPostByCode(ProjectState.WTPD);
					for(Post p : posts){
						users.add(p.getUser());
					}
				}
				//给问题判定人发送微信通知
				nm = new NewsMessage();
				articles = new Articles();
				articles.title = "您有新的待阅读问题";
				articles.description = "[项目编号:" + project.getXmbh() + "][产品名称:" + project.getCpmc() + "][流水号:"
						+ instance.getSequnceno() + "][发起人:" + fqr.getXm() + "]";
				url = "http://bpm.mesnac.com:8087/Feedback/wtgz/wtgz.do?objectid="
						+ project.getObjectid();
				url = PathUtil.getWXUrl(url);
				articles.url = url;
				nm.news.articles.add(articles);
				nm.touser = users.get(0).getZh();
				nm.msgtype = "news";
				nm.agentid = 27;
				WXService.sendNewsMessage(nm);
			}
			
			
			//更新问题信息
			ProjectIssueManage updatep = new ProjectIssueManage();
			updatep.setObjectid(project.getObjectid());
			updatep.setGzjd(wcjd);
			updatep.setUpdatetime(now);
			updatep.setUpdateby(user.getObjectid());
			projectMapper.updateByPrimaryKeySelective(updatep);
			res.setFlag(true);
			res.setData(project);
		}catch(Exception e){
			res.setFlag(false);
			res.setMessage(e.getMessage());
		}
		return res;
	}


	@Override
	public Result<ProjectIssueManage> wtqr(ProjectIssueManage project, User user) {
		Result<ProjectIssueManage> res = new Result<>();
		try{
			Date now = new Date();
			//完成自己的任务节点
			WorkItem wi = new WorkItem();
			wi.setObjectid(user.getWorkItemId());
			wi.setState(ProjectState.DOWN);
			wi.setFinisher(user.getObjectid());
			wi.setFinishtime(now);
			workItemMapper.updateByPrimaryKeySelective(wi);
			//更新问题信息状态为完成状态
			project.setState(ProjectState.DOWN);
			project.setUpdateby(user.getObjectid());
			project.setUpdatetime(now);
			projectMapper.updateByPrimaryKeySelective(project);
			//获取问题全部内容
			project = projectMapper.selectByPrimaryKey(project.getObjectid());
			InstanceContext instance = instanceMapper.selectByBizobjectid(project.getObjectid());
			User fqr = userMapper.selectByPrimaryKey(project.getCreateby());
			//获取问题判定人
			List<User> users = userMapper.selectByWorkItem(project.getObjectid(), ProjectState.WTPD);
			if(users == null || users.size() < 1){
				List<Post> posts = postMapper.getPostByCode(ProjectState.WTPD);
				for(Post p : posts){
					users.add(p.getUser());
				}
			}
			//开始问题关闭节点，对问题进行归纳
			WorkItem wtgn = createWorkItem(project.getCreateby(), users.get(0).getObjectid(), null, now, project.getObjectid(), instance.getObjectid(), 0, ProjectState.START, ProjectState.WTQR, Common.wtqr, ProjectState.WTQR);
			workItemMapper.insertSelective(wtgn);
			//给问题归纳人发送微信，提醒开始进行问题
			NewsMessage nm = new NewsMessage();
			Articles articles = new Articles();
			articles.title = "您有新的待关闭问题";
			articles.description = "[项目编号:" + project.getXmbh() + "][产品名称:" + project.getCpmc() + "][流水号:"
					+ instance.getSequnceno() + "][发起人:" + fqr.getXm() + "]";
			String url = "http://bpm.mesnac.com:8087/Feedback/wtgb/wtgb.do?objectid="
					+ project.getObjectid() + "&workitemid=" + wtgn.getObjectid();
			url = PathUtil.getWXUrl(url);
			articles.url = url;
			nm.news.articles.add(articles);
			nm.touser = users.get(0).getZh();
			nm.msgtype = "news";
			nm.agentid = 27;
			WXService.sendNewsMessage(nm);
			res.setFlag(true);
			res.setData(project);
		}catch(Exception e){
			res.setFlag(false);
			res.setMessage(e.getMessage());
		}
		return res;
	}

	@Override
	public Result<ProjectIssueManage> wtgb(ProjectIssueManage project, User user) {
		Result<ProjectIssueManage> res = new Result<>();
		try{
			Date now = new Date();
			//完成自己的任务节点
			WorkItem wi = new WorkItem();
			wi.setObjectid(user.getWorkItemId());
			wi.setState(ProjectState.DOWN);
			wi.setFinisher(user.getObjectid());
			wi.setFinishtime(now);
			workItemMapper.updateByPrimaryKeySelective(wi);
			//更新问题信息状态为结束状态
			project.setState(ProjectState.END);
			project.setUpdateby(user.getObjectid());
			project.setUpdatetime(now);
			projectMapper.updateByPrimaryKeySelective(project);
			//获取问题全部内容
			project = projectMapper.selectByPrimaryKey(project.getObjectid());
			res.setFlag(true);
			res.setData(project);
		}catch(Exception e){
			res.setFlag(false);
			res.setMessage(e.getMessage());
		}
		return res;
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

	@Override
	public Result<List<ProjectListItem>> selectTaskByUserActivity(String activity, String userid, int state) {
		Result<List<ProjectListItem>> res = new Result<>();
		try{
			List<ProjectListItem> data = projectMapper.selectTaskByUserAndActivity(activity, userid, state);
			res.setFlag(true);
			res.setData(data);
		}catch(Exception e){
			res.setFlag(false);
			res.setMessage(e.getMessage());
		}
		return res;
	}

	@Override
	public Result<List<ProjectListItem>> searchPli(ProjectListItem pli){
		Result<List<ProjectListItem>> res = new Result<>();
		try{
			List<ProjectListItem> data = projectMapper.searchPli(pli);
			res.setFlag(true);
			res.setData(data);
		}catch(Exception e){
			res.setFlag(false);
			res.setMessage(e.getMessage());
		}
		return res;
	}
	
}
