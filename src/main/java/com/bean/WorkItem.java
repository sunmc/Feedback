package com.bean;

import java.util.Date;

public class WorkItem {
    private String objectid;

    private String bizobjectid;

    private String bizobjectschemacode;

    private String activitycode;

    private String activityname;

    private String originator;

    private String receiver;
    
    private String receivername;

    private String sender;
    
    private String sendername;

    private String finisher;
    
    private String finishername;

    private Date receivetime;

    private Date starttime;

    private Date finishtime;

    private Date createtime;

    private Date updatetime;

    private Integer state;

    private Integer eitherorall;

    private String instanceid;
    
    private ProjectIssueManage projectIssueManage;

    public String getObjectid() {
        return objectid;
    }

    public void setObjectid(String objectid) {
        this.objectid = objectid == null ? null : objectid.trim();
    }

    public String getBizobjectid() {
        return bizobjectid;
    }

    public void setBizobjectid(String bizobjectid) {
        this.bizobjectid = bizobjectid == null ? null : bizobjectid.trim();
    }

    public String getBizobjectschemacode() {
        return bizobjectschemacode;
    }

    public void setBizobjectschemacode(String bizobjectschemacode) {
        this.bizobjectschemacode = bizobjectschemacode == null ? null : bizobjectschemacode.trim();
    }

    public String getActivitycode() {
        return activitycode;
    }

    public void setActivitycode(String activitycode) {
        this.activitycode = activitycode == null ? null : activitycode.trim();
    }

    public String getActivityname() {
        return activityname;
    }

    public void setActivityname(String activityname) {
        this.activityname = activityname == null ? null : activityname.trim();
    }

    public String getOriginator() {
        return originator;
    }

    public void setOriginator(String originator) {
        this.originator = originator == null ? null : originator.trim();
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver == null ? null : receiver.trim();
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender == null ? null : sender.trim();
    }

    public String getFinisher() {
        return finisher;
    }

    public void setFinisher(String finisher) {
        this.finisher = finisher == null ? null : finisher.trim();
    }

    public Date getReceivetime() {
        return receivetime;
    }

    public void setReceivetime(Date receivetime) {
        this.receivetime = receivetime;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getFinishtime() {
        return finishtime;
    }

    public void setFinishtime(Date finishtime) {
        this.finishtime = finishtime;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getEitherorall() {
        return eitherorall;
    }

    public void setEitherorall(Integer eitherorall) {
        this.eitherorall = eitherorall;
    }

    public String getInstanceid() {
        return instanceid;
    }

    public void setInstanceid(String instanceid) {
        this.instanceid = instanceid == null ? null : instanceid.trim();
    }

	public ProjectIssueManage getProjectIssueManage() {
		return projectIssueManage;
	}

	public void setProjectIssueManage(ProjectIssueManage projectIssueManage) {
		this.projectIssueManage = projectIssueManage;
	}

	public String getReceivername() {
		return receivername;
	}

	public void setReceivername(String receivername) {
		this.receivername = receivername;
	}

	public String getSendername() {
		return sendername;
	}

	public void setSendername(String sendername) {
		this.sendername = sendername;
	}

	public String getFinishername() {
		return finishername;
	}

	public void setFinishername(String finishername) {
		this.finishername = finishername;
	}

}