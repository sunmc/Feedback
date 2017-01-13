package com.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bean.Post;
import com.bean.ProjectIssueManage;
import com.bean.ProjectListItem;
import com.bean.ProjectState;
import com.bean.User;
import com.bean.WorkItem;
import com.bean.Wtdata;
import com.service.IPostService;
import com.service.IProjectIssueManageService;
import com.service.IWorkItemService;
import com.service.IWtdataService;
import com.util.bean.Common;
import com.util.bean.Result;

@Controller
@RequestMapping("wtpd")
public class A1WTPDController extends BaseController{

	@Autowired
	IProjectIssueManageService projectService;
	@Autowired
	private IWorkItemService workItemServie;
	@Autowired
	private IPostService postService;
	@Autowired
	private IWtdataService wtdataService;
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	@RequestMapping
	public ModelAndView index(HttpSession session){
		User user = (User)session.getAttribute("user");
		ProjectListItem pli = new ProjectListItem();
		pli.setActivitycode(ProjectState.WTPD);
		pli.setReceiver(user.getObjectid());
		pli.setQrstate(ProjectState.START);
		Result<List<ProjectListItem>> res = projectService.selectTaskByUserActivity(pli);
		ModelAndView model = null;
		if(res.isFlag()){
			model = new ModelAndView("mobile/a1wtpd/wtpdlist");
			model.addObject("items", res.getData());
		}else{
			model = new ModelAndView("mobile/error");
			model.addObject("error", res.getMessage());
		}
		return model;
	}
	
	@RequestMapping("wtpd")
	public ModelAndView wtpd(String objectid, String workitemid,HttpSession session){ 
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
				if(wi.getState() > ProjectState.DOING || !wi.getActivitycode().equals(ProjectState.WTPD)){//已完成的任务直接打开问题明细页
					model  = new ModelAndView("mobile/a6wtcx/wtcx");
					model.addObject("project", project);
					return model;
				}else{
					Result<List<Post>> posts = postService.getPostByCode(Common.xmjl); 
					Result<List<Wtdata>> wtlb = wtdataService.selectByFlag("问题类别");
					Result<List<Wtdata>> zrlb = wtdataService.selectByFlag("责任类别");
					Result<List<Wtdata>> cplb = wtdataService.selectByFlag("产品类别");
					Result<List<Wtdata>> btmc = wtdataService.selectByFlag("部套名称");
					Result<List<Wtdata>> xmjd = wtdataService.selectByFlag("项目阶段");
					Result<List<Wtdata>> jjcd = wtdataService.selectByFlag("紧急程度");
					model = new ModelAndView("mobile/a1wtpd/wtpd");
					model.addObject("project", project);
					model.addObject("xmjls", posts.getData());
					model.addObject("wtlbs", wtlb.getData());
					model.addObject("zrlbs", zrlb.getData());
					model.addObject("cplbs", cplb.getData());
					model.addObject("btmcs", btmc.getData());
					model.addObject("xmjds", xmjd.getData());
					model.addObject("jjcds", jjcd.getData());
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
	@ResponseBody
	@RequestMapping("wtpdlist")
	public Result<List<ProjectListItem>> wtpdlist(HttpSession session){
		User user = (User)session.getAttribute("user");
		ProjectListItem pli = new ProjectListItem();
		pli.setActivitycode(ProjectState.WTPD);
		pli.setReceiver(user.getObjectid());
		pli.setQrstate(ProjectState.START);
		Result<List<ProjectListItem>> res = projectService.selectTaskByUserActivity(pli);
		return res;
	}
	@RequestMapping("submit")
	public ModelAndView submit(ProjectIssueManage project, HttpSession session){
		ModelAndView model = null;
		User user = (User)session.getAttribute("user");
		if(project.getZrr() == null || project.getZrr().length() < 36){
			model = new ModelAndView("redirect:/wtpd.do？objectid="+project.getObjectid());
			return model;
		}
		Result<ProjectIssueManage> res = projectService.wtpd(project,user);
		if(res.isFlag()){
			model  = new ModelAndView("mobile/a1wtpd/wtpdlist");
			model.addObject("objectid", res.getData().getObjectid());
		}else{
			model  = new ModelAndView("mobile/error");
			model.addObject("error", res.getMessage());
		}
		return model;
		
	}
	@RequestMapping("wtpd-pc")
	public ModelAndView wtpdpc(HttpSession session){
		ModelAndView model = new ModelAndView("pc/a2wtpd/wtpd");
		User user = (User)session.getAttribute("user");
		WorkItem wi = new WorkItem();
		wi.setReceiver(user.getObjectid());
		wi.setState(ProjectState.START);
		wi.setActivitycode(ProjectState.WTPD);
		Result<List<WorkItem>> res = workItemServie.getWorkItemBySelective(wi);
		Result<List<Wtdata>> wtlb = wtdataService.selectByFlag("问题类别");
		Result<List<Wtdata>> zrlb = wtdataService.selectByFlag("责任类别");
		Result<List<Wtdata>> cplb = wtdataService.selectByFlag("产品类别");
		Result<List<Wtdata>> btmc = wtdataService.selectByFlag("部套名称");
		Result<List<Wtdata>> xmjd = wtdataService.selectByFlag("项目阶段");
		Result<List<Wtdata>> jjcd = wtdataService.selectByFlag("紧急程度");
		if(res.isFlag()){
			model.addObject("works", res.getData());
			model.addObject("wtlbs", wtlb.getData());
			model.addObject("zrlbs", zrlb.getData());
			model.addObject("cplbs", cplb.getData());
			model.addObject("btmcs", btmc.getData());
			model.addObject("xmjds", xmjd.getData());
			model.addObject("jjcds", jjcd.getData());
		}else{
			model = new ModelAndView("mobile/error");
			model.addObject("error", res.getMessage());
		}
		return model;
	}
}
