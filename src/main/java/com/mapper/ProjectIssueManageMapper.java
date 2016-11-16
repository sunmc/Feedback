package com.mapper;

import java.util.List;

import com.bean.Implementation;
import com.bean.ProjectIssueManage;
import com.bean.ProjectListItem;

public interface ProjectIssueManageMapper {
    int deleteByPrimaryKey(String objectid);

    int insert(ProjectIssueManage record);

    int insertSelective(ProjectIssueManage record);

    ProjectIssueManage selectByPrimaryKey(String objectid);

    int updateByPrimaryKeySelective(ProjectIssueManage record);

    int updateByPrimaryKey(ProjectIssueManage record);
    
    int insertImplementation(ProjectIssueManage record);
    
    List<Implementation> selectImplementationByParentId(String parentid);
    
    List<ProjectListItem> selectTaskByUserAndActivity(String activity, String userid, int state);
    
    List<ProjectListItem> searchPli(ProjectListItem record);
}