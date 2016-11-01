package com.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("wtsl")
public class WTSLController {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	@RequestMapping
	public ModelAndView index(){
		return new ModelAndView("mobile/wtsl/wtsllist");
	}
	
	@RequestMapping("wtsl")
	public ModelAndView slwt(String instanceid){
		if(instanceid != null && instanceid.length() > 0){
			log.debug(instanceid);
		}
		return new ModelAndView("mobile/wtsl/wtsl");
	}
}
