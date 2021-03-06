package com.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bean.Post;
import com.bean.ProjectState;
import com.bean.User;
import com.mapper.InstanceContextMapper;
import com.mapper.PostMapper;
import com.mapper.UserMapper;
import com.qq.weixin.mp.aes.bean.UserInfo;
import com.service.IUserService;
import com.util.StringUtil;
import com.util.bean.Result;

@Service
public class UserService implements IUserService {

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private PostMapper postMapper;
	@Autowired
	private InstanceContextMapper instanceMapper;
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
			if(data != null){
				res.setFlag(true);
				res.setData(data);				
			}else{
				res.setFlag(false);
				res.setMessage("用户名或密码错误");
			}
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

	@Override
	public Result<User> insert(User user) {
		Result<User> res = new Result<>();
		try{
			userMapper.insertSelective(user);
			res.setFlag(true);
		}catch(Exception e){
			res.setFlag(false);
			res.setMessage(e.getMessage());
		}
		return res;
	}

	@Override
	public Result<User> syncWXUser(UserInfo user) {
		Result<User> res = new Result<User>();
		String zh = user.userid;
		User u = new User();
		u.setZh(zh);
		List<User> users = userMapper.getList(u);
		if(users.size() > 0){
			res.setFlag(true);
			res.setData(users.get(0));
		}else{
			String id = instanceMapper.getID();
			Date now = new Date();
			u.setObjectid(id);
			u.setZh(zh);
			u.setCreateby("syncWX");
			u.setCreatetime(now);
			u.setDelflag(0);
			u.setEmail(user.email);
			u.setPasswd("123456");
			u.setSjh(user.mobile);
			u.setState(0);
			u.setSzbm(user.department[user.department.length - 1] + "");
			u.setTx(user.avatar);
			u.setWxh(user.weixinid);
			u.setXb(Integer.parseInt(user.gender));
			u.setXm(user.name);
			u.setZw(user.position);
			userMapper.insertSelective(u);
			res.setData(u);
			res.setFlag(true);
		}
		return res;
	}

	@Override
	public boolean ifAdmin(String userid) {
		if(StringUtil.isNullOrEmpty(userid)){
			return false;
		}
		try{
			List<Post> posts = postMapper.selectByUserid(userid);
			for(Post p : posts){
				if(p.getPostcode().equals(ProjectState.WTPD)){
					return true;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return false;
	}

	@Override
	public Result<User> update(User user) {
		Result<User> res = new Result<>();
		try{
			user.setUpdatetime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			userMapper.updateByPrimaryKeySelective(user);
			user = userMapper.selectByPrimaryKey(user.getObjectid());
			res.setData(user);
			res.setFlag(true);
		}catch(Exception e){
			res.setFlag(false);
			res.setMessage(e.getMessage());
		}
		return res;
	}
	

}
