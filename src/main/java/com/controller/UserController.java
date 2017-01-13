package com.controller;

import java.net.URLDecoder;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bean.User;
import com.service.IUserService;
import com.util.Base64Util;
import com.util.bean.Result;

@Controller
@RequestMapping("User")
public class UserController  extends BaseController{

	@Autowired
	private IUserService userService;
	
	@RequestMapping("LoginValidate")
	public ModelAndView LoginValidate(String code,String passwd, HttpSession session){
		ModelAndView model = null;
		if(code != null && code.length() > 0){
			Result<User> res = new Result<User>();
			res = userService.loginValidate(code, passwd);
			if(res.isFlag()){
				User user = res.getData();
				session.setAttribute("user", user);
				if(passwd.equals("123456")){
					return new ModelAndView("/mobile/user/updatepwd");
				}
				if(session.getAttribute("url") != null){
					String url = session.getAttribute("url").toString();	
					if(url.length() > 0 && !url.contains("log")){
						url = URLDecoder.decode(url);
						return new ModelAndView("redirect:" + url);
					}
				}
				
				return new ModelAndView("/mobile/a6wtcx/wtcxlist");
			}else{
				model = new ModelAndView("/mobile/login");
				model.addObject("error", res.getMessage());
				return model;
			}
		}else{
			model = new ModelAndView("/mobile/login");
			model.addObject("error", "请输入用户名");
			return model;
		}
		
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
	public ModelAndView login(String url, HttpSession session){
		session.setAttribute("url", url);
		return new ModelAndView("mobile/login");
	}
	@RequestMapping("logout")
	public ModelAndView logout(HttpSession session){
		session.setAttribute("user", null);
		return new ModelAndView("mobile/login");
	}
	@RequestMapping("update")
	public ModelAndView update(User user, HttpSession session){
		ModelAndView model = null;
		User u = (User)session.getAttribute("user");
		user.setObjectid(u.getObjectid());
		Result<User> res = userService.update(user);
		if(res.isFlag()){
			session.setAttribute("user", res.getData());
			return new ModelAndView("/mobile/a6wtcx/wtcxlist");
		}else{
			model = new ModelAndView("/mobile/error");
			model.addObject("error", res.getMessage());
		}
		return model;
	}
}
