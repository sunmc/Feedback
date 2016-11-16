package com.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bean.ProjectIssueManage;
import com.bean.ProjectListItem;
import com.bean.ProjectState;
import com.bean.User;
import com.service.IProjectIssueManageService;
import com.util.StringUtil;
import com.util.bean.Result;

@Controller
@RequestMapping("wtgb")
public class A5WTGBController extends BaseController {

	@Autowired
	private IProjectIssueManageService projectService;

	@RequestMapping()
	public ModelAndView index(HttpSession session){
		User user = (User)session.getAttribute("user");
		Result<List<ProjectListItem>> res = projectService.selectTaskByUserActivity(ProjectState.WTGB, user.getObjectid(), ProjectState.START);
		ModelAndView model = null;
		if(res.isFlag()){
			model = new ModelAndView("mobile/a5wtgb/wtgblist");
			model.addObject("items", res.getData());
		}else{
			model = new ModelAndView("mobile/error");
			model.addObject("error", res.getMessage());
		}
		return model;
	}

	@RequestMapping("wtgb")
	public ModelAndView wtgn(String objectid, String workitemid, HttpSession session) {
		// 将当前用户要处理的节点id存放
		User user = (User) session.getAttribute("user");
		if (!StringUtil.isNullOrEmpty(workitemid)) {
			user.setWorkItemId(workitemid);
		}
		session.setAttribute("user", user);
		Result<ProjectIssueManage> res = projectService.selectByObjectId(objectid);
		ModelAndView model = null;
		if (res.isFlag()) {
			model = new ModelAndView("mobile/a5wtgb/wtgb");
			model.addObject("project", res.getData());
		} else {
			model = new ModelAndView("mobile/error");
			model.addObject("error", res.getMessage());
		}
		return model;
	}
	
	@RequestMapping("submit")
	public ModelAndView submit(ProjectIssueManage project, HttpSession session){
		ModelAndView model = null;
		User user = (User)session.getAttribute("user");
		Result<ProjectIssueManage> res = projectService.wtgb(project,user);
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
