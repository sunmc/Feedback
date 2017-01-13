package com.mapper;

import java.util.List;

import com.bean.Implementation;
import com.bean.OverTaskParam;
import com.bean.ProjectIssueManage;
import com.bean.ProjectListItem;
import com.bean.TQProject;

public interface ProjectIssueManageMapper {
    int deleteByPrimaryKey(String objectid);

    int insert(ProjectIssueManage record);

    int insertSelective(ProjectIssueManage record);

    ProjectIssueManage selectByPrimaryKey(String objectid);

    int updateByPrimaryKeySelective(ProjectIssueManage record);

    int updateByPrimaryKey(ProjectIssueManage record);
    
    int insertImplementation(ProjectIssueManage record);
    
    List<Implementation> selectImplementationByParentId(String parentid);
    
    List<ProjectListItem> selectTaskByUserAndActivity(ProjectListItem pli);
    
    List<ProjectListItem> searchPli(ProjectListItem record);
    
    OverTaskParam selectOverTaskCount(String userid);
    
    List<ProjectListItem> selectOverTask(OverTaskParam otp);
    
    TQProject selectOverProjectCount(TQProject otp);
    
    List<ProjectListItem> selectOverProject(TQProject otp);
    
}