package com.service.impl;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bean.Attachment;
import com.bean.Implementation;
import com.bean.InstanceContext;
import com.bean.LsjData;
import com.bean.OverTaskParam;
import com.bean.Post;
import com.bean.ProjectIssueManage;
import com.bean.ProjectListItem;
import com.bean.ProjectState;
import com.bean.TQProject;
import com.bean.TZBGData;
import com.bean.Unit;
import com.bean.User;
import com.bean.WorkItem;
import com.bean.Wtdata;
import com.mapper.ImplementationMapper;
import com.mapper.InstanceContextMapper;
import com.mapper.PDMMapper;
import com.mapper.PostMapper;
import com.mapper.ProjectIssueManageMapper;
import com.mapper.UnitMapper;
import com.mapper.UserMapper;
import com.mapper.WorkItemMapper;
import com.mapper.WtdataMapper;
import com.mapper.XGXXMapper;
import com.qq.weixin.mp.aes.WXService;
import com.qq.weixin.mp.aes.bean.Articles;
import com.qq.weixin.mp.aes.bean.NewsMessage;
import com.service.IProjectIssueManageService;
import com.util.DGSUtil;
import com.util.DateUtil;
import com.util.PathUtil;
import com.util.ReadExcelUtil;
import com.util.StringUtil;
import com.util.WTDRUtil;
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
	@Autowired
	private XGXXMapper xgxxMapper;
	@Autowired
	private UnitMapper unitMapper;
	
	private PDMMapper pdmMapper = new PDMMapper();
	//问题提出
	@Override
	public Result<ProjectIssueManage> insert(ProjectIssueManage project, User user) {
		Result<ProjectIssueManage> res = new Result<ProjectIssueManage>();
		String ymd = new SimpleDateFormat("yyyyMMdd").format(new Date());
		ymd = ymd.substring(2, 4);
		try {
			// 存储问题明细
			String id = instanceMapper.getID();
			project.setObjectid(id);
			project.setCreateby(user.getObjectid());
			project.setCreatetime(new Date());
			project.setState(ProjectState.START);
			project.setDeleteflag(0);
			project.setGzjd(0);
			String wtms = project.getWtms();
			 byte bytes[] = {(byte) 0xC2,(byte) 0xA0};
            String UTFSpace = new String(bytes,"utf-8");
            wtms = wtms.replaceAll(UTFSpace, "&nbsp;");
			project.setWtms(wtms);
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
					a.setCreatetime(new Date());
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
			InstanceContext instanceContext = createInstance(project, user, new Date(), ymd, id, instanceid, 0);
			instanceMapper.insertSelective(instanceContext);
			project.setLsh(instanceContext.getSequnceno());
			projectMapper.insert(project);
			// 流程节点生成workitem(问题提出)
			WorkItem workItem = createWorkItem(user.getObjectid(), user.getObjectid(), user.getObjectid(), new Date(), id,
					instanceid, 0, ProjectState.DOWN, ProjectState.WTTC, Common.wttc, ProjectState.WTTC);
			workItem.setFinishtime(new Date());
			workItemMapper.insertSelective(workItem);
			// 流程节点生成workitem(问题判定)
			List<Post> posts = postMapper.getPostByCode(ProjectState.WTPD);
			for (Post p : posts) {
				WorkItem wi = createWorkItem(user.getObjectid(), p.getOwner(), null, new Date(), id, instanceid, 0, ProjectState.START, ProjectState.WTPD,
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
				wtpd.setFinishtime(new Date());
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
			//开始问题处理节点
			WorkItem wtcl = createWorkItem(project.getCreateby(), project.getZrr(), null, new Date(), project.getObjectid(), instance.getObjectid(), 1, ProjectState.START, ProjectState.WTCL, Common.wtcl, ProjectState.WTCL);
			workItemMapper.insertSelective(wtcl);
			User uwtcl = userMapper.selectByPrimaryKey(project.getZrr());
			User fqr = userMapper.selectByPrimaryKey(project.getCreateby());
			//给问题处理人发微信
			NewsMessage nm = new NewsMessage();
			Articles articles = new Articles();
			articles.title = "您有新的待处理问题";
			articles.description = "[项目编号:" + project.getXmbh() + "][产品名称:" + project.getCpmc() + "][流水号:"
					+ instance.getSequnceno() + "][发起人:" + fqr.getXm() + "]";
			String url = "http://bpm.mesnac.com:8087/Feedback/wtcl/wtcl.do?objectid="
					+ project.getObjectid() + "&workitemid=" + wtcl.getObjectid();
			articles.url = PathUtil.getWXUrl(url);
			nm.news.articles.add(articles);
			nm.touser = uwtcl.getZh();
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
	
	//问题处理
	//问题处理的接收人，身份为责任人，可以进行问题分析，问题整改，分派任务给相关负责人
	//方案执行人，身份通责任人，可以进行问题分析，问题整改，分派任务，
	//接收到任务的问题处理人，只能进行问题整改，不能再分派任务
	public Result<ProjectIssueManage> wtcl(ProjectIssueManage project, User user){
		Result<ProjectIssueManage> res = new Result<>();
		try{
			//更新问题信息和解决方案信息
			//查询当前【问题处理】的活动节点
			boolean iszrr = true;//责任人标识
			boolean isfazxr = false;//方案执行人标识
			boolean fawi = false;//生成方案执行人的任务标识
			WorkItem active = new WorkItem();
			active.setActivitycode(ProjectState.WTCL);
			active.setReceiver(user.getObjectid());
			active.setState(ProjectState.START);
			active.setBizobjectid(project.getObjectid());
			List<WorkItem> wis = workItemMapper.selectBySelective(active);
			if(wis != null && wis.size() > 0){
				active = wis.get(0);
			}else{
				res.setFlag(false);
				res.setMessage("该任务不存在！");
				return res;
			}
			//判断当前处理人是否为责任人
			ProjectIssueManage projectAll = projectMapper.selectByPrimaryKey(project.getObjectid());
			if(projectAll.getZrr().equals(user.getObjectid())){
				iszrr = true;
			}else{
				iszrr = false;
			}
			//判断当前处理人是否为方案执行人
			if(!StringUtil.isNullOrEmpty(projectAll.getFazxr()) && projectAll.getFazxr().equals(user.getObjectid())){
				isfazxr = true;
			}else{
				isfazxr = false;
			}
			if(!StringUtil.isNullOrEmpty(project.getFazxr()) && !project.getFazxr().equals(user.getObjectid())){
				isfazxr = false;
			}
			//判断是否新增方案执行人任务
			//不是责任人，也不是上一个方案执行人
			if(!StringUtil.isNullOrEmpty(project.getFazxr()) && !project.getFazxr().equals(projectAll.getZrr()) && !StringUtil.isNullOrEmpty(projectAll.getFazxr()) && !project.getFazxr().equals(projectAll.getFazxr())){
				fawi = true;
			}else{
				fawi = false;
			}
			//如果方案执行人被重新更改，结束上一个方案执行人的任务
			//上一个方案执行人是责任人的话不结束
			if(!StringUtil.isNullOrEmpty(projectAll.getFazxr()) && !StringUtil.isNullOrEmpty(project.getFazxr()) && !project.getFazxr().equals(projectAll.getFazxr()) && !projectAll.getFazxr().equals(projectAll.getZrr())){
				WorkItem fawork = new WorkItem();
				fawork.setActivitycode(ProjectState.WTCL);
				fawork.setBizobjectid(project.getObjectid());
				fawork.setBizobjectschemacode(ProjectState.WTCL);
				fawork.setReceiver(projectAll.getFazxr());
				fawork.setState(ProjectState.START);
				List<WorkItem> faworks = workItemMapper.selectBySelective(fawork);
				if(faworks != null && !faworks.isEmpty()){
					for(WorkItem wi : faworks){
						wi.setUpdatetime(new Date());
						wi.setState(ProjectState.CANCEL);
						workItemMapper.updateByPrimaryKeySelective(wi);
					}
				}
			}
			project.setUpdateby(user.getObjectid());
			project.setUpdatetime(new Date());
			projectMapper.updateByPrimaryKeySelective(project);
			//责任人和方案执行人可以分派任务
			List<String> zgrs = new ArrayList<>();
			if(iszrr || isfazxr){
				if(project.getImplementations() != null && project.getImplementations().size() > 0){
					//存在分派任务
					//遍历分派任务
					Iterator<Implementation> iterator = project.getImplementations().iterator();
					List<Implementation> insertimpl = new ArrayList<Implementation>();
					List<Implementation> updateimpl = new ArrayList<Implementation>();
					while(iterator.hasNext()){
						Implementation imp = iterator.next();
						if(StringUtil.isNullOrEmpty(imp.getObjectid()) && StringUtil.isNullOrEmpty(imp.getJjcs()) && StringUtil.isNullOrEmpty(imp.getJjcszrr())){
							//剔除无效任务
							iterator.remove();
						}else if(StringUtil.isNullOrEmpty(imp.getObjectid())){
							//新增措施
							imp.setCreateby(user.getObjectid());
							imp.setCreatetime(new Date());
							imp.setParentobjectid(project.getObjectid());
							imp.setGzjd(0);
							insertimpl.add(imp);
							if(!zgrs.contains(imp.getJjcszrr())){
								zgrs.add(imp.getJjcszrr());
							}
						}else{
							//更新措施，包括删除措施
							imp.setUpdateby(user.getObjectid());
							imp.setUpdatetime(new Date());
							updateimpl.add(imp);
						}
					}
					if(insertimpl.size() > 0){
						ProjectIssueManage pi = new ProjectIssueManage();
						pi.setImplementations(insertimpl);
						projectMapper.insertImplementation(pi);
					}
					if(updateimpl.size() > 0){
						ProjectIssueManage pi = new ProjectIssueManage();
						pi.setImplementations(updateimpl);
						implementationMapper.updateImplementations(pi);
					}
				}
			//措施执行人
			}else{
				if(project.getImplementations() != null){
					for(Implementation impl : project.getImplementations()){
						if(impl.getGzjd() != null && impl.getGzjd() == 100){
							impl.setJjcswcsj(new Date());
						}
						impl.setUpdatetime(new Date());
						impl.setUpdateby(user.getObjectid());
					}
				}
				implementationMapper.updateImplementations(project);
				//如果本人的所有的任务都已经完成，则结束本人任务
				if(project.getImplementations() != null){
					int gzjd = 0;
					int i = 0;
					for(Implementation imp : project.getImplementations()){
						gzjd += imp.getGzjd();
						i++;
					}
					gzjd = gzjd/i;
					if(gzjd == 100){
						WorkItem wtzg = new WorkItem();
						wtzg.setReceiver(user.getObjectid());
						wtzg.setActivitycode(ProjectState.WTCL);
						wtzg.setBizobjectschemacode(ProjectState.WTZG);
						wtzg.setBizobjectid(project.getObjectid());
						List<WorkItem> wtzgs = workItemMapper.selectBySelective(wtzg);
						if(wtzgs != null && wtzgs.size() > 0){
							for(WorkItem wi : wtzgs){
								wi.setUpdatetime(new Date());
								wi.setFinisher(user.getObjectid());
								wi.setFinishtime(new Date());
								wi.setState(ProjectState.DOWN);
								workItemMapper.updateByPrimaryKeySelective(wi);
							}
						}
					}
				}
			}
			//获取问题全部内容
			project = projectMapper.selectByPrimaryKey(project.getObjectid());
			InstanceContext instance = instanceMapper.selectByBizobjectid(project.getObjectid());
			User fqr = project.getFqr();
			for(String oid : zgrs){
				//生成问题整改任务
				WorkItem wtzg = createWorkItem(project.getCreateby(), oid, null, new Date(), project.getObjectid(), instance.getObjectid(), 1, ProjectState.START, ProjectState.WTCL, Common.wtcl, ProjectState.WTZG);
				workItemMapper.insertSelective(wtzg);
				//给问题整改人发微信
				User zgr = userMapper.selectByPrimaryKey(oid);
				NewsMessage nm = new NewsMessage();
				Articles articles = new Articles();
				articles.title = "您有新的待处理问题";
				articles.description = "[项目编号:" + project.getXmbh() + "][产品名称:" + project.getCpmc() + "][流水号:"
						+ instance.getSequnceno() + "][发起人:" + fqr.getXm() + "]";
				String url = "http://bpm.mesnac.com:8087/Feedback/wtcl/wtcl.do?objectid="
						+ project.getObjectid() + "&workitemid=" + wtzg.getObjectid();
				articles.url = PathUtil.getWXUrl(url);
				nm.news.articles.add(articles);
				nm.touser = zgr.getZh();
				nm.msgtype = "news";
				nm.agentid = 27;
				WXService.sendNewsMessage(nm);
			}
			//如果当前处理人不是方案执行人，生成方案执行人任务，发送微信消息,如果当前处理人不是负责人结束处理人任务
			if(fawi){
				WorkItem fazx = createWorkItem(project.getCreateby(), project.getFazxr(),null, new Date(), project.getObjectid(), instance.getObjectid(), 1, ProjectState.START, ProjectState.WTCL, Common.wtcl, ProjectState.WTCL);
				workItemMapper.insertSelective(fazx);
				if(!iszrr){
					active.setState(ProjectState.DOWN);
					active.setUpdatetime(new Date());
					active.setFinisher(user.getObjectid());
					active.setFinishtime(new Date());
					workItemMapper.updateByPrimaryKeySelective(active);					
				}
				//给方案执行人发送微信
				User fazxr = userMapper.selectByPrimaryKey(project.getFazxr());
				NewsMessage nm = new NewsMessage();
				Articles articles = new Articles();
				articles.title = "您有新的待处理问题";
				articles.description = "[项目编号:" + project.getXmbh() + "][产品名称:" + project.getCpmc() + "][流水号:"
						+ instance.getSequnceno() + "][发起人:" + fqr.getXm() + "]";
				String url = "http://bpm.mesnac.com:8087/Feedback/wtcl/wtcl.do?objectid="
						+ project.getObjectid() + "&workitemid=" + fazx.getObjectid();
				articles.url = PathUtil.getWXUrl(url);
				nm.news.articles.add(articles);
				nm.touser = fazxr.getZh();
				nm.msgtype = "news";
				nm.agentid = 27;
				WXService.sendNewsMessage(nm);
			}
			//问题处理节点完成
			if(project.getGzjd() == 100 && (user.getObjectid().equals(project.getFazxr()) || user.getObjectid().equals(project.getZrr()))){
				//结束负责人和方案执行人的任务
				WorkItem wtcl = new WorkItem();
				wtcl.setBizobjectid(project.getObjectid());
				wtcl.setActivitycode(ProjectState.WTCL);
				wtcl.setBizobjectschemacode(ProjectState.WTCL);
				wtcl.setState(ProjectState.START);
				List<WorkItem> wtcls = workItemMapper.selectBySelective(wtcl);
				if(wtcls != null && !wtcls.isEmpty()){
					for(WorkItem wi : wtcls){
						wi.setUpdatetime(new Date());
						wi.setFinisher(user.getObjectid());
						wi.setFinishtime(new Date());
						wi.setState(ProjectState.DOWN);;
						workItemMapper.updateByPrimaryKeySelective(wi);
					}
				}
				//开始问题解决确认环节
				WorkItem wtqr = createWorkItem(project.getCreateby(), project.getCreateby(), null, new Date(), project.getObjectid(), instance.getObjectid(), 0, ProjectState.START, ProjectState.WTQR, Common.wtqr, ProjectState.WTQR);
				workItemMapper.insertSelective(wtqr);
				//给问题解决确认人发微信
				NewsMessage nm = new NewsMessage();
				Articles articles = new Articles();
				articles.title = "您有新的待解决问题";
				articles.description = "[项目编号:" + project.getXmbh() + "][产品名称:" + project.getCpmc() + "][流水号:"
						+ instance.getSequnceno() + "][发起人:" + fqr.getXm() + "]";
				String url = "http://bpm.mesnac.com:8087/Feedback/wtqr/wtqr.do?objectid="
						+ project.getObjectid() + "&workitemid=" + wtqr.getObjectid();
				articles.url = PathUtil.getWXUrl(url);
				nm.news.articles.add(articles);
				nm.touser = fqr.getZh();
				nm.msgtype = "news";
				nm.agentid = 27;
				WXService.sendNewsMessage(nm);
			}
			res.setFlag(true);
			res.setData(project);
		}catch(Exception e){
			e.printStackTrace();
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
				wtpd.setFinishtime(new Date());
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
			WorkItem wtzg = createWorkItem(project.getCreateby(), project.getFazxr(), null, new Date(), project.getObjectid(), instance.getObjectid(), 1, ProjectState.START, ProjectState.WTZG, Common.wtqr, ProjectState.WTFP);
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
		try{
			//去除无效的解决措施
			Iterator<Implementation> impls = project.getImplementations().iterator(); 
			Implementation imp1 = impls.next();
			imp1.setCreatetime(new Date());
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
					imp.setCreatetime(new Date());
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
			wtfp.setFinishtime(new Date());
			wtfp.setState(ProjectState.DOWN);
			workItemMapper.updateByPrimaryKeySelective(wtfp);
			//如果只有一条解决措施，说明没有分派任务，如果进度为100，则结束本环节,开启问题确认环节
			if(project.getImplementations().size() == 1 && project.getGzjd() == 100){
				WorkItem wtgb = createWorkItem(fqr.getObjectid(), fqr.getObjectid(), null, new Date(), project.getObjectid(), instance.getObjectid(), 0, ProjectState.START, ProjectState.WTQR, Common.wtqr, ProjectState.WTQR);
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
				WorkItem wi = createWorkItem(project.getCreateby(), uid, null, new Date(), project.getObjectid(), instance.getObjectid(), 1, ProjectState.START, ProjectState.WTZG, Common.wtzg, ProjectState.WTZG);
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
				wi.setFinishtime(new Date());
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
				WorkItem wi = createWorkItem(fqr.getObjectid(), fqr.getObjectid(), null, new Date(), project.getObjectid(), instance.getObjectid(), 0, ProjectState.START, ProjectState.WTQR, Common.wtqr, ProjectState.WTQR);
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
			updatep.setUpdatetime(new Date());
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

	//解决确认
	@Override
	public Result<ProjectIssueManage> wtqr(ProjectIssueManage project, User user) {
		Result<ProjectIssueManage> res = new Result<>();
		try{
			//完成自己的任务节点
			WorkItem wi = new WorkItem();
			wi.setObjectid(user.getWorkItemId());
			wi.setState(ProjectState.DOWN);
			wi.setFinisher(user.getObjectid());
			wi.setFinishtime(new Date());
			workItemMapper.updateByPrimaryKeySelective(wi);
			//更新问题信息状态为完成状态
			project.setState(ProjectState.DOWN);
			project.setUpdateby(user.getObjectid());
			project.setUpdatetime(new Date());
			project.setSjwcsj(new Date());
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
			WorkItem wtgn = createWorkItem(project.getCreateby(), users.get(0).getObjectid(), null, new Date(), project.getObjectid(), instance.getObjectid(), 0, ProjectState.START, ProjectState.WTGB, Common.wtgb, ProjectState.WTGB);
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

	//问题关闭
	@Override
	public Result<ProjectIssueManage> wtgb(ProjectIssueManage project, User user) {
		Result<ProjectIssueManage> res = new Result<>();
		try{
			//完成自己的任务节点
			WorkItem wi = new WorkItem();
			wi.setObjectid(user.getWorkItemId());
			wi.setState(ProjectState.DOWN);
			wi.setFinisher(user.getObjectid());
			wi.setFinishtime(new Date());
			workItemMapper.updateByPrimaryKeySelective(wi);
			//结束所有分派的问题处理
			WorkItem wtcl = new WorkItem();
			wtcl.setBizobjectid(project.getObjectid());
			wtcl.setActivitycode(ProjectState.WTCL);
			wtcl.setBizobjectschemacode(ProjectState.WTZG);
			wtcl.setState(ProjectState.START);
			List<WorkItem> wtcls = workItemMapper.selectBySelective(wtcl);
			if(wtcls != null && !wtcls.isEmpty()){
				for(WorkItem wicl : wtcls){
					wi.setUpdatetime(new Date());
					wicl.setFinisher(user.getObjectid());
					wicl.setFinishtime(new Date());
					wicl.setState(ProjectState.DOWN);;
					workItemMapper.updateByPrimaryKeySelective(wicl);
				}
			}
			//更新问题信息状态为结束状态
			project.setState(ProjectState.END);
			project.setUpdateby(user.getObjectid());
			project.setUpdatetime(new Date());
			project.setWtgbsj(new Date());
			projectMapper.updateByPrimaryKeySelective(project);
			//更新详细解决措施内容
			if(project.getImplementations() != null && project.getImplementations().size() > 0){
				List<Implementation> is = project.getImplementations();
				for(Implementation imp : is){
					imp.setUpdateby(user.getObjectid());
					imp.setUpdatetime(new Date());
				}
				implementationMapper.updateImplementations(project);
			}
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
	public Result<ProjectIssueManage> updateByObjectId(ProjectIssueManage project, User user) {
		Result<ProjectIssueManage> res = new Result<>();
		try{
			project.setUpdateby(user.getObjectid());
			project.setUpdatetime(new Date());;
			projectMapper.updateByPrimaryKeySelective(project);
			res.setFlag(true);
			res.setData(project);
		}catch(Exception e){
			e.printStackTrace();
			res.setFlag(false);
			res.setMessage(e.getMessage());
		}
		return res;
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
	public Result<List<ProjectListItem>> selectTaskByUserActivity(ProjectListItem pli) {
		Result<List<ProjectListItem>> res = new Result<>();
		try{
			List<ProjectListItem> data = projectMapper.selectTaskByUserAndActivity(pli);
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
		int size = 20;
		try{
			List<ProjectListItem> data = projectMapper.searchPli(pli);
			List<ProjectListItem> datareturn = new ArrayList<>();
			int count = data.size();
			int page = 1;
			if(pli.getPagecurrent() != null && pli.getPagecurrent() > 0){
				page = pli.getPagecurrent();
			}
			while((page-1)*size > data.size()){
				if(page >1){
					page--;
				}else{
					break;
				}
			}
			int pagepre = page - 1;
			for(int i = pagepre * size; i < page * size && i < count; i++){
				datareturn.add(data.get(i));
			}
			res.setCount(count);
			res.setFlag(true);
			res.setData(datareturn);
		}catch(Exception e){
			e.printStackTrace();
			res.setFlag(false);
			res.setMessage(e.getMessage());
		}
		return res;
	}

	@Override
	public Result<OverTaskParam> getOverTask(String userid) {
		Result<OverTaskParam> res = new Result<>();
		try{
			OverTaskParam data = projectMapper.selectOverTaskCount(userid);
			res.setFlag(true);
			res.setData(data);
		}catch(Exception e){
			e.printStackTrace();
			res.setFlag(false);
			res.setMessage(e.getMessage());
		}
		return res;
	}

	@Override
	public Result<List<ProjectListItem>> getOverTask(OverTaskParam otp) {
		Result<List<ProjectListItem>> res = new Result<>();
		try{
			List<ProjectListItem> data = projectMapper.selectOverTask(otp);
			res.setFlag(true);
			res.setData(data);
		}catch(Exception e){
			e.printStackTrace();
			res.setFlag(false);
			res.setMessage(e.getMessage());
		}
		return res;
	}

	//根据问题号获取非BOM流程数据
	@Override
	public Result<List<LsjData>> getLsjData(String wth) {
		Result<List<LsjData>> res = new Result<>();
		try{
			List<LsjData> data = xgxxMapper.selectLsjData(wth);
			res.setData(data);
			res.setFlag(true);
		}catch(Exception e){
			res.setFlag(false);
			res.setMessage(e.getMessage());
		}
		return res;
	}
	//根据BPM流程单号获取流程节点处理情况
	@Override
	public Result<List<WorkItem>> getLsjWorkItem(String lsh){
		Result<List<WorkItem>> res = new Result<>();
		try{
			List<WorkItem> data = xgxxMapper.getLsjWorkItem(lsh);
			res.setData(data);
			res.setFlag(true);
		}catch(Exception e){
			res.setFlag(false);
			res.setMessage(e.getMessage());
		}
		return res;
	}

	//根据问题号获取图纸变更流程数据
	@Override
	public Result<List<TZBGData>> getTZBGData(String wth) {
		Result<List<TZBGData>> res = new Result<>();
		try{
			List<TZBGData> data = pdmMapper.selectByWth(wth);
			res.setData(data);
			res.setFlag(true);
		}catch(Exception e){
			e.printStackTrace();
			res.setFlag(false);
			res.setMessage(e.getMessage());
		}
//		try{
//			for(DA02Data da : res.getData()){
//				//从SAP获取物料描述和单位
//				SAPUtil sap = new SAPUtil();
//				Map<String, String> paramIn = new HashMap<>();
//				paramIn.put("I_MATNR", da.getBM());
//				Map<String, String> paramOut = sap.execute("ZCO_GET_MATE_INFO", paramIn);
//				da.setWLMS(paramOut.get("O_MAKTX"));
//				da.setDW(paramOut.get("O_MEINS"));
//			}
//		}catch(Exception e){
//			e.printStackTrace();
//		}
		return res;
	}
	//根据图纸变更流程单号获取流程节点处理情况
	@Override
	public Result<List<WorkItem>> getTZBGWorkItem(String wth) {
		Result<List<WorkItem>> res = new Result<>();
		try{
			List<TZBGData> data = pdmMapper.selectByWth(wth);
			WorkItem wi = new WorkItem();
			if(data.size() > 0){
				TZBGData d = data.get(0);
				wi.setActivityname("图纸变更");
				wi.setActivitycode(d.getPHT_ECNNO());
				wi.setFinisher(d.getPdate_released());
				wi.setReceivername(d.getPHT_APPLYER());
			}
			List<WorkItem> datas = new ArrayList<WorkItem>();
			datas.add(wi);
			res.setData(datas);
			res.setFlag(true);
		}catch(Exception e){
			res.setFlag(false);
			res.setMessage(e.getMessage());
		}
		return res;
	}

	//导入
	@Override
	public Result<List<ProjectIssueManage>> importProject(String filename) {
		Result<List<ProjectIssueManage>> res = new Result<>();
		try {  
			String srcFile = PathUtil.HOST + filename;
			String newFilename = DateUtil.nowLong() + filename.substring(filename.indexOf("."));
			String dstFile = PathUtil.HOST + newFilename;
			//解密文件
			Result<String> dgsres = DGSUtil.DECFile(srcFile, dstFile);
			if(!dgsres.isFlag()){
				res.setFlag(false);
				res.setMessage("文件读取失败，请联系管理员！");
				return res;
			}
            ReadExcelUtil excelReader = new ReadExcelUtil(PathUtil.DRWJ_PATH + newFilename);  
            // 对读取Excel表格内容测试  
            Map<Integer, Map<Integer,Object>> wts = excelReader.readExcelContent();  
            List<User> users = userMapper.getList(new User());
            Map<String, User> userMap = new HashMap<>();
            for(User user : users){
            	userMap.put(user.getXm(), user);
            }
            List<Post> pdps =  postMapper.getPostByCode(ProjectState.WTPD);
            List<Wtdata> wtdatas = wtdataMapper.selectAll();
            Map<String, String> dataMap = new HashMap<>();
            for(Wtdata data : wtdatas){
            	dataMap.put(data.getText(), data.getValue());
            }
            Result<List<ProjectIssueManage>> drres = WTDRUtil.wtdr(wts, userMap, dataMap);
            if(drres.isFlag()){
            	for(ProjectIssueManage project : drres.getData()){
                	String bizobjectid = instanceMapper.getID();
                	project.setObjectid(bizobjectid);
                	String instanceid = instanceMapper.getID();
                	//确定完成情况
                	Integer state = ProjectState.START;
                	String wcqk = project.getWcqk();
                	if(StringUtil.isNullOrEmpty(wcqk)){
                		state = ProjectState.START;
                	}else if(wcqk.trim().equals("按期完成")){
                		state = ProjectState.DOWN;
                	}else if(wcqk.trim().equals("处理中")){
                		state = ProjectState.START;
                	}else if(wcqk.trim().equals("拖期完成")){
                		state = ProjectState.DOWN;
                	}else if(wcqk.trim().equals("拖期未完成")){
                		state = ProjectState.START;
                	}
                	InstanceContext instanceContext = new InstanceContext();
            		// 流水号生成
                	String ymd = new SimpleDateFormat("yyyyMMdd").format(new Date());
            		ymd = ymd.substring(2, 4);
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
            		instanceContext.setBizobjectid(bizobjectid);
            		instanceContext.setObjectid(instanceid);
            		instanceContext.setCreatedtime(new Date());
            		instanceContext.setOriginator(project.getTbr());
            		instanceContext.setSequnceno(orders);
            		instanceContext.setStarttime(new Date());
            		instanceContext.setState(state);
        			instanceMapper.insertSelective(instanceContext);
        			project.setLsh(instanceContext.getSequnceno());
        			project.setLsh(orders);
        			project.setState(state);
        			project.setDeleteflag(0);
        			project.setBz("DeleteFlag");
                	
                	if(state.equals(ProjectState.START)){
                		//处理环节
                		WorkItem wicl = new WorkItem();
                		wicl.setObjectid(instanceMapper.getID());
                		wicl.setStarttime(new Date());
                		wicl.setCreatetime(new Date());
                		wicl.setReceiver(project.getZrr());
                		wicl.setReceivetime(new Date());
                		wicl.setState(ProjectState.START);
                		wicl.setBizobjectid(bizobjectid);
                		wicl.setInstanceid(instanceid);
                		wicl.setActivitycode(ProjectState.WTCL);
                		wicl.setActivityname("问题处理");
                		wicl.setBizobjectschemacode(ProjectState.WTCL);
                		workItemMapper.insertSelective(wicl);
                		if(StringUtil.isNullOrEmpty(project.getYyfx()) && StringUtil.isNullOrEmpty(project.getClfa())){
                			
                		}else{
                			if(project.getFazxr() != null){
                				WorkItem wizg = new WorkItem();
                				wizg.setObjectid(instanceMapper.getID());
                				wizg.setStarttime(new Date());
                				wizg.setCreatetime(new Date());
                				wizg.setReceiver(project.getFazxr());
                				wizg.setReceivetime(new Date());
                				wizg.setState(ProjectState.START);
                				wizg.setBizobjectid(bizobjectid);
                				wizg.setInstanceid(instanceid);
                				wizg.setActivitycode(ProjectState.WTCL);
                				wizg.setActivityname("问题处理");
                				wizg.setBizobjectschemacode(ProjectState.WTCL);
                        		workItemMapper.insertSelective(wizg);
                			}else{
                				project.setFazxr(project.getZrr());
                			}
                		}
                		
                		
                	}else{
                		//开启问题关闭环节
                		project.setGzjd(100);
                		if(StringUtil.isNullOrEmpty(project.getFazxr())){
                			project.setFazxr(project.getZrr());
                		}
                		for(Post p : pdps){
                			String userid = p.getUser().getObjectid();
                			WorkItem wigb = new WorkItem();
                			wigb.setObjectid(instanceMapper.getID());
                			wigb.setStarttime(new Date());
                			wigb.setCreatetime(new Date());
                			wigb.setReceiver(userid);
                			wigb.setReceivetime(new Date());
                			wigb.setState(ProjectState.START);
                			wigb.setBizobjectid(bizobjectid);
                			wigb.setInstanceid(instanceid);
                			wigb.setActivitycode(ProjectState.WTGB);
                			wigb.setActivityname("问题关闭");
                			wigb.setBizobjectschemacode(ProjectState.WTGB);
                			workItemMapper.insertSelective(wigb);
                		}
                	}
                	projectMapper.insertSelective(project);
                }
            	res.setData(drres.getData());
            	res.setFlag(true);
            }else{
            	res.setFlag(false);
            	res.setMessage(drres.getMessage());
            }
        } catch (FileNotFoundException e) {  
            System.out.println("未找到指定路径的文件!");  
            e.printStackTrace();  
            res.setFlag(false);
            res.setMessage(e.getMessage());
        }catch (Exception e) {  
            e.printStackTrace();  
            res.setFlag(false);
            res.setMessage(e.getMessage());
        }  
		return res;
	}
	@Override
	public Result<TQProject> getTQProjectCount(TQProject record){
		Result<TQProject> res = new Result<>();
		try{
			TQProject data = projectMapper.selectOverProjectCount(record);
			res.setFlag(true);
			res.setData(data);
		}catch(Exception e){
			e.printStackTrace();
			res.setFlag(false);
			res.setMessage(e.getMessage());
		}
		return res;
	}
	//给每个人发送待办任务、拖期任务页面
	@Override
    public void sendDBTQ(){
    	//获取所有用户
    	User u = new User();
    	List<User> users = userMapper.getList(u);
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	String title = "项目质量问题管理系统：" + sdf.format(new Date()) + " 您尚未处理的流程汇总";
    	for(User user : users){
    		//获取用户的代办任务和拖期任务
    		Result<OverTaskParam> otpres = getOverTask(user.getObjectid());
    		if(otpres.isFlag()){
    			OverTaskParam otp = otpres.getData();
    			String messageInfo = "您有";
    			if(!StringUtil.isNullOrEmpty(otp.getDbrw()) && !otp.getDbrw().equals("0")){
    				messageInfo += otp.getDbrw() + "条待办任务;\n";
    			}
    			if(!StringUtil.isNullOrEmpty(otp.getYtn()) && !otp.getYtn().equals("0")){
    				messageInfo += otp.getYtn() + "条拖期1天以内任务;\n";
    			}
    			if(!StringUtil.isNullOrEmpty(otp.getStn()) && !otp.getStn().equals("0")){
    				messageInfo += otp.getStn() + "条拖期3天以内任务;\n";
    			}
    			if(!StringUtil.isNullOrEmpty(otp.getWtn()) && !otp.getWtn().equals("0")){
    				messageInfo += otp.getWtn() + "条拖期5天以内任务;\n";
    			}
    			if(!StringUtil.isNullOrEmpty(otp.getWts()) && !otp.getWts().equals("0")){
    				messageInfo += otp.getWts() + "条拖期5天以上任务;";
    			}
    			if(messageInfo.length() > 2){
    				String url = "http://bpm.mesnac.com:8087/Feedback/wtcx/overtasky.do";
    				sendWXMessage(title, messageInfo, url, user.getZh());
    			}
    		}
    	}
    }
    //给管理员发送拖期未解决，拖期未关闭问题
    @Override
    public void sendTQWT(){
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	String title = "项目质量问题管理系统：" + sdf.format(new Date()) + " 拖期问题汇总";
    	//给【部门负责人】发送通知
    	List<Post> bms = postMapper.getPostByCode(Common.bmfzr);
    	for(Post p : bms){
    		TQProject tqp = new TQProject();
    		tqp.setUnitcode(p.getUser().getSzbm());
    		TQProject tq = projectMapper.selectOverProjectCount(tqp);
    		String messageInfo = "";
			if(tq.getYtnwjj() != null && tq.getYtnwjj() > 0){
				messageInfo += tq.getYtnwjj() + "条一天内未解决的问题;\n";
			}
			if(tq.getStnwjj() != null && tq.getStnwjj() > 0){
				messageInfo += tq.getStnwjj() + "条三天内未解决的问题;\n";
			}
			if(tq.getWtnwjj() != null && tq.getWtnwjj() > 0){
				messageInfo += tq.getWtnwjj() + "条五天内未解决的问题;\n";
			}
			if(tq.getWtswjj() != null && tq.getWtswjj() > 0){
				messageInfo += tq.getWtswjj() + "条五天以上未解决的问题;\n";
			}
			if(tq.getYtnwgb() != null && tq.getYtnwgb() > 0){
				messageInfo += tq.getYtnwgb() + "条一天内未关闭的问题;\n";
			}
			if(tq.getStnwgb() != null && tq.getStnwgb() > 0){
				messageInfo += tq.getStnwgb() + "条三天内未关闭的问题;\n";
			}
			if(tq.getWtnwgb() != null && tq.getWtnwgb() > 0){
				messageInfo += tq.getWtnwgb() + "条五天内未关闭的问题;\n";
			}
			if(tq.getWtswgb() != null && tq.getWtswgb() > 0){
				messageInfo += tq.getWtswgb() + "条五天以上未关闭的问题;";
			}
			if(messageInfo.length() > 2){
				String url = "http://bpm.mesnac.com:8087/Feedback/wtcx/tqwt.do";
				sendWXMessage(title, messageInfo, url, p.getUser().getZh());
			}
    	}
    	//项目经理
    	List<Post> xmjls = postMapper.getPostByCode(Common.xmjl);
    	for(Post p : xmjls){
    		TQProject tqp = new TQProject();
    		tqp.setXmjl(p.getOwner());
    		TQProject tq = projectMapper.selectOverProjectCount(tqp);
    		String messageInfo = "";
			if(tq.getYtnwjj() != null && tq.getYtnwjj() > 0){
				messageInfo += tq.getYtnwjj() + "条一天内未解决的问题;\n";
			}
			if(tq.getStnwjj() != null && tq.getStnwjj() > 0){
				messageInfo += tq.getStnwjj() + "条三天内未解决的问题;\n";
			}
			if(tq.getWtnwjj() != null && tq.getWtnwjj() > 0){
				messageInfo += tq.getWtnwjj() + "条五天内未解决的问题;\n";
			}
			if(tq.getWtswjj() != null && tq.getWtswjj() > 0){
				messageInfo += tq.getWtswjj() + "条五天以上未解决的问题;";
			}
			if(tq.getYtnwgb() != null && tq.getYtnwgb() > 0){
				messageInfo += tq.getYtnwgb() + "条一天内未关闭的问题;\n";
			}
			if(tq.getStnwgb() != null && tq.getStnwgb() > 0){
				messageInfo += tq.getStnwgb() + "条三天内未关闭的问题;\n";
			}
			if(tq.getWtnwgb() != null && tq.getWtnwgb() > 0){
				messageInfo += tq.getWtnwgb() + "条五天内未关闭的问题;\n";
			}
			if(tq.getWtswgb() != null && tq.getWtswgb() > 0){
				messageInfo += tq.getWtswgb() + "条五天以上未关闭的问题;";
			}
			if(messageInfo.length() > 2){
				String url = "http://bpm.mesnac.com:8087/Feedback/wtcx/tqwt.do";
				sendWXMessage(title, messageInfo, url, p.getUser().getZh());
			}
    	}
    }
    //发送微信消息
    public void sendWXMessage(String title, String description, String url, String touser){
    	NewsMessage nm = new NewsMessage();
		Articles articles = new Articles();
		articles.title = title;
		articles.description = description;
		articles.url = PathUtil.getWXUrl(url);
		nm.news.articles.add(articles);
		nm.touser = touser;
		nm.msgtype = "news";
		nm.agentid = 27;
		WXService.sendNewsMessage(nm);
    }

	@Override
	public Result<List<ProjectListItem>> getTQProject(TQProject record) {
		Result<List<ProjectListItem>> res = new Result<>();
		try{
			List<ProjectListItem> data = projectMapper.selectOverProject(record);
			res.setFlag(true);
			res.setData(data);
		}catch(Exception e){
			e.printStackTrace();
			res.setFlag(false);
			res.setMessage(e.getMessage());
		}
		return res;
	}
	
}
