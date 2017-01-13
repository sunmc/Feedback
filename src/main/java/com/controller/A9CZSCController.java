package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("help")
public class A9CZSCController {

	@RequestMapping
	public ModelAndView index(){
		ModelAndView model = new ModelAndView("mobile/a9help/czsc");
		return model;
	}
	@RequestMapping("wttc")
	public ModelAndView wttc(){
		ModelAndView model = new ModelAndView("mobile/a9help/wttc");
		return model;
	}
	@RequestMapping("wtpd")
	public ModelAndView wtpd(){
		ModelAndView model = new ModelAndView("mobile/a9help/wtpd");
		return model;
	}
	@RequestMapping("wtcl")
	public ModelAndView wtcl(){
		ModelAndView model = new ModelAndView("mobile/a9help/wtcl");
		return model;
	}
	@RequestMapping("wtjj")
	public ModelAndView wtjj(){
		ModelAndView model = new ModelAndView("mobile/a9help/wtjj");
		return model;
	}
	@RequestMapping("wtgb")
	public ModelAndView wtgb(){
		ModelAndView model = new ModelAndView("mobile/a9help/wtgb");
		return model;
	}
	@RequestMapping("wtcx")
	public ModelAndView wtcx(){
		ModelAndView model = new ModelAndView("mobile/a9help/wtcx");
		return model;
	}
	@RequestMapping("wtts")
	public ModelAndView wtts(){
		ModelAndView model = new ModelAndView("mobile/a9help/wtts");
		return model;
	}
}
