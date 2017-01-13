package com.service;

import java.util.List;

import com.bean.LsjData;
import com.bean.OverTaskParam;
import com.bean.ProjectIssueManage;
import com.bean.ProjectListItem;
import com.bean.TQProject;
import com.bean.TZBGData;
import com.bean.User;
import com.bean.WorkItem;
import com.util.bean.Result;

public interface IProjectIssueManageService {

	Result<ProjectIssueManage> insert(ProjectIssueManage project, User user);
	
	Result<List<ProjectIssueManage>> getBySelective(ProjectIssueManage project);
	
	Result<List<ProjectListItem>> selectTaskByUserActivity(ProjectListItem pli);
	
	Result<ProjectIssueManage> updateByObjectId(ProjectIssueManage project, User user);
	
	Result<ProjectIssueManage> selectByObjectId(String objectid);
	
	Result<ProjectIssueManage> wtpd(ProjectIssueManage project, User user);
	
	Result<ProjectIssueManage> wtcl(ProjectIssueManage project, User user);
	
	Result<ProjectIssueManage> wtfx(ProjectIssueManage project, User user);
	
	Result<ProjectIssueManage> wtfp(ProjectIssueManage project, User user);
	
	Result<ProjectIssueManage> wtzg(ProjectIssueManage project, User user);
	
	Result<ProjectIssueManage> wtqr(ProjectIssueManage project, User user);

	Result<ProjectIssueManage> wtgb(ProjectIssueManage project, User user);
	
	Result<List<ProjectListItem>> searchPli(ProjectListItem pli);
	
	Result<OverTaskParam> getOverTask(String userid);
	
	Result<List<ProjectListItem>> getOverTask(OverTaskParam otp);
	
	Result<List<LsjData>> getLsjData(String wth);
	
	Result<List<WorkItem>> getLsjWorkItem(String lsh);
	
	Result<List<TZBGData>> getTZBGData(String wth);
	
	Result<List<WorkItem>> getTZBGWorkItem(String lsh);
	
	Result<List<ProjectIssueManage>> importProject(String filepath);
	
	void sendDBTQ();
	
	void sendTQWT();
	
	Result<TQProject> getTQProjectCount(TQProject record);
	
	Result<List<ProjectListItem>> getTQProject(TQProject record);
}
