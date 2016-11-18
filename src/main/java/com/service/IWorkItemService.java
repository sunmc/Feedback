package com.service;

import java.util.List;

import com.bean.WorkItem;
import com.util.bean.Result;

public interface IWorkItemService {

	public Result<WorkItem> getByObjectId(String objectid);
	
	public Result<WorkItem> updateByObjectId(WorkItem wi);
	
	public Result<WorkItem> insert(WorkItem wi);
	
	public Result<List<WorkItem>> getWorkFlowStatus(String bizobjectid);
}
