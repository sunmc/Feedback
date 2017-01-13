package com.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bean.Post;
import com.bean.User;
import com.service.IPostService;
import com.util.bean.Result;

@Controller
@RequestMapping("post")
public class PostController extends BaseController {

	@Autowired
	private IPostService postService;
	
	@ResponseBody
	@RequestMapping("getByCode")
	public Result<List<Post>> getByCode(String code){
		Result<List<Post>> res = postService.getPostByCode(code);
		return res;
	}
	@RequestMapping("updateView")
	public ModelAndView updateView(){
		ModelAndView model = new ModelAndView("mobile/admin/post");
		Result<List<Post>> res = postService.selectPosts();
		if(res.isFlag()){
			model.addObject("posts", res.getData());
		}
		return model;
	}
	@ResponseBody
	@RequestMapping("update")
	public Result<List<Post>> update(Post post, HttpSession session){
		User user = (User)session.getAttribute("user");
		Result<List<Post>> res = postService.update(post, user);
		return res;
	}
}
