package com.bean;

import java.util.Date;

public class Attachment {
    private String objectid;

    private String parentobjectid;

    private Date createtime;

    private String createby;

    private Date updatetime;

    private String updateby;

    private String bizschemacode;

    private String datafield;

    private String contenttype;

    private Integer contentlength;

    private String filename;

    private String storagepath;

    private String storagefilename;

    private String downloadurl;

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

    public String getBizschemacode() {
        return bizschemacode;
    }

    public void setBizschemacode(String bizschemacode) {
        this.bizschemacode = bizschemacode == null ? null : bizschemacode.trim();
    }

    public String getDatafield() {
        return datafield;
    }

    public void setDatafield(String datafield) {
        this.datafield = datafield == null ? null : datafield.trim();
    }

    public String getContenttype() {
        return contenttype;
    }

    public void setContenttype(String contenttype) {
        this.contenttype = contenttype == null ? null : contenttype.trim();
    }

    public Integer getContentlength() {
        return contentlength;
    }

    public void setContentlength(Integer contentlength) {
        this.contentlength = contentlength;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename == null ? null : filename.trim();
    }

    public String getStoragepath() {
        return storagepath;
    }

    public void setStoragepath(String storagepath) {
        this.storagepath = storagepath == null ? null : storagepath.trim();
    }

    public String getStoragefilename() {
        return storagefilename;
    }

    public void setStoragefilename(String storagefilename) {
        this.storagefilename = storagefilename == null ? null : storagefilename.trim();
    }

    public String getDownloadurl() {
        return downloadurl;
    }

    public void setDownloadurl(String downloadurl) {
        this.downloadurl = downloadurl == null ? null : downloadurl.trim();
    }
}