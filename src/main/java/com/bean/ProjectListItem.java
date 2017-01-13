package com.bean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ProjectListItem {

	private String objectid;
	
	private String workitemid;
	
	private String cplb;
	
	private String zrlb;
	
	private String state;
	
	private String btmc;
	
	private String xmbh;
	
	private String xmmc;
	
	private String wtms;
	
	private String wtlb;
	
	private String lsh;
	
	private String fqrxm;
	
	private String xmjl;
	
	private String zrr;
	
	private String bizschemacode;
	
	private String activitycode;
	
	private String receiver;
	
	private Date yqwcsj;
	
	private Date sjwcsj;
	
	private Integer qrstate;
	
	private String statecolor;
	
	private Date createtime;
	
	private Date createtimeStart;
	
	private Date createtimeEnd;
	
	private String createtimes;
	
	private List<String> objectids;
	
	private Integer pagecurrent;
	
	private String sfylsjlc;
	
	private String wtsfcffs;
	
	private String xmjd;
	
	private String fqr;

	public String getObjectid() {
		return objectid;
	}

	public void setObjectid(String objectid) {
		this.objectid = objectid;
	}

	
	public String getCplb() {
		return cplb;
	}

	public void setCplb(String cplb) {
		this.cplb = cplb;
	}

	public String getZrlb() {
		return zrlb;
	}

	public void setZrlb(String zrlb) {
		this.zrlb = zrlb;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getBtmc() {
		return btmc;
	}

	public void setBtmc(String btmc) {
		this.btmc = btmc;
	}

	public Date getCreatetimeEnd() {
		return createtimeEnd;
	}

	public void setCreatetimeEnd(Date createtimeEnd) {
		this.createtimeEnd = createtimeEnd;
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
				this.statecolor = "#87CEFA"; //未完成
			}else if(now.after(this.yqwcsj)){
				this.statecolor = "#FA8072"; //拖期未完成
			}
		}else if(this.qrstate != null && this.qrstate == ProjectState.DOWN  && this.yqwcsj != null){
			if(now.before(this.yqwcsj)){
				this.statecolor = "#DDFFF5"; //完成
			}else if(now.after(this.yqwcsj)){
				this.statecolor = "#FFB6C1"; //拖期完成
			}
		}else if(this.qrstate == ProjectState.END){
			this.statecolor = "#efeff4"; //关闭
		}
	}

	public List<String> getObjectids() {
		return objectids;
	}

	public void setObjectids(List<String> objectids) {
		this.objectids = objectids;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getXmmc() {
		return xmmc;
	}

	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}

	public Integer getPagecurrent() {
		return pagecurrent;
	}

	public void setPagecurrent(Integer pagecurrent) {
		this.pagecurrent = pagecurrent;
	}

	public String getXmjl() {
		return xmjl;
	}

	public void setXmjl(String xmjl) {
		this.xmjl = xmjl;
	}

	public String getZrr() {
		return zrr;
	}

	public void setZrr(String zrr) {
		this.zrr = zrr;
	}

	public Date getSjwcsj() {
		return sjwcsj;
	}

	public void setSjwcsj(Date sjwcsj) {
		this.sjwcsj = sjwcsj;
	}

	public String getSfylsjlc() {
		return sfylsjlc;
	}

	public void setSfylsjlc(String sfylsjlc) {
		this.sfylsjlc = sfylsjlc;
	}

	public String getWtsfcffs() {
		return wtsfcffs;
	}

	public void setWtsfcffs(String wtsfcffs) {
		this.wtsfcffs = wtsfcffs;
	}

	public String getXmjd() {
		return xmjd;
	}

	public void setXmjd(String xmjd) {
		this.xmjd = xmjd;
	}

	public String getFqr() {
		return fqr;
	}

	public void setFar(String fqr) {
		this.fqr = fqr;
	}
	
}
