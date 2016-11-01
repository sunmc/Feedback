package com.bean;

import java.util.Date;

public class InstanceContext {
    private String objectid;

    private String bizobjectid;

    private String sequnceno;

    private String originator;

    private String orgunit;

    private String parentinstanceid;

    private Integer state;

    private Date createdtime;

    private Date starttime;

    private Date finishtime;

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

    public String getSequnceno() {
        return sequnceno;
    }

    public void setSequnceno(String sequnceno) {
        this.sequnceno = sequnceno == null ? null : sequnceno.trim();
    }

    public String getOriginator() {
        return originator;
    }

    public void setOriginator(String originator) {
        this.originator = originator == null ? null : originator.trim();
    }

    public String getOrgunit() {
        return orgunit;
    }

    public void setOrgunit(String orgunit) {
        this.orgunit = orgunit == null ? null : orgunit.trim();
    }

    public String getParentinstanceid() {
        return parentinstanceid;
    }

    public void setParentinstanceid(String parentinstanceid) {
        this.parentinstanceid = parentinstanceid == null ? null : parentinstanceid.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getCreatedtime() {
        return createdtime;
    }

    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
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
}