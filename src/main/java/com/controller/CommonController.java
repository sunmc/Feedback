package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.util.bean.Common;

@Controller
@RequestMapping("common")
public class CommonController  extends BaseController{

	@RequestMapping()
	public ModelAndView index(){
		return new ModelAndView("mobile/a6wtcx/wtcxlist");
	}
	
	@RequestMapping("test")
	public ModelAndView test(){
		return new ModelAndView("mobile/success");
	}
	
	@RequestMapping("setWidth")
	public String setWidth(int width){
		if(width != 0){
			Common.width = width;
			return "已经修改为" + width;
		}
		return "修改失败，请输入宽度";
	}
}
