package com.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bean.OverTaskParam;
import com.bean.Post;
import com.bean.ProjectIssueManage;
import com.bean.ProjectListItem;
import com.bean.TQProject;
import com.bean.User;
import com.bean.WorkItem;
import com.service.IPostService;
import com.service.IProjectIssueManageService;
import com.service.IWorkItemService;
import com.util.StringUtil;
import com.util.bean.Common;
import com.util.bean.Result;

@RequestMapping("wtcx")
@Controller
public class A7WTCXController  extends BaseController{

	@Autowired
	private IProjectIssueManageService projectService;
	@Autowired
	private IPostService postService;
	@Autowired
	private IWorkItemService workItemService;
	@RequestMapping()
	public ModelAndView index(HttpSession session){
		User user = (User)session.getAttribute("user");
		Result<List<Post>> xmjls = postService.getPostByCode(Common.xmjl);
		Result<List<Post>> zrrs = postService.getPostByCode(Common.cpfzr);
		ModelAndView model = new ModelAndView("mobile/a6wtcx/wtcxlist");
		model.addObject("xmjls", xmjls.getData());
		model.addObject("zrrs", zrrs.getData());
		model.addObject("user", user);
		return model ;
	}
	@RequestMapping("wtcx")
	public ModelAndView wtcx(String objectid){
		ModelAndView model = null;
		Result<ProjectIssueManage> res = projectService.selectByObjectId(objectid);
		if(res.isFlag()){
			model = new ModelAndView("mobile/a6wtcx/wtcx");
			model.addObject("project", res.getData());
		}else{
			model = new ModelAndView("mobile/error");
			model.addObject("error", res.getMessage());
		}
		return model;
	}
	@ResponseBody
	@RequestMapping("search")
	public Result<List<ProjectListItem>> searchPli(ProjectListItem pli){
		Result<List<ProjectListItem>> res = projectService.searchPli(pli);
		return res;
	}
	
	@RequestMapping("wtzt")
	public ModelAndView workFlowStatus(String objectid, HttpSession session){
		ModelAndView model = null;
		User user = (User)session.getAttribute("user");
		if(StringUtil.isNullOrEmpty(objectid)){
			model = new ModelAndView("mobile/error");
			model.addObject("error", "未找到流程");
			return model;
		}
		Result<List<WorkItem>> res = workItemService.getWorkFlowStatus(objectid);
		if(res.isFlag()){
			model = new ModelAndView("mobile/a6wtcx/wtzt");
			model.addObject("objectid", objectid);
			model.addObject("workitems", res.getData());
			model.addObject("user", user);
		}else{
			model = new ModelAndView("mobile/error");
			model.addObject("error", res.getMessage());
			return model;
		}
		return model;
	}
	@ResponseBody
	@RequestMapping("overtask")
	public Result<OverTaskParam> overTask(HttpSession session){
		User user = (User)session.getAttribute("user");
		Result<OverTaskParam> res = projectService.getOverTask(user.getObjectid());
		return res;
	}
	@RequestMapping("overtasky")
	public ModelAndView overTask(){
		return new ModelAndView("mobile/a6wtcx/overtask");
	}
	@RequestMapping("overtaskmx")
	public ModelAndView overTaskMx(HttpSession session, OverTaskParam otp){
		ModelAndView model;
		User user = (User)session.getAttribute("user");
		otp.setReceiver(user.getObjectid());
		Result<List<ProjectListItem>> res = projectService.getOverTask(otp);
		if(res.isFlag()){
			model = new ModelAndView("mobile/a6wtcx/overtaskmx");
			model.addObject("items", res.getData());
			if(otp.getStart() == null && otp.getEnd() == null){
				model.addObject("title","待办任务");
			}else if(otp.getStart() != null && otp.getEnd() == null){
				model.addObject("title","拖期" + otp.getStart() + "天以上任务列表");
			}else{
				model.addObject("title","拖期" + otp.getEnd() + "天内任务列表");
			}
		}else{
			model = new ModelAndView("mobile/error");
			model.addObject("error", res.getMessage());
		}
		return model;
	}
	@RequestMapping("tqwt")
	public ModelAndView tqwt(TQProject tqp, HttpSession session){
		ModelAndView model = null;
		User user = (User)session.getAttribute("user");
		tqp.setUnitcode(user.getSzbm());
		Result<TQProject> res = projectService.getTQProjectCount(tqp);
		if(res.isFlag()){
			model = new ModelAndView("mobile/a6wtcx/tqwt");
			model.addObject("tqproject", res.getData());
		}else{
			model = new ModelAndView("Mobile/error");
			model.addObject("error", res.getMessage());
		}
		return model;
	}
	@RequestMapping("tqwtmx")
	public ModelAndView tqwtmx(TQProject tqp, HttpSession session){
		ModelAndView model = null;
		User user = (User)session.getAttribute("user");
		tqp.setUnitcode(user.getSzbm());
		Result<List<ProjectListItem>> res = projectService.getTQProject(tqp);
		if(res.isFlag()){
			model = new ModelAndView("mobile/a6wtcx/tqwtmx");
			model.addObject("items", res.getData());
			String type = "";
			if(tqp.getWtstate() != null && tqp.getWtstate() == 1){
				type = "未解决问题";
			}else if(tqp.getWtstate() != null && tqp.getWtstate() == 3){
				type = "未关闭问题";
			}
			if(tqp.getStart() != null && tqp.getEnd() == null){
				model.addObject("title","拖期" + tqp.getStart() + "天以上" + type);
			}else{
				model.addObject("title","拖期" + tqp.getEnd() + "天内" + type);
			}
		}else{
			model = new ModelAndView("Mobile/error");
			model.addObject("error", res.getMessage());
		}
		return model;
	}
	@ResponseBody
	@RequestMapping("send")
	public String send(){
		projectService.sendDBTQ();
		projectService.sendTQWT();
		return "发送成功！";
	}
	@ResponseBody
	@RequestMapping("del")
	public Result<ProjectIssueManage> delwt(ProjectIssueManage project, HttpSession session){
		User user = (User)session.getAttribute("user");
		project.setDeleteflag(1);
		Result<ProjectIssueManage> res = projectService.updateByObjectId(project, user);
		return res;
	}
}
