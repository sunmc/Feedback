package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("common")
public class CommonController  extends BaseController{

	@RequestMapping()
	public ModelAndView index(){
		return new ModelAndView("mobile/a6wtgz/wtgzlist");
	}
	
	
}
