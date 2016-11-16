package com.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bean.ProjectIssueManage;
import com.bean.ProjectListItem;
import com.bean.ProjectState;
import com.bean.User;
import com.bean.WorkItem;
import com.service.IProjectIssueManageService;
import com.service.IWorkItemService;
import com.util.bean.Result;

@Controller
@RequestMapping("wtpd")
public class A1WTPDController extends BaseController{

	@Autowired
	IProjectIssueManageService projectService;
	@Autowired
	private IWorkItemService workItemServie;
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	@RequestMapping
	public ModelAndView index(HttpSession session){
		User user = (User)session.getAttribute("user");
		Result<List<ProjectListItem>> res = projectService.selectTaskByUserActivity(ProjectState.WTPD, user.getObjectid(), ProjectState.START);
		ModelAndView model = null;
		if(res.isFlag()){
			model = new ModelAndView("mobile/a1wtpd/wtpdlist");
			model.addObject("items", res.getData());
		}else{
			model = new ModelAndView("mobile/error");
			model.addObject("error", res.getMessage());
		}
		return model;
	}
	
	@RequestMapping("wtpd")
	public ModelAndView wtpd(String objectid, String workitemid,HttpSession session){ 
		ModelAndView model = null;
		//将当前用户要处理的节点id存放
		User user = (User)session.getAttribute("user");
		user.setWorkItemId(workitemid);
		session.setAttribute("user", user);
		//获取工作任务，检验是否已完成
		Result<WorkItem> wres = workItemServie.getByObjectId(workitemid);
		if(wres.isFlag()){
			Result<ProjectIssueManage> res = projectService.selectByObjectId(objectid);
			ProjectIssueManage project = res.getData();
			WorkItem wi = wres.getData();
			if(res.isFlag()){
				if(wi.getState() > ProjectState.DOING){//已完成的任务直接打开问题明细页
					model  = new ModelAndView("mobile/a6wtgz/wtgz");
					model.addObject("project", project);
					return model;
				}else{
					model = new ModelAndView("mobile/a1wtpd/wtpd");
					model.addObject("project", project);
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
	@RequestMapping("submit")
	public ModelAndView submit(ProjectIssueManage project, HttpSession session){
		ModelAndView model = null;
		User user = (User)session.getAttribute("user");
		if(project.getZrr() == null || project.getZrr().length() < 36){
			model = new ModelAndView("redirect:/wtpd.do？objectid="+project.getObjectid());
			return model;
		}
		Result<ProjectIssueManage> res = projectService.wtpd(project,user);
		if(res.isFlag()){
			model  = new ModelAndView("mobile/success");
			model.addObject("objectid", res.getData().getObjectid());
		}else{
			model  = new ModelAndView("mobile/error");
			model.addObject("error", res.getMessage());
		}
		return model;
		
	}
}
