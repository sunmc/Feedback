package com.qq.weixin.mp.aes.bean;

public class NewsMessage {
	public String touser;
	public String toparty;
	public String totag;
	public String msgtype = "news";
	public int agentid;
	public News news = new News();
}


