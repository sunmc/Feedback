package com.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.bean.ProjectIssueManage;
import com.bean.User;
import com.bean.WorkItem;
import com.service.IProjectIssueManageService;
import com.service.IUserService;
import com.service.IWorkItemService;
import com.util.PathUtil;
import com.util.StringUtil;
import com.util.UploadUtil;
import com.util.bean.Result;

@Controller
@RequestMapping("admin")
public class AdminController extends BaseController{

	@Autowired
	private IUserService userService;
	@Autowired
	private IWorkItemService workItemService;
	@Autowired
	private IProjectIssueManageService projectService;
	@RequestMapping
	public ModelAndView index(HttpSession session){
		ModelAndView model = null;
		User user = (User)session.getAttribute("user");
		if(user.getRole() != null && user.getRole() == 1){
			model = new ModelAndView("mobile/admin/admin");
			model.addObject("user",user);
		}else{
			model = new ModelAndView("mobile/error");
			model.addObject("error", "权限不足！");
		}
		return model;
	}
	
	@RequestMapping("adjustView")
	public ModelAndView adjustView(String objectid, HttpSession session){
		ModelAndView model = null;
		User user = (User)session.getAttribute("user");
		if(StringUtil.isNullOrEmpty(objectid)){
			model = new ModelAndView("mobile/error");
			model.addObject("error", "查询条件不足");
			return model;
		}
		if(userService.ifAdmin(user.getObjectid())){
			model = new ModelAndView("mobile/admin/adjust");
			model.addObject("objectid", objectid);
		}else{
			model = new ModelAndView("mobile/error");
			model.addObject("error", "您没有权限访问本页面");
		}
		return model;
	}
	//修改流程节点处理人
	@ResponseBody
	@RequestMapping("adjust")
	public Result<String> updateWorkItemClr(WorkItem wi, HttpSession session){
		User user = (User)session.getAttribute("user");
		Result<String> res = workItemService.updateWorkItemClr(wi, user);
		return res;
	}
	//取消活动节点
	@ResponseBody
	@RequestMapping("cancelWork")
	public Result<String> cancelWork(WorkItem wi){
		Result<String> res = workItemService.updateBySelective(wi);
		return res;
	}
	@RequestMapping("wtdrView")
	public ModelAndView wtdrView(){
		ModelAndView model = new ModelAndView("mobile/admin/wtdr");
		return model;
	}
	//导入问题
	@RequestMapping("wtdr")
	public ModelAndView wtdr(@RequestParam(value = "file", required = true) MultipartFile file,
			HttpSession session){
		ModelAndView model = null;
		User user = (User)session.getAttribute("user");
		Result<String> up = UploadUtil.uploadFileToPath(user.getZh(), file, PathUtil.DRWJ_PATH);
		if(up.isFlag()){
			Result<List<ProjectIssueManage>> res = projectService.importProject(up.getData());
			if(res.isFlag()){
				model = new ModelAndView("mobile/admin/success");
				model.addObject("items", res.getData());
				model.addObject("message", "导入成功！");
			}else{
				model = new ModelAndView("mobile/error");
				model.addObject("error", res.getMessage());
			}
		}else{
			model = new ModelAndView("mobile/error");
			model.addObject("error", "文件上传失败，请重新上传文件！");
			
		}
		return model;
	}
}
