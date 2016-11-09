package com.mapper;

import com.bean.ProjectIssueManage;

public interface ProjectIssueManageMapper {
    int deleteByPrimaryKey(String objectid);

    int insert(ProjectIssueManage record);

    int insertSelective(ProjectIssueManage record);

    ProjectIssueManage selectByPrimaryKey(String objectid);

    int updateByPrimaryKeySelective(ProjectIssueManage record);

    int updateByPrimaryKey(ProjectIssueManage record);
    
    int insertImplementation(ProjectIssueManage record);
}