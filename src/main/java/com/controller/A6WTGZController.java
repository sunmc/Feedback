package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bean.ProjectIssueManage;
import com.service.IProjectIssueManageService;
import com.util.bean.Result;

@Controller
@RequestMapping("wtgz")
public class A6WTGZController  extends BaseController{

	@Autowired
	private IProjectIssueManageService projectService;
	@RequestMapping
	public ModelAndView index(){
		return new ModelAndView("mobile/a6wtgz/wtgzlist");
	}
	
	@RequestMapping("wtgz")
	public ModelAndView wtgz(String objectid){
		ModelAndView model = null;
		Result<ProjectIssueManage> res = projectService.selectByObjectId(objectid);
		if(res.isFlag()){
			model = new ModelAndView("mobile/a6wtgz/wtgz");
			model.addObject("project", res.getData());
		}else{
			model = new ModelAndView("mobile/error");
			model.addObject("error", res.getMessage());
		}
		return model;
	}
}
