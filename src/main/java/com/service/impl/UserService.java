package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bean.User;
import com.mapper.UserMapper;
import com.service.IUserService;
import com.util.CommonUtil;
import com.util.bean.Result;

@Service
public class UserService implements IUserService {

	@Autowired
	private UserMapper userMapper;
	@Override
	public Result<List<User>> getList(User user) {
		Result<List<User>> res = new Result<>();
		List<User> data = null;
		try{
			data = userMapper.getList(user);
			res.setFlag(true);
			res.setData(data);
		}catch(Exception e){
			res.setFlag(false);
			res.setMessage(e.getMessage());
		}
		return res;
	}

	@Override
	public Result<User> loginValidate(String code, String passwd) {
//		passwd = CommonUtil.md5Unicode(passwd);
		Result<User> res = new Result<User>();
		User data = null;
		try{
			data = userMapper.loginValidate(code, passwd);
			res.setFlag(true);
			res.setData(data);
		}catch(Exception e){
			res.setFlag(false);
			res.setMessage(e.getMessage());
		}
		return res;
	}

	@Override
	public Result<List<User>> search(String text) {
		Result<List<User>> res = new Result<>();
		try{
			text = "%" + text + "%";
			List<User> data = userMapper.search(text);
			res.setData(data);
			res.setFlag(true);
		}catch(Exception e){
			res.setFlag(false);
			res.setMessage(e.getMessage());
		}
		return res;
	}

}
