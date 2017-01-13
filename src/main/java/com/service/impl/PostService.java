package com.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bean.Post;
import com.bean.User;
import com.mapper.InstanceContextMapper;
import com.mapper.PostMapper;
import com.mapper.UserMapper;
import com.service.IPostService;
import com.util.bean.Result;
@Service
public class PostService implements IPostService {

	@Autowired
	private PostMapper postMapper;
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private InstanceContextMapper instanceMapper;
	@Override
	public Result<List<Post>> getPostByCode(String code) {
		Result<List<Post>> res = new Result<>();
		try{
			List<Post> data  = postMapper.getPostByCode(code);
			res.setFlag(true);
			res.setData(data);
		}catch(Exception e){
			e.printStackTrace();
			res.setFlag(false);
			res.setMessage(e.getMessage());
		}
		return res;
	}

	@Override
	public Result<List<Post>> getPostByName(String name) {
		Result<List<Post>> res = new Result<>();
		try{
			List<Post> data  = postMapper.getPostByName(name);
			res.setFlag(true);
			res.setData(data);
		}catch(Exception e){
			e.printStackTrace();
			res.setFlag(false);
			res.setMessage(e.getMessage());
		}
		return res;
	}

	@Override
	public Result<Post> insert(Post post, User user) {
		Result<Post> res = new Result<>();
		try{
			post.setObjectid(instanceMapper.getID());
			int i  = postMapper.insertSelective(post);
			res.setFlag(true);
			res.setData(post);
		}catch(Exception e){
			e.printStackTrace();
			res.setFlag(false);
			res.setMessage(e.getMessage());
		}
		return res;
	}

	@Override
	public Result<List<Post>> update(Post post, User user) {
		Result<List<Post>> res = new Result<>();
		List<Post> data = new ArrayList<>();
		try{
			//获取岗位本次设置的人员
			String owners = post.getOwner();
			List<User> users = userMapper.selectByName(owners);
			owners = "";
			for(User u : users){
				owners = owners + u.getObjectid() + ";";
			}
			String postcode = post.getPostcode();
			String postname = post.getPostname();
			//获取岗位原有的人员
			List<Post> posts = postMapper.getPostByCode(postcode);
			for(Post p : posts){
				String owner = p.getOwner();
				if(owners.contains(owner)){
					owners = owners.replaceAll(owner + ";", "");
					data.add(p);
				}else{
					//将不在本次设置的人员移除
					p.setDeleteflag(1);
					p.setUpdatetime(new Date());
					p.setUpdateby(user.getObjectid());
					postMapper.updateByPrimaryKeySelective(p);
				}
			}
			//将岗位新增的人员添加
			Iterator<User> useriterator = users.iterator();
			while(useriterator.hasNext()){//剔除不在本次设置的人员
				User u = useriterator.next();
				if(!owners.contains(u.getObjectid())){
					useriterator.remove();
				}
			}
			for(User u : users){
				Post p = new Post();
				String pid = instanceMapper.getID();
				p.setObjectid(pid);
				p.setOwner(u.getObjectid());
				p.setCreatetime(new Date());
				p.setCreateby(user.getObjectid());
				p.setPostcode(postcode);
				p.setPostname(postname);
				p.setState(0);
				p.setDeleteflag(0);
				postMapper.insertSelective(p);
				data.add(p);
			}
			res.setFlag(true);
			res.setData(data);
			res.setMessage("保存成功！");
		}catch(Exception e){
			e.printStackTrace();
			res.setFlag(false);
			res.setMessage(e.getMessage());
		}
		return res;
	}

	@Override
	public Result<List<Post>> selectPosts() {
		Result<List<Post>> res = new Result<>();
		try{
			List<Post> data = postMapper.selectPosts();
			res.setFlag(true);
			res.setData(data);
		}catch(Exception e){
			e.printStackTrace();
			res.setFlag(false);
			res.setMessage(e.getMessage());
		}
		return res;
	}
	
	

}
