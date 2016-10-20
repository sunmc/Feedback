package com.bean;

import java.util.Date;

public class WTTC {
    private String objectid;

    private String parentobjectid;

    private String createby;

    private Date createtime;

    private String updateby;

    private Date updatetime;

    private Integer state;

    private String ztc;

    private Date tbrq;

    private String khmc;

    private String cpmc;

    private String xmbh;

    private String xmjd;

    private Integer jjcd;

    private Integer zycd;

    private String btmc;

    private String wtjth;

    private String wlbm;

    private String wtms;

    private String wttp1;

    private String wttp2;

    private String wtsp;

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

    public String getCreateby() {
        return createby;
    }

    public void setCreateby(String createby) {
        this.createby = createby == null ? null : createby.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getUpdateby() {
        return updateby;
    }

    public void setUpdateby(String updateby) {
        this.updateby = updateby == null ? null : updateby.trim();
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

    public String getZtc() {
        return ztc;
    }

    public void setZtc(String ztc) {
        this.ztc = ztc == null ? null : ztc.trim();
    }

    public Date getTbrq() {
        return tbrq;
    }

    public void setTbrq(Date tbrq) {
        this.tbrq = tbrq;
    }

    public String getKhmc() {
        return khmc;
    }

    public void setKhmc(String khmc) {
        this.khmc = khmc == null ? null : khmc.trim();
    }

    public String getCpmc() {
        return cpmc;
    }

    public void setCpmc(String cpmc) {
        this.cpmc = cpmc == null ? null : cpmc.trim();
    }

    public String getXmbh() {
        return xmbh;
    }

    public void setXmbh(String xmbh) {
        this.xmbh = xmbh == null ? null : xmbh.trim();
    }

    public String getXmjd() {
        return xmjd;
    }

    public void setXmjd(String xmjd) {
        this.xmjd = xmjd == null ? null : xmjd.trim();
    }

    public Integer getJjcd() {
        return jjcd;
    }

    public void setJjcd(Integer jjcd) {
        this.jjcd = jjcd;
    }

    public Integer getZycd() {
        return zycd;
    }

    public void setZycd(Integer zycd) {
        this.zycd = zycd;
    }

    public String getBtmc() {
        return btmc;
    }

    public void setBtmc(String btmc) {
        this.btmc = btmc == null ? null : btmc.trim();
    }

    public String getWtjth() {
        return wtjth;
    }

    public void setWtjth(String wtjth) {
        this.wtjth = wtjth == null ? null : wtjth.trim();
    }

    public String getWlbm() {
        return wlbm;
    }

    public void setWlbm(String wlbm) {
        this.wlbm = wlbm == null ? null : wlbm.trim();
    }

    public String getWtms() {
        return wtms;
    }

    public void setWtms(String wtms) {
        this.wtms = wtms == null ? null : wtms.trim();
    }

    public String getWttp1() {
        return wttp1;
    }

    public void setWttp1(String wttp1) {
        this.wttp1 = wttp1 == null ? null : wttp1.trim();
    }

    public String getWttp2() {
        return wttp2;
    }

    public void setWttp2(String wttp2) {
        this.wttp2 = wttp2 == null ? null : wttp2.trim();
    }

    public String getWtsp() {
        return wtsp;
    }

    public void setWtsp(String wtsp) {
        this.wtsp = wtsp == null ? null : wtsp.trim();
    }
}