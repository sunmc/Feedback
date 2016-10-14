package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("wtjj")
public class WTJJController {

	@RequestMapping
	public ModelAndView index(){
		return new ModelAndView("mobile/wtjj/wtjjlist");
	}
	@RequestMapping("wtjj")
	public ModelAndView jjwt(){
		return new ModelAndView("mobile/wtjj/wtjj");
	}
}
