package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bean.XMXX;
import com.service.IXMXXService;
import com.util.bean.Result;

@Controller
@RequestMapping("xmxx")
public class XMXXController {

	@Autowired
	private IXMXXService xmxxService;
	
	@ResponseBody
	@RequestMapping("searchxm")
	public Result<List<XMXX>> searchXMXX(String text){
		return xmxxService.searchXM(text);
	}
}
