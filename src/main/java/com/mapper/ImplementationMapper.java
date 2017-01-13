package com.mapper;

import com.bean.Implementation;
import com.bean.ProjectIssueManage;

public interface ImplementationMapper {
    int deleteByPrimaryKey(String objectid);

    int insert(Implementation record);

    int insertSelective(Implementation record);

    Implementation selectByPrimaryKey(String objectid);

    int updateByPrimaryKeySelective(Implementation record);

    int updateByPrimaryKey(Implementation record);
    
    int updateImplementations(ProjectIssueManage project);
}