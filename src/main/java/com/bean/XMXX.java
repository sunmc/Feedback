package com.bean;

public class XMXX {
    private String xmbh;

    private String khmc;

    private String cpmc;

    private String cpjd;

    public String getXmbh() {
        return xmbh;
    }

    public void setXmbh(String xmbh) {
        this.xmbh = xmbh == null ? null : xmbh.trim();
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

    public String getCpjd() {
        return cpjd;
    }

    public void setCpjd(String cpjd) {
        this.cpjd = cpjd == null ? null : cpjd.trim();
    }
}