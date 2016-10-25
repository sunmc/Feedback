package com.qq.weixin.mp.aes;

import java.util.List;

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
	class Token{
		public String access_token;
		public String expires_in;
	}
	public class UserId{
		//成员UserID
		public String UserId;
		//手机设备号(由微信在安装时随机生成，删除重装会改变，升级不受影响，同一设备上不同的登录账号生成的deviceid也不同)
		public String DeviceId;
		//	非企业成员的标识，对当前企业号唯一
		public String OpenId;
		public String errcode;
		public String errmsg;
		
		public String toString(){
			return "UserId:"+UserId+";OpenId:"+OpenId+";DeviceId"+DeviceId+";errcode:"+errcode+";errmsg:"+errmsg;
		}
	}
	public class UserInfo{
		public String errcode;//	返回码
		public String errmsg;//	对返回码的文本描述内容
		public String userid;//	成员UserID。对应管理端的帐号
		public String name;//	成员名称
		public int[] department;//	成员所属部门id列表
		public String position;//	职位信息
		public String mobile;//	手机号码
		public String gender;//	性别。0表示未定义，1表示男性，2表示女性
		public String email;//	邮箱
		public String weixinid;//	微信号
		public String avatar;//	头像url。注：如果要获取小图将url最后的"/0"改成"/64"即可
		public String status;//	关注状态: 1=已关注，2=已禁用，4=未关注
		public Extattr extattr;//	扩展属性
	}
	public class Extattr{
		public List<Attr> attr;
	}
	public class Attr{
		public String name;
		public String value;
	}
	public class Department{
		public String id;//	部门id
		public String name;//	部门名称
		public String parentid;//	父亲部门id。根部门为1
		public String order;//	在父部门中的次序值。order值小的排序靠前。
	}
	public class DepartmentList{
		public String errcode;//	返回码
		public String errmsg;//	对返回码的文本描述内容
		public List<Department> department; //部门列表数据。以部门的order字段从小到大排列
	}
}
