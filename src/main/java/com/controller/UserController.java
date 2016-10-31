package com.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bean.User;
import com.qq.weixin.mp.aes.WXService;
import com.qq.weixin.mp.aes.WXService.UserInfo;
import com.service.IUserService;
import com.util.bean.Result;

@Controller
@RequestMapping("User")
public class UserController {

	@Autowired
	private IUserService userService;
	
	@RequestMapping("LoginValidate")
	public ModelAndView LoginValidate(String code,String passwd, HttpSession session){
		if(code != null && code.length() > 0){
			Result<User> res = new Result<User>();
			res = userService.loginValidate(code, passwd);
			if(res.isFlag()){
				User user = res.getData();
				session.setAttribute("user", user);
				return new ModelAndView("/mobile/wtcx/wtcx");
			}
		}
		
		return new ModelAndView("/mobile/login");
	}
	@ResponseBody
	@RequestMapping("search")
	public Result<List<User>> search(String text){
		Result<List<User>> res = new Result<List<User>>();
		res = userService.search(text);
		return res;
	}
	@ResponseBody
	@RequestMapping("contact")
	public ModelAndView contact(){
		return new ModelAndView("mobile/contact");
	}
	@RequestMapping("login")
	public ModelAndView login(){
		return new ModelAndView("mobile/login");
	}
}
