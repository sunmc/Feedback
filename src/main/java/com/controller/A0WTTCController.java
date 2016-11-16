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
@RequestMapping("wttc")
public class A0WTTCController extends BaseController{

	@Autowired
	private IProjectIssueManageService projectService;
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping
	public ModelAndView index(String code, String state,HttpSession session){
		
		ModelAndView mv = new ModelAndView("mobile/a0wttc/wttc");
		return mv;
	}
	@RequestMapping("submit")
	public ModelAndView submit(ProjectIssueManage project, HttpSession session){
		User user = (User)session.getAttribute("user");
		Result<ProjectIssueManage> res = projectService.insert(project, user);
		ModelAndView model = null;
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
