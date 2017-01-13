package com.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bean.User;
import com.bean.WorkItem;
import com.service.IUserService;
import com.service.IWorkItemService;
import com.util.StringUtil;
import com.util.bean.Result;

@Controller
@RequestMapping("workitem")
public class WorkItemController {

	@Autowired
	private IUserService userService;
	@Autowired
	private IWorkItemService workItemService;
	//根据流程id和节点名称获取节点处理人
	@ResponseBody
	@RequestMapping("clrs")
	public Result<List<WorkItem>> getWorkItemUsers(WorkItem wi){
		Result<List<WorkItem>> res = workItemService.getWorkItemUsers(wi);
		return res;
	}
	@ResponseBody
	@RequestMapping("lcjd")
	public Result<List<WorkItem>> getWorkItemJd(String bizobjectid){
		Result<List<WorkItem>> res = workItemService.selectWorkItemJd(bizobjectid);
		return res;
	}
	
}
