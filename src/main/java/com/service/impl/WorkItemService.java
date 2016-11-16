package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bean.WorkItem;
import com.mapper.WorkItemMapper;
import com.service.IWorkItemService;
import com.util.StringUtil;
import com.util.bean.Result;

@Service
public class WorkItemService implements IWorkItemService{

	@Autowired
	private WorkItemMapper workItemMapper;
	
	@Override
	public Result<WorkItem> getByObjectId(String objectid) {
		Result<WorkItem> res = new Result<WorkItem>();
		if(StringUtil.isNullOrEmpty(objectid)){
			res.setFlag(false);
			res.setMessage("不存在的任务");
			return res;
		}
		try{
			WorkItem data = workItemMapper.selectByPrimaryKey(objectid);
			res.setFlag(true);
			res.setData(data);
		}catch(Exception e){
			res.setFlag(false);
			res.setMessage(e.getMessage());
		}
		
		return res;
	}

	@Override
	public Result<WorkItem> updateByObjectId(WorkItem wi) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result<WorkItem> insert(WorkItem wi) {
		return null;
	}

}
