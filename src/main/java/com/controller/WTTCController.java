package com.controller;

import java.io.File;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.bean.WTTC;
import com.qq.weixin.mp.aes.WXService;
import com.qq.weixin.mp.aes.WXService.UserId;
import com.qq.weixin.mp.aes.WXService.UserInfo;
import com.service.IWTTCService;
import com.util.bean.Result;

@Controller
@RequestMapping("wttc")
public class WTTCController {

	@Autowired
	private IWTTCService wttcService;
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping
	public ModelAndView index(String code, String state,HttpSession session){
		log.debug(code + "----" + state);
		log.debug(WXService.token);
		UserInfo ui = WXService.getUserInfo("sunmc");
		UserInfo user = (UserInfo)session.getAttribute(ui.userid);
		if(user == null){
			user = WXService.getUserInfo(ui.userid);
			session.setAttribute(ui.userid, user);
		}
		log.debug(ui.toString());
		ModelAndView mv = new ModelAndView("mobile/wttc/wttc");
		return mv;
	}
	@RequestMapping("submit")
	public ModelAndView submit(WTTC wttc){
		//Result<WTTC> res = wttcService.insert(wttc);
		return null;
	}
	
	
	@RequestMapping(value = "upload", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")  
	public Result<String> uploadFile(MultipartFile file){
		Result<String> res = new Result<String>();
		
		System.out.println("开始");  
        String path = "D:/upload/";  
        String fileName = file.getOriginalFilename();  
//        String fileName = new Date().getTime()+".jpg";  
        System.out.println(path);  
        File targetFile = new File(path, fileName);  
        if(!targetFile.exists()){  
            targetFile.mkdirs();  
        }  
  
        //保存  
        try {  
            file.transferTo(targetFile);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        res.setData(fileName);
		return res;
	}
}
