package com.bean;

public class Department {
    private String objectid;

    private String parentobjectid;

    private String name;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}