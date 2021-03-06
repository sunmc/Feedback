package com.mapper;

import java.util.List;

import com.bean.WorkItem;

public interface WorkItemMapper {
    int deleteByPrimaryKey(String objectid);

    int insert(WorkItem record);

    int insertSelective(WorkItem record);

    WorkItem selectByPrimaryKey(String objectid);

    int updateByPrimaryKeySelective(WorkItem record);

    int updateByPrimaryKey(WorkItem record);
    
    int updateByBizCode(WorkItem record);
    
    List<WorkItem> selectBySelective(WorkItem record);
    
    List<WorkItem> selectWorkFlowStatus(String bizobjectid);
    
    List<WorkItem> selectWorkItemUsers(WorkItem record);
    
    List<WorkItem> selectWorkItemJd(String bizobjectid);
    
}