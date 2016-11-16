package com.bean;

import java.util.Date;
import java.util.List;

import com.util.StringUtil;
import com.util.bean.Result;

public class ProjectIssueManage {
    private String objectid;

    private String parentobjectid;

    private String createby;
    
    private User fqr;

    private Date createtime;

    private String updateby;

    private Date updatetime;

    private Integer state;

    private Integer deleteflag;

    private Integer xh;

    private Date tbrq;

    private String khmc;

    private String cpmc;

    private String xmbh;

    private String tbr;

    private String xmjd;

    private String jjcd;

    private String btmc;

    private String wtjth;

    private String wlbm;

    private String wtms;

    private String wttp;
    
    private List<Attachment> wttps;

    private String wtvedio;

    private String zrlb;

    private String wtlb;

    private String yyfx;

    private String clfa;

    private String zrr;
    
    private Date jhwcsj;
    
    private User uzrr;

    private String fazxr;
    
    private User ufazxr;

	private Date yqwcsj;

    private Date sjwcsj;

    private String wcqk;

    private String lsjdh;

    private String sfbgtz;

    private String tzbgzrr;
    
    private User utzbgzrr;

    private Date sjcnbgsj;

    private Date sjtzbgsj;

    private String sjtzbgdh;

    private String bgwcqk;

    private String cpjx;

    private String wtsfcffs;

    private String sjqd;

    private Integer wts;
    
    private Integer gzjd;

    private String bz;
    
    private String lsh;
    
    private List<Implementation> implementations;

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

    public Integer getDeleteflag() {
        return deleteflag;
    }

    public void setDeleteflag(Integer deleteflag) {
        this.deleteflag = deleteflag;
    }

    public Integer getXh() {
        return xh;
    }

    public void setXh(Integer xh) {
        this.xh = xh;
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
    
    public User getUzrr() {
		return uzrr;
	}

	public void setUzrr(User uzrr) {
		this.uzrr = uzrr;
	}

	public User getUfazxr() {
		return ufazxr;
	}

	public void setUfazxr(User ufazxr) {
		this.ufazxr = ufazxr;
	}

	public User getUtzbgzrr() {
		return utzbgzrr;
	}

	public void setUtzbgzrr(User utzbgzrr) {
		this.utzbgzrr = utzbgzrr;
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

    public String getTbr() {
        return tbr;
    }

    public void setTbr(String tbr) {
        this.tbr = tbr == null ? null : tbr.trim();
    }

    public String getXmjd() {
        return xmjd;
    }

    public void setXmjd(String xmjd) {
        this.xmjd = xmjd == null ? null : xmjd.trim();
    }

    public String getJjcd() {
        return jjcd;
    }

    public void setJjcd(String jjcd) {
        this.jjcd = jjcd == null ? null : jjcd.trim();
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

    public String getWttp() {
        return wttp;
    }

    public void setWttp(String wttp) {
        this.wttp = wttp == null ? null : wttp.trim();
    }

    public String getWtvedio() {
        return wtvedio;
    }

    public void setWtvedio(String wtvedio) {
        this.wtvedio = wtvedio == null ? null : wtvedio.trim();
    }

    public String getZrlb() {
        return zrlb;
    }

    public void setZrlb(String zrlb) {
        this.zrlb = zrlb == null ? null : zrlb.trim();
    }

    public String getWtlb() {
        return wtlb;
    }

    public void setWtlb(String wtlb) {
        this.wtlb = wtlb == null ? null : wtlb.trim();
    }

    public String getYyfx() {
        return yyfx;
    }

    public void setYyfx(String yyfx) {
        this.yyfx = yyfx == null ? null : yyfx.trim();
    }

    public String getClfa() {
        return clfa;
    }

    public void setClfa(String clfa) {
        this.clfa = clfa == null ? null : clfa.trim();
    }

    public String getZrr() {
        return zrr;
    }

    public void setZrr(String zrr) {
        this.zrr = zrr == null ? null : zrr.trim();
    }

    public String getFazxr() {
        return fazxr;
    }

    public void setFazxr(String fazxr) {
        this.fazxr = fazxr == null ? null : fazxr.trim();
    }

    public Date getYqwcsj() {
        return yqwcsj;
    }

    public void setYqwcsj(Date yqwcsj) {
        this.yqwcsj = yqwcsj;
    }

    public Date getSjwcsj() {
        return sjwcsj;
    }

    public void setSjwcsj(Date sjwcsj) {
        this.sjwcsj = sjwcsj;
    }

    public String getWcqk() {
        return wcqk;
    }

    public void setWcqk(String wcqk) {
        this.wcqk = wcqk == null ? null : wcqk.trim();
    }

    public String getLsjdh() {
        return lsjdh;
    }

    public void setLsjdh(String lsjdh) {
        this.lsjdh = lsjdh == null ? null : lsjdh.trim();
    }

    public String getSfbgtz() {
        return sfbgtz;
    }

    public void setSfbgtz(String sfbgtz) {
        this.sfbgtz = sfbgtz == null ? null : sfbgtz.trim();
    }

    public String getTzbgzrr() {
        return tzbgzrr;
    }

    public void setTzbgzrr(String tzbgzrr) {
        this.tzbgzrr = tzbgzrr == null ? null : tzbgzrr.trim();
    }

    public Date getSjcnbgsj() {
        return sjcnbgsj;
    }

    public void setSjcnbgsj(Date sjcnbgsj) {
        this.sjcnbgsj = sjcnbgsj;
    }

    public Date getSjtzbgsj() {
        return sjtzbgsj;
    }

    public void setSjtzbgsj(Date sjtzbgsj) {
        this.sjtzbgsj = sjtzbgsj;
    }

    public String getSjtzbgdh() {
        return sjtzbgdh;
    }

    public void setSjtzbgdh(String sjtzbgdh) {
        this.sjtzbgdh = sjtzbgdh == null ? null : sjtzbgdh.trim();
    }

    public String getBgwcqk() {
        return bgwcqk;
    }

    public void setBgwcqk(String bgwcqk) {
        this.bgwcqk = bgwcqk == null ? null : bgwcqk.trim();
    }

    public String getCpjx() {
        return cpjx;
    }

    public void setCpjx(String cpjx) {
        this.cpjx = cpjx == null ? null : cpjx.trim();
    }

    public String getWtsfcffs() {
        return wtsfcffs;
    }

    public void setWtsfcffs(String wtsfcffs) {
        this.wtsfcffs = wtsfcffs == null ? null : wtsfcffs.trim();
    }

    public String getSjqd() {
        return sjqd;
    }

    public void setSjqd(String sjqd) {
        this.sjqd = sjqd == null ? null : sjqd.trim();
    }

    public Integer getWts() {
        return wts;
    }

    public void setWts(Integer wts) {
        this.wts = wts;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz == null ? null : bz.trim();
    }

	public List<Implementation> getImplementations() {
		return implementations;
	}

	public void setImplementations(List<Implementation> implementations) {
		this.implementations = implementations;
	}

	public List<Attachment> getWttps() {
		return wttps;
	}

	public void setWttps(List<Attachment> wttps) {
		this.wttps = wttps;
	}
	
	public Result<String> validate(int state, ProjectIssueManage project){
		Result<String> res = new Result<String>();
		switch(state){
		case 1:
			if(StringUtil.isNullOrEmpty(project.xmbh)){
				res.setFlag(false);
				res.setMessage("项目编号不能为空");
			}
			if(StringUtil.isNullOrEmpty(project.khmc)){
				res.setFlag(false);
				res.setMessage("客户名称不能为空");
			}
			if(StringUtil.isNullOrEmpty(project.cpmc)){
				res.setFlag(false);
				res.setMessage("产品名称不能为空");
			}
			if(StringUtil.isNullOrEmpty(project.xmjd)){
				res.setFlag(false);
				res.setMessage("项目阶段不能为空");
			}
			if(project.yqwcsj == null){
				res.setFlag(false);
				res.setMessage("要求完成时间不能为空");
			}
			if(StringUtil.isNullOrEmpty(project.btmc)){
				res.setFlag(false);
				res.setMessage("部套名称不能为空");
			}
			if(StringUtil.isNullOrEmpty(project.wtjth)){
				res.setFlag(false);
				res.setMessage("问题件图号不能为空");
			}
			break;
		}
		return res;
	}

	public Integer getGzjd() {
		return gzjd;
	}

	public void setGzjd(Integer gzjd) {
		this.gzjd = gzjd;
	}

	public String getLsh() {
		return lsh;
	}

	public void setLsh(String lsh) {
		this.lsh = lsh;
	}

	public Date getJhwcsj() {
		return jhwcsj;
	}

	public void setJhwcsj(Date jhwcsj) {
		this.jhwcsj = jhwcsj;
	}

	public User getFqr() {
		return fqr;
	}

	public void setFqr(User fqr) {
		this.fqr = fqr;
	}
}