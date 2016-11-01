package com.qq.weixin.mp.aes.bean;

public class UserInfo {
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
