package com.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bean.ProjectIssueManage;
import com.bean.ProjectListItem;
import com.bean.ProjectState;
import com.bean.User;
import com.bean.WorkItem;
import com.service.IProjectIssueManageService;
import com.service.IWorkItemService;
import com.util.StringUtil;
import com.util.bean.Result;

@Controller
@RequestMapping("wtqr")
public class A4WTQRController extends BaseController{

	@Autowired
	private IProjectIssueManageService projectService;
	@Autowired
	private IWorkItemService workItemService;
	@RequestMapping
	public ModelAndView index(HttpSession session){
		User user = (User)session.getAttribute("user");
		ProjectListItem pli = new ProjectListItem();
		pli.setActivitycode(ProjectState.WTQR);
		pli.setReceiver(user.getObjectid());
		pli.setQrstate(ProjectState.START);
		Result<List<ProjectListItem>> res = projectService.selectTaskByUserActivity(pli);
		ModelAndView model = null;
		if(res.isFlag()){
			model = new ModelAndView("mobile/a4wtqr/wtqrlist");
			model.addObject("items", res.getData());
		}else{
			model = new ModelAndView("mobile/error");
			model.addObject("error", res.getMessage());
		}
		return model;
	}
	@ResponseBody
	@RequestMapping("wtqrlist")
	public Result<List<ProjectListItem>> wtqrlist(HttpSession session){
		User user = (User)session.getAttribute("user");
		ProjectListItem pli = new ProjectListItem();
		pli.setActivitycode(ProjectState.WTQR);
		pli.setReceiver(user.getObjectid());
		pli.setQrstate(ProjectState.START);
		Result<List<ProjectListItem>> res = projectService.selectTaskByUserActivity(pli);
		return res;
	}
	@RequestMapping("wtqr")
	public ModelAndView wtqr(String objectid, String workitemid,HttpSession session){ 
		ModelAndView model = null;
		//将当前用户要处理的节点id存放
		User user = (User)session.getAttribute("user");
		if(!StringUtil.isNullOrEmpty(workitemid)){
			user.setWorkItemId(workitemid);
		}
		session.setAttribute("user", user);
		//获取工作任务，检验是否已完成
		Result<WorkItem> wres = workItemService.getByObjectId(workitemid);
		if(wres.isFlag()){
			Result<ProjectIssueManage> res = projectService.selectByObjectId(objectid);
			ProjectIssueManage project = res.getData();
			WorkItem wi = wres.getData();
			if(res.isFlag()){
				if(wi.getState() > ProjectState.DOING || !wi.getActivitycode().equals(ProjectState.WTQR)){//已完成的任务直接打开问题明细页
					model  = new ModelAndView("mobile/a6wtcx/wtcx");
					model.addObject("project", project);
					return model;
				}else{
					model  = new ModelAndView("mobile/a4wtqr/wtqr");
					model.addObject("project", project);
				}
			}else{
				model  = new ModelAndView("mobile/error");
				model.addObject("error", res.getMessage());
			}
			
		}
		return model;
	}
	@RequestMapping("submit")
	public ModelAndView submit(ProjectIssueManage project, HttpSession session){
		ModelAndView model = null;
		User user = (User)session.getAttribute("user");
		Result<ProjectIssueManage> res = projectService.wtqr(project,user);
		if(res.isFlag()){
			model = new ModelAndView("mobile/a4wtqr/wtqrlist");
			model.addObject("objectid", res.getData().getObjectid());
		}else{
			model = new ModelAndView("mobile/error");
			model.addObject("error", res.getMessage());
		}
		return model;
	}
}
