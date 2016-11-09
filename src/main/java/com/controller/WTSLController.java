package com.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bean.ProjectIssueManage;
import com.bean.User;
import com.service.IProjectIssueManageService;
import com.util.bean.Result;

@Controller
@RequestMapping("wtsl")
public class WTSLController {

	@Autowired
	IProjectIssueManageService projectService;
	private Logger log = LoggerFactory.getLogger(this.getClass());
	@RequestMapping
	public ModelAndView index(){
		return new ModelAndView("mobile/wtsl/wtsllist");
	}
	
	@RequestMapping("wtsl")
	public ModelAndView wtpd(String objectid, String workitemid,HttpSession session){ 
		//将当前用户要处理的节点id存放
		User user = (User)session.getAttribute("user");
		user.setWorkItemId(workitemid);
		session.setAttribute("user", user);
		Result<ProjectIssueManage> res = projectService.selectByObjectId(objectid);
		ModelAndView model = null;
		if(res.isFlag()){
			model  = new ModelAndView("mobile/wtsl/wtsl");
			model.addObject("project", res.getData());
		}else{
			model  = new ModelAndView("mobile/error");
			model.addObject("error", res.getMessage());
		}
		return model;
	}
	@RequestMapping("submit")
	public ModelAndView submit(ProjectIssueManage project, HttpSession session){
		ModelAndView model = null;
		User user = (User)session.getAttribute("user");
		if(project.getZrr() == null || project.getZrr().length() < 36){
			model = new ModelAndView("redirect:/wtsl.do？objectid="+project.getObjectid());
			return model;
		}
		Result<ProjectIssueManage> res = projectService.wtpd(project,user);
		if(res.isFlag()){
			
		}else{
			model  = new ModelAndView("mobile/error");
			model.addObject("error", res.getMessage());
		}
		return model;
		
	}
}
