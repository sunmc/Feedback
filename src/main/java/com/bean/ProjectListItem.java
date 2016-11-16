package com.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.util.StringUtil;

public class ProjectListItem {

	private String objectid;
	
	private String workitemid;
	
	private String xmbh;
	
	private String wtms;
	
	private String wtlb;
	
	private String lsh;
	
	private String fqrxm;
	
	private String bizschemacode;
	
	private String activitycode;
	
	private Date yqwcsj;
	
	private Integer qrstate;
	
	private String statecolor;
	
	private Date createtime;
	
	private Date createtimeStart;
	
	private Date createtimeEnd;
	
	private String createtimes;

	public String getObjectid() {
		return objectid;
	}

	public void setObjectid(String objectid) {
		this.objectid = objectid;
	}

	public String getWorkitemid() {
		return workitemid;
	}

	public void setWorkitemid(String workitemid) {
		this.workitemid = workitemid;
	}

	public String getWtms() {
		return wtms;
	}

	public void setWtms(String wtms) {
		this.wtms = wtms;
	}

	public String getFqrxm() {
		return fqrxm;
	}

	public void setFqrxm(String fqrxm) {
		this.fqrxm = fqrxm;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.createtimes = sdf.format(createtime);
	}

	public String getLsh() {
		return lsh;
	}

	public void setLsh(String lsh) {
		this.lsh = lsh;
	}

	public String getXmbh() {
		return xmbh;
	}

	public void setXmbh(String xmbh) {
		this.xmbh = xmbh;
	}

	public String getCreatetimes() {
		return createtimes;
	}

	public void setCreatetimes(String createtimes) {
		this.createtimes = createtimes;
	}

	public String getBizschemacode() {
		return bizschemacode;
	}

	public void setBizschemacode(String bizschemacode) {
		this.bizschemacode = bizschemacode;
	}

	public String getActivitycode() {
		return activitycode;
	}

	public void setActivitycode(String activitycode) {
		this.activitycode = activitycode;
	}

	public Date getCreatetimeStart() {
		return createtimeStart;
	}

	public void setCreatetimeStart(Date createtimeStart) {
		this.createtimeStart = createtimeStart;
	}

	public Date getcreatetimeEnd() {
		return createtimeEnd;
	}

	public void setcreatetimeEnd(Date createtimeEnd) {
		this.createtimeEnd = createtimeEnd;
	}

	public String getWtlb() {
		return wtlb;
	}

	public void setWtlb(String wtlb) {
		this.wtlb = wtlb;
	}

	public Date getYqwcsj() {
		return yqwcsj;
	}

	public void setYqwcsj(Date yqwcsj) {
		this.yqwcsj = yqwcsj;
		setStatecolor();
		
	}

	public Integer getQrstate() {
		return qrstate;
	}

	public void setQrstate(Integer qrstate) {
		this.qrstate = qrstate;
		setStatecolor();
	}

	public String getStatecolor() {
		return statecolor;
	}

	public void setStatecolor(String statecolor) {
		
		this.statecolor = statecolor;
	}
	
	public void setStatecolor(){
		Date now = new Date();
		if((this.qrstate == null || (this.qrstate != null && this.qrstate < ProjectState.DOWN)) && this.yqwcsj != null){
			if(now.before(this.yqwcsj)){
				this.statecolor = "#DDFFF5"; //未完成
			}else if(now.after(this.yqwcsj)){
				this.statecolor = "#CD5C5C"; //拖期未完成
			}
		}else if(this.qrstate != null && this.qrstate == ProjectState.DOWN  && this.yqwcsj != null){
			if(now.before(this.yqwcsj)){
				this.statecolor = "#FFFF99"; //完成
			}else if(now.after(this.yqwcsj)){
				this.statecolor = "#F0A29B"; //拖期完成
			}
		}
	}
	
}
