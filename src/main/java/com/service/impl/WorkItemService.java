     package com.service.impl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bean.ProjectIssueManage;
import com.bean.ProjectState;
import com.bean.User;
import com.bean.WorkItem;
import com.mapper.InstanceContextMapper;
import com.mapper.ProjectIssueManageMapper;
import com.mapper.UserMapper;
import com.mapper.WorkItemMapper;
import com.qq.weixin.mp.aes.WXService;
import com.qq.weixin.mp.aes.bean.Articles;
import com.qq.weixin.mp.aes.bean.NewsMessage;
import com.service.IWorkItemService;
import com.util.PathUtil;
import com.util.StringUtil;
import com.util.bean.Result;

@Service
public class WorkItemService implements IWorkItemService{

	@Autowired
	private WorkItemMapper workItemMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private InstanceContextMapper instanceMapper;
	
	@Autowired
	private ProjectIssueManageMapper projectMapper;
	
	private Logger log = LoggerFactory.getLogger(WorkItemService.class);
	@Override
	public Result<WorkItem> getByObjectId(String objectid) {
		Result<WorkItem> res = new Result<WorkItem>();
		if(StringUtil.isNullOrEmpty(objectid)){
			res.setFlag(false);
			res.setMessage("不存在的任务");
			return res;
		}
		try{
			WorkItem data = workItemMapper.selectByPrimaryKey(objectid);
			res.setFlag(true);
			res.setData(data);
		}catch(Exception e){
			res.setFlag(false);
			res.setMessage(e.getMessage());
		}
		
		return res;
	}

	@Override
	public Result<WorkItem> updateByObjectId(WorkItem wi) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result<WorkItem> insert(WorkItem wi) {
		return null;
	}

	@Override
	public Result<List<WorkItem>> getWorkFlowStatus(String bizobjectid) {
		Result<List<WorkItem>> res = new Result<>();
		try{
			List<WorkItem> data = workItemMapper.selectWorkFlowStatus(bizobjectid);
			Iterator<WorkItem> di = data.iterator();
			while(di.hasNext()){
				WorkItem wi = di.next();
				if(wi.getActivitycode().equals(ProjectState.WTPD) && wi.getFinishername() != null && !wi.getReceivername().equals(wi.getFinishername())){
					di.remove();
				}
				if(wi.getActivitycode().equals(ProjectState.WTGB) && wi.getFinishername() != null && !wi.getReceivername().equals(wi.getFinishername())){
					di.remove();
				}
			}
			res.setData(data);
			res.setFlag(true);
		}catch(Exception e){
			res.setFlag(false);
			res.setMessage(e.getMessage());
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public Result<List<WorkItem>> getWorkItemUsers(WorkItem wi) {
		Result<List<WorkItem>> res = new Result<>();
		if(wi == null || StringUtil.isNullOrEmpty(wi.getBizobjectid()) || StringUtil.isNullOrEmpty(wi.getActivitycode())){
			res.setFlag(false);
			res.setMessage("查询条件不足");
			return res;
		}
		try{
			List<WorkItem> data = workItemMapper.selectWorkItemUsers(wi);
			res.setFlag(true);
			res.setData(data);
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage());
			res.setFlag(false);
			res.setMessage(e.getMessage());
		}
 		return res;
	}

	@Override
	public Result<List<WorkItem>> selectWorkItemJd(String bizobjectid) {
		Result<List<WorkItem>> res = new Result<>();
		try{
			List<WorkItem> data = workItemMapper.selectWorkItemJd(bizobjectid);
			res.setFlag(true);
			res.setData(data);
		}catch(Exception e){
			res.setFlag(false);
			res.setMessage(e.getMessage());
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public Result<String> updateWorkItemClr(WorkItem wi,User user) {
		Result<String> res = new Result<>();
		try{
			//获取已经存在的任务节点，将其设为取消状态
			List<WorkItem> wis = workItemMapper.selectBySelective(wi);
			if(wis != null && !wis.isEmpty()){
				for(WorkItem work : wis){
					work.setUpdatetime(new Date());
					work.setState(ProjectState.CANCEL);
					workItemMapper.updateByPrimaryKeySelective(work);
				}
			}
			String activityCode = wi.getActivitycode();
			if(activityCode.equals(ProjectState.WTPD) || activityCode.equals(ProjectState.WTGB)){
				wi.setEitherorall(ProjectState.EITHER);
			}else{
				wi.setEitherorall(ProjectState.ALL);
			}
			//获取问题信息
			ProjectIssueManage project = projectMapper.selectByPrimaryKey(wi.getBizobjectid());
			if(wi.getActivitycode().equals(ProjectState.WTPD) || wi.getActivitycode().equals(ProjectState.WTCL) || wi.getActivitycode().equals(ProjectState.WTQR)){
				project.setState(ProjectState.START);
			}else if(wi.getActivitycode().equals(ProjectState.WTGB)){
				project.setState(ProjectState.DOWN);
			}
			project.setUpdatetime(new Date());
			project.setUpdateby(user.getObjectid());
			projectMapper.updateByPrimaryKeySelective(project);
			if(project == null || project.getObjectid() == null){
				res.setFlag(false);
				res.setMessage("流程不存在");
				return res;
			}
			if(project.getFqr() == null){
				res.setFlag(false);
				res.setMessage("流程发起人不存在");
				return res;
			}
			String receivername = wi.getReceivername();
			//搜索用户
			List<User> users = userMapper.selectByName(receivername);
			for(User u : users){
				//生成任务
				String oid = instanceMapper.getID();
				wi.setCreatetime(new Date());
				wi.setObjectid(oid);
				wi.setReceiver(u.getObjectid());
				wi.setReceivetime(new Date());
				wi.setState(ProjectState.START);
				workItemMapper.insertSelective(wi);
				String title = wi.getActivityname().substring(2);
				//发送微信消息
				NewsMessage nm = new NewsMessage();
				Articles articles = new Articles();
				articles.title = "您有新的待"+title+"问题";
				articles.description = "[项目编号:" + project.getXmbh() + "][产品名称:" + project.getCpmc() + "][流水号:"
						+ project.getLsh() + "][发起人:" + project.getFqr().getXm() + "]";
				String url = "http://bpm.mesnac.com:8087/Feedback/"+wi.getActivitycode()+"/"+wi.getActivitycode()+".do?objectid="
						+ project.getObjectid() + "&workitemid=" + oid;
				url = PathUtil.getWXUrl(url);
				articles.url = url;
				nm.news.articles.add(articles);
				nm.touser = u.getZh();
				nm.msgtype = "news";
				nm.agentid = 27;
				WXService.sendNewsMessage(nm);
			}
			if(activityCode.equals(ProjectState.WTCL)){
				project.setZrr(users.get(0).getObjectid());
			}
			res.setFlag(true);
			res.setData("调整成功！");
		}catch(Exception e){
			e.printStackTrace();
			res.setFlag(false);
			res.setMessage(e.getMessage());
		}
		
		return res;
	}

	@Override
	public Result<String> updateBySelective(WorkItem wi) {
		Result<String> res = new Result<>();
		try{
			wi.setState(ProjectState.START);
			List<WorkItem> wis = workItemMapper.selectBySelective(wi);
			if(wis != null && !wis.isEmpty()){
				for(WorkItem work : wis){
					work.setUpdatetime(new Date());
					work.setState(ProjectState.CANCEL);
					workItemMapper.updateByPrimaryKeySelective(work);
				}
			}
			res.setFlag(true);
			res.setData("修改成功!");
		}catch(Exception e){
			e.printStackTrace();
			res.setFlag(false);
			res.setMessage(e.getMessage());
		}
		return res;
	}

	@Override
	public Result<List<WorkItem>> getWorkItemBySelective(WorkItem wi) {
		Result<List<WorkItem>> res = new Result<>();
		try{
			List<WorkItem> data = workItemMapper.selectBySelective(wi);
			res.setData(data);
			res.setFlag(true);
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage());
			res.setFlag(false);
			res.setMessage(e.getMessage());
		}
		return res;
	}

}
