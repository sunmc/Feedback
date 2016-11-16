package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bean.ProjectListItem;
import com.service.IProjectIssueManageService;
import com.util.bean.Result;

@RequestMapping("wtcx")
@Controller
public class A7WTCXController  extends BaseController{

	@Autowired
	private IProjectIssueManageService projectService;
	@RequestMapping()
	public ModelAndView index(){
		return new ModelAndView("mobile/wtcx/wtcx");
	}
	
	@ResponseBody
	@RequestMapping("search")
	public Result<List<ProjectListItem>> searchPli(ProjectListItem pli){
		Result<List<ProjectListItem>> res = projectService.searchPli(pli);
		return res;
	}
}
