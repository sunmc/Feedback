package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("wtsl")
public class WTSLController {

	@RequestMapping
	public ModelAndView index(){
		return new ModelAndView("mobile/wtsl/wtsllist");
	}
	
	@RequestMapping("wtsl")
	public ModelAndView slwt(){
		return new ModelAndView("mobile/wtsl/wtsl");
	}
}
