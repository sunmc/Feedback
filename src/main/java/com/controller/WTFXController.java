package com.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bean.ProjectIssueManage;
import com.bean.User;
import com.service.IProjectIssueManageService;
import com.util.StringUtil;
import com.util.bean.Result;

@Controller
@RequestMapping("wtfx")
public class WTFXController {

	@Autowired
	private IProjectIssueManageService projectService;
	@RequestMapping
	public ModelAndView index(){
		return new ModelAndView("mobile/wtfx/wtfxlist");
	}
	
	@RequestMapping("wtfx")
	public ModelAndView wtfx(String objectid, String workitemid,HttpSession session){
		//将当前用户要处理的节点id存放
		User user = (User)session.getAttribute("user");
		user.setWorkItemId(workitemid);
		session.setAttribute("user", user);
		Result<ProjectIssueManage> res = projectService.selectByObjectId(objectid);
		ModelAndView model = null;
		if(res.isFlag()){
			model  = new ModelAndView("mobile/wtfx/wtfx");
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
		if(!StringUtil.isNullOrEmpty(project.getFazxr())){
			Result<ProjectIssueManage> res = projectService.wtfx(project, user);
			if(res.isFlag()){
				
			}else{
				model  = new ModelAndView("mobile/error");
				model.addObject("error", res.getMessage());
			}
		}else{
			Result<ProjectIssueManage> res = projectService.selectByObjectId(project.getObjectid());
			if(res.isFlag()){
				model  = new ModelAndView("mobile/wtfx/wtfx");
				model.addObject("error", "方案执行人为必填项");
				model.addObject("project", res.getData());
			}else{
				model  = new ModelAndView("mobile/error");
				model.addObject("error", res.getMessage());
			}
		}
		
		return model;
	}
}
