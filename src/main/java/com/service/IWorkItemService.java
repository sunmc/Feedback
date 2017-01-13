package com.service;

import java.util.List;

import com.bean.User;
import com.bean.WorkItem;
import com.util.bean.Result;

public interface IWorkItemService {

	public Result<WorkItem> getByObjectId(String objectid);
	
	public Result<WorkItem> updateByObjectId(WorkItem wi);
	
	public Result<WorkItem> insert(WorkItem wi);
	
	public Result<List<WorkItem>> getWorkFlowStatus(String bizobjectid);
	
	public Result<List<WorkItem>> getWorkItemUsers(WorkItem wi);
	
	public Result<List<WorkItem>> selectWorkItemJd(String bizobjectid);
	
	public Result<String> updateWorkItemClr(WorkItem wi,User user);
	
	public Result<String> updateBySelective(WorkItem wi);
	
	public Result<List<WorkItem>> getWorkItemBySelective(WorkItem wi);
}
