package com.service;

import java.util.List;

import com.bean.ProjectIssueManage;
import com.bean.ProjectListItem;
import com.bean.User;
import com.util.bean.Result;

public interface IProjectIssueManageService {

	Result<ProjectIssueManage> insert(ProjectIssueManage project, User user);
	
	Result<List<ProjectIssueManage>> getBySelective(ProjectIssueManage project);
	
	Result<List<ProjectListItem>> selectTaskByUserActivity(String activity, String userid, int state);
	
	Result<ProjectIssueManage> updateByObjectId(ProjectIssueManage project);
	
	Result<ProjectIssueManage> selectByObjectId(String objectid);
	
	Result<ProjectIssueManage> wtpd(ProjectIssueManage project, User user);
	
	Result<ProjectIssueManage> wtfx(ProjectIssueManage project, User user);
	
	Result<ProjectIssueManage> wtfp(ProjectIssueManage project, User user);
	
	Result<ProjectIssueManage> wtzg(ProjectIssueManage project, User user);
	
	Result<ProjectIssueManage> wtqr(ProjectIssueManage project, User user);

	Result<ProjectIssueManage> wtgb(ProjectIssueManage project, User user);
	
	Result<List<ProjectListItem>> searchPli(ProjectListItem pli);
	
}
