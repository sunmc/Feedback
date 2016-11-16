package com.bean;

import java.util.Date;

public class Implementation {
    private String objectid;

    private String parentobjectid;

    private Date createtime;

    private String createby;

    private Date updatetime;

    private String updateby;

    private Integer state;

    private Integer deleteflag;

    private String jjcs;

    private String jjcszrr;

    private Date jjcsjhwcsj;

    private String jjcsjjwt;

    private Date jjcswcsj;
    
    private Integer gzjd;
    
    private String zrrxm;

    public String getObjectid() {
        return objectid;
    }

    public void setObjectid(String objectid) {
        this.objectid = objectid == null ? null : objectid.trim();
    }

    public String getParentobjectid() {
        return parentobjectid;
    }

    public void setParentobjectid(String parentobjectid) {
        this.parentobjectid = parentobjectid == null ? null : parentobjectid.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getCreateby() {
        return createby;
    }

    public void setCreateby(String createby) {
        this.createby = createby == null ? null : createby.trim();
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getUpdateby() {
        return updateby;
    }

    public void setUpdateby(String updateby) {
        this.updateby = updateby == null ? null : updateby.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getDeleteflag() {
        return deleteflag;
    }

    public void setDeleteflag(Integer deleteflag) {
        this.deleteflag = deleteflag;
    }

    public String getJjcs() {
        return jjcs;
    }

    public void setJjcs(String jjcs) {
        this.jjcs = jjcs == null ? null : jjcs.trim();
    }

    public String getJjcszrr() {
        return jjcszrr;
    }

    public void setJjcszrr(String jjcszrr) {
        this.jjcszrr = jjcszrr == null ? null : jjcszrr.trim();
    }

    public Date getJjcsjhwcsj() {
        return jjcsjhwcsj;
    }

    public void setJjcsjhwcsj(Date jjcsjhwcsj) {
        this.jjcsjhwcsj = jjcsjhwcsj;
    }

    public String getJjcsjjwt() {
        return jjcsjjwt;
    }

    public void setJjcsjjwt(String jjcsjjwt) {
        this.jjcsjjwt = jjcsjjwt == null ? null : jjcsjjwt.trim();
    }

    public Date getJjcswcsj() {
        return jjcswcsj;
    }

    public void setJjcswcsj(Date jjcswcsj) {
        this.jjcswcsj = jjcswcsj;
    }

	public Integer getGzjd() {
		return gzjd;
	}

	public void setGzjd(Integer gzjd) {
		this.gzjd = gzjd;
	}

	public String getZrrxm() {
		return zrrxm;
	}

	public void setZrrxm(String zrrxm) {
		this.zrrxm = zrrxm;
	}
}