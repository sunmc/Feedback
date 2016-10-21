package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("wtcx")
@Controller
public class WTCXController {

	@RequestMapping()
	public ModelAndView index(){
		return new ModelAndView("mobile/wtcx/wtcx");
	}
}
