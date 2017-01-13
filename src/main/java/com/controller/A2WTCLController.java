package com.controller;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bean.Implementation;
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
@RequestMapping("wtcl")
public class A2WTCLController extends BaseController{

	@Autowired
	private IProjectIssueManageService projectService;

	@Autowired
	private IWorkItemService workItemServie;
	@RequestMapping
	public ModelAndView index(HttpSession session){
		User user = (User)session.getAttribute("user");
		ProjectListItem pli = new ProjectListItem();
		pli.setActivitycode(ProjectState.WTCL);
		pli.setReceiver(user.getObjectid());
		pli.setQrstate(ProjectState.START);
		Result<List<ProjectListItem>> res = projectService.selectTaskByUserActivity(pli);
		ModelAndView model = null;
		if(res.isFlag()){
			model = new ModelAndView("mobile/a2wtcl/wtcllist");
			model.addObject("items", res.getData());
		}else{
			model = new ModelAndView("mobile/error");
			model.addObject("error", res.getMessage());
		}
		return model;
	}
	@ResponseBody
	@RequestMapping("wtcllist")
	public Result<List<ProjectListItem>> wtcllist(HttpSession session){
		User user = (User)session.getAttribute("user");
		ProjectListItem pli = new ProjectListItem();
		pli.setActivitycode(ProjectState.WTCL);
		pli.setReceiver(user.getObjectid());
		pli.setQrstate(ProjectState.START);
		Result<List<ProjectListItem>> res = projectService.selectTaskByUserActivity(pli);
		return res;
	}
	@RequestMapping("wtcl")
	public ModelAndView wtcl(String objectid, String workitemid,HttpSession session){
		ModelAndView model = null;
		if(StringUtil.isNullOrEmpty(workitemid) || StringUtil.isNullOrEmpty(objectid)){
			model = new ModelAndView("mobile/error");
			model.addObject("error","任务不存在");
			return model;
		}
		//将当前用户要处理的节点id存放
		User user = (User)session.getAttribute("user");
		user.setWorkItemId(workitemid);
		session.setAttribute("user", user);
		//获取工作任务，检验是否已完成
		Result<WorkItem> wres = workItemServie.getByObjectId(workitemid);
		if(wres.isFlag()){
			
			Result<ProjectIssueManage> res = projectService.selectByObjectId(objectid);
			ProjectIssueManage project = res.getData();
			//如果方案执行人为空，设默认值为当前用户
			if(StringUtil.isNullOrEmpty(project.getFazxr())){
				project.setFazxr(user.getObjectid());
				project.setUfazxr(user);
			}
			//计划完成时间默认为要求完成时间
			if(project.getJhwcsj() == null){
				project.setJhwcsj(project.getYqwcsj());
			}
			WorkItem wi = wres.getData();
			if(res.isFlag()){
				//验证任务是否是本次登录人的
				if(!wi.getReceiver().equals(user.getObjectid())){
					model  = new ModelAndView("mobile/a6wtcx/wtcx");
					model.addObject("project", project);
					return model;
				}
				if(wi.getState() > ProjectState.DOING || !wi.getActivitycode().equals(ProjectState.WTCL)){//已完成的任务直接打开问题明细页
					model  = new ModelAndView("mobile/a6wtcx/wtcx");
					model.addObject("project", project);
					return model;
				}else{
					//判断任务是问题处理还是问题整改
					if(wi.getBizobjectschemacode().equals(ProjectState.WTCL)){
						model = new ModelAndView("mobile/a2wtcl/wtcl");
						model.addObject("project", project);
						return model;
					}else{
						model = new ModelAndView("mobile/a2wtcl/wtzg");
						//剔除不属于当前用户的解决措施
						Iterator<Implementation> iterator = project.getImplementations().iterator();
						while(iterator.hasNext()){
							Implementation imp = iterator.next();
							if(!(imp.getJjcszrr() != null && imp.getJjcszrr().equals(user.getObjectid()))){
								//剔除无效任务
								iterator.remove();
							}
						}
						model.addObject("project", project);
						return model;
					}
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
	}
	
	@RequestMapping("submitcl")
	public ModelAndView submitcl(ProjectIssueManage project, HttpSession session){
		ModelAndView model = null;
		User user = (User)session.getAttribute("user");
		Result<ProjectIssueManage> res = projectService.wtcl(project, user);
		if(res.isFlag()){
			model = new ModelAndView("mobile/a2wtcl/wtcllist");
			model.addObject("objectid", res.getData().getObjectid());
		}else{
			model  = new ModelAndView("mobile/error");
			model.addObject("error", res.getMessage());
		}
		
		return model;
	}
	
	@RequestMapping("submitzg")
	public ModelAndView submitzg(ProjectIssueManage project, HttpSession session){
		ModelAndView model = null;
		User user = (User)session.getAttribute("user");
		Result<ProjectIssueManage> res = projectService.wtcl(project, user);
		if(res.isFlag()){
			model = new ModelAndView("mobile/a2wtcl/wtcllist");
			model.addObject("objectid", res.getData().getObjectid());
		}else{
			model  = new ModelAndView("mobile/error");
			model.addObject("error", res.getMessage());
		}
		return model;
	}
}
