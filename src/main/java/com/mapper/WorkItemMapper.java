package com.mapper;

import com.bean.WorkItem;

public interface WorkItemMapper {
    int deleteByPrimaryKey(String objectid);

    int insert(WorkItem record);

    int insertSelective(WorkItem record);

    WorkItem selectByPrimaryKey(String objectid);

    int updateByPrimaryKeySelective(WorkItem record);

    int updateByPrimaryKey(WorkItem record);
}