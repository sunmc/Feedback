package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bean.ProjectListItem;
import com.bean.WorkItem;
import com.service.IProjectIssueManageService;
import com.service.IWorkItemService;
import com.util.StringUtil;
import com.util.bean.Result;

@RequestMapping("wtcx")
@Controller
public class A7WTCXController  extends BaseController{

	@Autowired
	private IProjectIssueManageService projectService;
	@Autowired
	private IWorkItemService workItemService;
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
	
	@RequestMapping("wtzt")
	public ModelAndView workFlowStatus(String objectid){
		ModelAndView model = null;
		if(StringUtil.isNullOrEmpty(objectid)){
			model = new ModelAndView("mobile/error");
			model.addObject("error", "未找到流程");
			return model;
		}
		Result<List<WorkItem>> res = workItemService.getWorkFlowStatus(objectid);
		if(res.isFlag()){
			model = new ModelAndView("mobile/a7wtcx/wtzt");
			model.addObject("workitems", res.getData());
		}else{
			model = new ModelAndView("mobile/error");
			model.addObject("error", res.getMessage());
			return model;
		}
		return model;
	}
}
