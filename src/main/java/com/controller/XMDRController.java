package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("xmdr")
public class XMDRController {

	@RequestMapping()
	public ModelAndView index(){
		return new ModelAndView("mobile/admin/xmdr");
	}
}
