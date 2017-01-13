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
import com.bean.WorkItem;
import com.service.IProjectIssueManageService;
import com.service.IWorkItemService;
import com.util.StringUtil;
import com.util.bean.Result;

@Controller
@RequestMapping("wtfx")
public class A2WTFXController extends BaseController{

	@Autowired
	private IProjectIssueManageService projectService;

	@Autowired
	private IWorkItemService workItemServie;
	
	@RequestMapping
	public ModelAndView index(HttpSession session){
		User user = (User)session.getAttribute("user");
		ProjectListItem pli = new ProjectListItem();
		pli.setActivitycode(ProjectState.WTFX);
		pli.setReceiver(user.getObjectid());
		pli.setQrstate(ProjectState.START);
		Result<List<ProjectListItem>> res = projectService.selectTaskByUserActivity(pli);
		ModelAndView model = null;
		if(res.isFlag()){
			model = new ModelAndView("mobile/a2wtfx/wtfxlist");
			model.addObject("items", res.getData());
		}else{
			model = new ModelAndView("mobile/error");
			model.addObject("error", res.getMessage());
		}
		return model;
	}
	
	@RequestMapping("wtfx")
	public ModelAndView wtfx(String objectid, String workitemid,HttpSession session){
		ModelAndView model = null;
		//将当前用户要处理的节点id存放
		User user = (User)session.getAttribute("user");
		user.setWorkItemId(workitemid);
		session.setAttribute("user", user);
		//获取工作任务，检验是否已完成
		Result<WorkItem> wres = workItemServie.getByObjectId(workitemid);
		if(wres.isFlag()){
			Result<ProjectIssueManage> res = projectService.selectByObjectId(objectid);
			ProjectIssueManage project = res.getData();
			WorkItem wi = wres.getData();
			if(res.isFlag()){
				if(wi.getState() > ProjectState.DOING){//已完成的任务直接打开问题明细页
					model  = new ModelAndView("mobile/a6wtgz/wtgz");
					model.addObject("project", project);
					return model;
				}else{
					model = new ModelAndView("mobile/a2wtfx/wtfx");
					model.addObject("project", project);
				}
			}else{
				model  = new ModelAndView("mobile/error");
				model.addObject("error", res.getMessage());
				return model;
			}
		}else{
			model  = new ModelAndView("mobile/error");
			model.addObject("error", wres.getMessage());
			return model;
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
				model = new ModelAndView("mobile/success");
				model.addObject("objectid", res.getData().getObjectid());
			}else{
				model  = new ModelAndView("mobile/error");
				model.addObject("error", res.getMessage());
			}
		}else{
			Result<ProjectIssueManage> res = projectService.selectByObjectId(project.getObjectid());
			if(res.isFlag()){
				model  = new ModelAndView("mobile/a2wtfx/wtfx");
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
