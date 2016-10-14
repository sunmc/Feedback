package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("wtgz")
public class WTGZController {

	@RequestMapping
	public ModelAndView index(){
		return new ModelAndView("mobile/wtgz/wtgzlist");
	}
	
	@RequestMapping("wtgz")
	public ModelAndView wtgz(){
		return new ModelAndView("mobile/wtgz/wtgz");
	}
}
