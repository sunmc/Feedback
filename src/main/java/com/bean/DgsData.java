package com.bean;

public class DgsData {

	private String account;
	
	private String psd;
	
	private String srcFile;
	
	private String dstFile;
	
	private Integer level;
	
	private String internalIdentifier;
	
	public String getInternalIdentifier() {
		return internalIdentifier;
	}

	public void setInternalIdentifier(String internalIdentifier) {
		this.internalIdentifier = internalIdentifier;
	}

	private String sysName;
	
	private String serialNumber;
	
	
	public DgsData(){
		
	}
	
	public DgsData(String account, String psd, String srcFile, String dstFile, Integer level, String internalIdentifier, String sysName, String serialNumber){
		this.account = account;
		this.psd = psd;
		this.srcFile = srcFile;
		this.dstFile = dstFile;
		this.level = level;
		this.internalIdentifier = internalIdentifier;
		this.sysName = sysName;
		this.serialNumber = serialNumber;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPsd() {
		return psd;
	}

	public void setPsd(String psd) {
		this.psd = psd;
	}

	public String getSrcFile() {
		return srcFile;
	}

	public void setSrcFile(String srcFile) {
		this.srcFile = srcFile;
	}

	public String getDstFile() {
		return dstFile;
	}

	public void setDstFile(String dstFile) {
		this.dstFile = dstFile;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getSysName() {
		return sysName;
	}

	public void setSysName(String sysName) {
		this.sysName = sysName;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	
	
	
}
