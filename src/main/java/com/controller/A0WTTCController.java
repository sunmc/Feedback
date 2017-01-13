package com.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bean.Post;
import com.bean.ProjectIssueManage;
import com.bean.User;
import com.service.IPostService;
import com.service.IProjectIssueManageService;
import com.util.bean.Common;
import com.util.bean.Result;

@Controller
@RequestMapping("wttc")
public class A0WTTCController extends BaseController{

	@Autowired
	private IProjectIssueManageService projectService;
	@Autowired
	private IPostService postService;
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping
	public ModelAndView index(String code, String state,HttpSession session){
		Result<List<Post>> ps = postService.getPostByCode(Common.xmjl);
		ModelAndView mv = new ModelAndView("mobile/a0wttc/wttc");
		mv.addObject("xmjls", ps.getData());
		return mv;
	}
	@RequestMapping("submit")
	public ModelAndView submit(ProjectIssueManage project, HttpSession session){
		User user = (User)session.getAttribute("user");
		Result<ProjectIssueManage> res = projectService.insert(project, user);
		ModelAndView model = null;
		if(res.isFlag()){
			model = new ModelAndView("mobile/a6wtcx/wtcxlist");
			model.addObject("project", res.getData());
		}else{
			model = new ModelAndView("mobile/error");
			model.addObject("error", res.getMessage());
		}
		return model;
	}
}
