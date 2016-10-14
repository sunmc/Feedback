package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("wtfx")
public class WTFXController {

	@RequestMapping
	public ModelAndView index(){
		return new ModelAndView("mobile/wtfx/wtfxlist");
	}
	
	@RequestMapping("wtfx")
	public ModelAndView wtfx(){
		return new ModelAndView("mobile/wtfx/wtfx");
	}
}
