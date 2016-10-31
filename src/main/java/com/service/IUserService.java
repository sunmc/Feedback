package com.service;

import java.util.List;

import com.bean.User;
import com.qq.weixin.mp.aes.WXService.UserInfo;
import com.util.bean.Result;

public interface IUserService {

	public Result<List<User>> getList(User user);
	
	public Result<List<User>> search(String text);
	
	public Result<User> loginValidate(String code, String passwd);
	
	public Result<User> insert(User user);
	
	public Result<User> syncWXUser(UserInfo user);
	
}
