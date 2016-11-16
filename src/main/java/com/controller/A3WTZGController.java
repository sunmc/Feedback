package com.controller;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bean.Implementation;
import com.bean.ProjectIssueManage;
import com.bean.ProjectListItem;
import com.bean.ProjectState;
import com.bean.User;
import com.bean.WorkItem;
import com.service.IProjectIssueManageService;
import com.service.IWorkItemService;
import com.util.StringUtil;
import com.util.bean.Result;

@Controller
@RequestMapping("wtzg")
public class A3WTZGController extends BaseController{

	@Autowired
	private IProjectIssueManageService projectService;
	
	@Autowired
	private IWorkItemService workItemServie;
	
	@RequestMapping
	public ModelAndView index(HttpSession session){
		User user = (User)session.getAttribute("user");
		Result<List<ProjectListItem>> res = projectService.selectTaskByUserActivity(ProjectState.WTZG, user.getObjectid(), ProjectState.START);
		ModelAndView model = null;
		if(res.isFlag()){
			model = new ModelAndView("mobile/a3wtzg/wtzglist");
			model.addObject("items", res.getData());
		}else{
			model = new ModelAndView("mobile/error");
			model.addObject("error", res.getMessage());
		}
		return model;
	}
	@RequestMapping("wtzg")
	public ModelAndView jjwt(String objectid, String workitemid,HttpSession session){ 
		ModelAndView model = null;
		//将当前用户要处理的节点id存放
		User user = (User)session.getAttribute("user");
		if(!StringUtil.isNullOrEmpty(workitemid)){
			user.setWorkItemId(workitemid);
		}
		session.setAttribute("user", user);
		//获取工作任务，检验是否已完成
		Result<WorkItem> wres = workItemServie.getByObjectId(workitemid);
		if(wres.isFlag()){
			Result<ProjectIssueManage> res = projectService.selectByObjectId(objectid);
			ProjectIssueManage project = res.getData();
			WorkItem wi = wres.getData();
			if(res.isFlag()){
				if(wi.getState() > ProjectState.DOING){//已完成的任务直接打开问题明细页
					model  = new ModelAndView("mobile/a3wtgz/wtgz");
					model.addObject("project", wres.getData());
				}else{//未完成的任务，分工作分派和工作解决，工作分派可以直接解决问题也可以在分派给他人任务，问题解决则只能解决问题
					String schema = wi.getBizobjectschemacode();
					if(StringUtil.isNullOrEmpty(schema) || schema.equals(ProjectState.WTFP)){
						model  = new ModelAndView("mobile/a3wtzg/wtfp");
						model.addObject("project", project);
						return model;
					}else{
						//解决问题直显示自己要解决的
						Iterator<Implementation> ims = res.getData().getImplementations().iterator();
						while(ims.hasNext()){
							Implementation im = ims.next();
							if(!user.getObjectid().equals(im.getJjcszrr())){
								ims.remove();
							}
						}
						model  = new ModelAndView("mobile/a3wtzg/wtzg");
						model.addObject("project", res.getData());
						return model;
					}
				}
			}else{
				model  = new ModelAndView("mobile/error");
				model.addObject("error", res.getMessage());
				return model;
			}
		}else{
			model  = new ModelAndView("mobile/error");
			model.addObject("error", wres.getMessage());
			return model;
		}
		return model;
	}
	@RequestMapping("wtfp")
	public ModelAndView gzfp(String objectid, String workitemid,HttpSession session){ 
		//将当前用户要处理的节点id存放
		User user = (User)session.getAttribute("user");
		if(!StringUtil.isNullOrEmpty(workitemid)){
			user.setWorkItemId(workitemid);
		}
		session.setAttribute("user", user);
		Result<ProjectIssueManage> res = projectService.selectByObjectId(objectid);
		ModelAndView model = null;
		if(res.isFlag()){
			//如果解决措施中有内容，则不能再分派任务
			if(res.getData().getImplementations() != null && res.getData().getImplementations().size() > 0){
				model = new ModelAndView("redirect:/wtzg/wtzg.do?objectid="+res.getData().getObjectid());
				return model;
			}
			model  = new ModelAndView("mobile/a3wtzg/wtfp");
			model.addObject("project", res.getData());
		}else{
			model  = new ModelAndView("mobile/error");
			model.addObject("error", res.getMessage());
		}
		return model;
	}
	@RequestMapping("submitfp")
	public ModelAndView submitfp(ProjectIssueManage project, HttpSession session){
		ModelAndView model = null;
		User user = (User)session.getAttribute("user");
		if(project.getImplementations() == null || project.getImplementations().size() < 1){
			model = new ModelAndView("redirect:/wtjj/wtfp.do?objectid="+project.getObjectid());
			return model;
		}
		Result<ProjectIssueManage> res = projectService.wtfp(project,user);
		if(res.isFlag()){
			model = new ModelAndView("mobile/success");
			model.addObject("objectid", res.getData().getObjectid());
		}else{
			model = new ModelAndView("mobile/error");
			model.addObject("error", res.getMessage());
		}
		return model;
	}
	@RequestMapping("submitzg")
	public ModelAndView submitjj(ProjectIssueManage project, HttpSession session){
		ModelAndView model = null;
		User user = (User)session.getAttribute("user");
		if(project.getImplementations() == null || project.getImplementations().size() < 1){
			model = new ModelAndView("redirect:/wtzg/wtzg.do?objectid="+project.getObjectid());
			return model;
		}
		Result<ProjectIssueManage> res = projectService.wtzg(project,user);
		if(res.isFlag()){
			model = new ModelAndView("mobile/success");
			model.addObject("objectid", res.getData().getObjectid());
		}else{
			model = new ModelAndView("mobile/error");
			model.addObject("error", res.getMessage());
		}
		return model;
	}

}
