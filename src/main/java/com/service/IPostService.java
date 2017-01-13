package com.service;

import java.util.List;

import com.bean.Post;
import com.bean.User;
import com.util.bean.Result;

public interface IPostService {

	Result<List<Post>> getPostByCode(String code); 
	
	Result<List<Post>> getPostByName(String name); 
	
	Result<Post> insert(Post post, User user);
	
	Result<List<Post>> update(Post post, User user);
	
	Result<List<Post>> selectPosts();
}
