package com.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bean.ProjectIssueManage;
import com.bean.User;
import com.service.IProjectIssueManageService;
import com.util.StringUtil;
import com.util.bean.Result;

@Controller
@RequestMapping("wtjj")
public class WTJJController extends BaseController{

	@Autowired
	private IProjectIssueManageService projectService;
	
	@RequestMapping
	public ModelAndView index(){
		return new ModelAndView("mobile/wtjj/wtjjlist");
	}
	@RequestMapping("wtjj")
	public ModelAndView jjwt(String objectid, String workitemid,HttpSession session){ 
		//将当前用户要处理的节点id存放
		User user = (User)session.getAttribute("user");
		if(!StringUtil.isNullOrEmpty(workitemid)){
			user.setWorkItemId(workitemid);
		}
		session.setAttribute("user", user);
		Result<ProjectIssueManage> res = projectService.selectByObjectId(objectid);
		ModelAndView model = null;
		if(res.isFlag()){
			model  = new ModelAndView("mobile/wtjj/wtjj");
			model.addObject("project", res.getData());
		}else{
			model  = new ModelAndView("mobile/error");
			model.addObject("error", res.getMessage());
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
			model  = new ModelAndView("mobile/wtjj/wtfp");
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
			
		}else{
			model  = new ModelAndView("mobile/error");
			model.addObject("error", res.getMessage());
		}
		return model;
	}
	@RequestMapping("submitjj")
	public ModelAndView submitjj(ProjectIssueManage project, HttpSession session){
		ModelAndView model = null;
		User user = (User)session.getAttribute("user");
		if(project.getZrr() == null || project.getZrr().length() < 36){
			model = new ModelAndView("redirect:/wtjj/wtjj.do?objectid="+project.getObjectid());
			return model;
		}
		Result<ProjectIssueManage> res = projectService.wtjj(project,user);
		if(res.isFlag()){
			
		}else{
			model  = new ModelAndView("mobile/error");
			model.addObject("error", res.getMessage());
		}
		return model;
	}

}
