package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bean.LsjData;
import com.bean.TZBGData;
import com.bean.WorkItem;
import com.service.IProjectIssueManageService;
import com.util.StringUtil;
import com.util.bean.Result;

@Controller
@RequestMapping("xgxx")
public class A8XGXXController {

	@Autowired
	private IProjectIssueManageService projectService;
	
	@RequestMapping("lsjData")
	public ModelAndView lsjData(String wth){
		ModelAndView model = null;
		if(StringUtil.isNullOrEmpty(wth)){
			model = new ModelAndView("mobile/error");
			model.addObject("error", "没有找到相关非BOM流程");
			return model;
		}
		Result<List<LsjData>> res = projectService.getLsjData(wth);
		if(res.isFlag()){
			model = new ModelAndView("mobile/a8wtxgxx/lsjData");
			model.addObject("lsjs", res.getData());
			return model;
		}else{
			model = new ModelAndView("mobile/error");
			model.addObject("error", res.getMessage());
			return model;
		}
	}
	@RequestMapping("lsjWorkItem")
	public ModelAndView lsjWorkItem(String lsh){
		ModelAndView model = null;
		if(StringUtil.isNullOrEmpty(lsh)){
			model = new ModelAndView("mobile/error");
			model.addObject("error", "没有找到相关非BOM流程");
			return model;
		}
		Result<List<WorkItem>> res = projectService.getLsjWorkItem(lsh);
		if(res.isFlag()){
			model = new ModelAndView("mobile/a8wtxgxx/lsjlczt");
			model.addObject("workitems", res.getData());
			return model;
		}else{
			model = new ModelAndView("mobile/error");
			model.addObject("error", res.getMessage());
			return model;
		}
	}
	@RequestMapping("tzbgData")
	public ModelAndView da02Data(String wth){
		ModelAndView model = null;
		if(StringUtil.isNullOrEmpty(wth)){
			model = new ModelAndView("mobile/error");
			model.addObject("error", "没有找到相关图纸变更流程");
			return model;
		}
		Result<List<TZBGData>> res = projectService.getTZBGData(wth);
		if(res.isFlag()){
			model = new ModelAndView("mobile/a8wtxgxx/tzbgData");
			model.addObject("tzbg", res.getData());
			model.addObject("wth", wth);
			return model;
		}else{
			model = new ModelAndView("mobile/error");
			model.addObject("error", res.getMessage());
			return model;
		}
	}
	@RequestMapping("tzbgWorkItem")
	public ModelAndView da02WorkItem(String lsh){
		ModelAndView model = null;
		if(StringUtil.isNullOrEmpty(lsh)){
			model = new ModelAndView("mobile/error");
			model.addObject("error", "没有找到相关图纸变更流程");
			return model;
		}
		Result<List<WorkItem>> res = projectService.getTZBGWorkItem(lsh);
		if(res.isFlag()){
			model = new ModelAndView("mobile/a8wtxgxx/tzbglczt");
			model.addObject("workitems", res.getData());
			return model;
		}else{
			model = new ModelAndView("mobile/error");
			model.addObject("error", res.getMessage());
			return model;
		}
	}
}
