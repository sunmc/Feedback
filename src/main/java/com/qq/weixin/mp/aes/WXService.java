package com.qq.weixin.mp.aes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.qq.weixin.mp.aes.bean.DepartmentList;
import com.qq.weixin.mp.aes.bean.NewsMessage;
import com.qq.weixin.mp.aes.bean.Token;
import com.qq.weixin.mp.aes.bean.UserId;
import com.qq.weixin.mp.aes.bean.UserInfo;
import com.util.HttpUtil;

public class WXService {

	private final static String corpId = "wx46be78f8feff44a2";
	private final static String sercret = "W3krKxBeYGcNxEqUsPbRumRQ86qQc8Xj8EAhXTUKHIbFTEkKuJsswM2SIgIOql7R";
	public static String token = "";
	private final static String sendMessageUrl = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=";
	static Gson gson = new GsonBuilder().disableHtmlEscaping().create();
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
	public static UserId getUserId(String code){
		if(token == null || token.length() < 2){
			getToken();
		}
		String url = "https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo";
		String param = "access_token=" + token + "&code=" + code+"&agentid=27";
		String res = HttpUtil.sendGet(url, param);
		UserId userinfo = gson.fromJson(res, new TypeToken<UserId>(){}.getType());
		return userinfo;
	}
	//获取成员信息
	public static UserInfo getUserInfo(String userid){
		if(token == null || token.length() < 2){
			getToken();
		}
		String url = "https://qyapi.weixin.qq.com/cgi-bin/user/get";
		String param = "access_token=" + token + "&userid=" + userid;
		String res = HttpUtil.sendGet(url, param);
		UserInfo userinfo = gson.fromJson(res, new TypeToken<UserInfo>(){}.getType());
		return userinfo;
	}
	//获取部门列表
	public static DepartmentList getDepartmentList(String id){
		if(token == null || token.length() < 2){
			getToken();
		}
		String url = "https://qyapi.weixin.qq.com/cgi-bin/department/list";
		String param = "access_token=" + token;
		if(id != null && id.length() > 0){
			param += "&id="+id;
		}
		String res = HttpUtil.sendGet(url, param);
		DepartmentList department = gson.fromJson(res, new TypeToken<DepartmentList>(){}.getType());
		return department;
	}
	//发送消息（news）
	public static String sendNewsMessage(NewsMessage message){
		if(token == null || token.length() < 2){
			getToken();
		}
		String url = sendMessageUrl + token;
		String datajson = gson.toJson(message);
		String res = HttpUtil.sendPost(url, datajson);
		return res;
	}
	
}
