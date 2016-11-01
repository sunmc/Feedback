package com.qq.weixin.mp.aes.bean;

public class UserId {
	// 成员UserID
	public String UserId;
	// 手机设备号(由微信在安装时随机生成，删除重装会改变，升级不受影响，同一设备上不同的登录账号生成的deviceid也不同)
	public String DeviceId;
	// 非企业成员的标识，对当前企业号唯一
	public String OpenId;
	public String errcode;
	public String errmsg;

	public String toString() {
		return "UserId:" + UserId + ";OpenId:" + OpenId + ";DeviceId" + DeviceId + ";errcode:" + errcode + ";errmsg:"
				+ errmsg;
	}
}
