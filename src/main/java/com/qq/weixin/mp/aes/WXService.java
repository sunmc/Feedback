package com.qq.weixin.mp.aes;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.util.HttpUtil;

public class WXService {

	private final static String corpId = "wx46be78f8feff44a2";
	private final static String sercret = "W3krKxBeYGcNxEqUsPbRumRQ86qQc8Xj8EAhXTUKHIbFTEkKuJsswM2SIgIOql7R";
	public static String token = "";
	static Gson gson = new Gson();
	//获取token
	public static String getToken(){
		String url = "https://qyapi.weixin.qq.com/cgi-bin/gettoken";
		String param = "corpid="+corpId+"&corpsecret="+sercret;
		String res = HttpUtil.sendGet(url, param);
		Token t = gson.fromJson(res, new TypeToken<Token>(){}.getType());
		token = t.access_token;
		return token;
	}
	//获取员工id
	public static UserInfo getUserInfo(String code){
		String url = "https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo";
		String param = "access_token=" + token + "&code=" + code;
		String res = HttpUtil.sendGet(url, param);
		UserInfo userinfo = gson.fromJson(res, new TypeToken<UserInfo>(){}.getType());
		return userinfo;
	}
	class Token{
		public String access_token;
		public String expires_in;
	}
	public class UserInfo{
		//成员UserID
		String UserId;
		//手机设备号(由微信在安装时随机生成，删除重装会改变，升级不受影响，同一设备上不同的登录账号生成的deviceid也不同)
		String DeviceId;
		//	非企业成员的标识，对当前企业号唯一
		String OpenId;
		String errcode;
		String errmsg;
	}
}
