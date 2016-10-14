package com.controller;

import java.io.File;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.util.bean.Result;

@Controller
@RequestMapping("wttc")
public class WTTCController {

	
	@RequestMapping
	public ModelAndView index(){
		ModelAndView mv = new ModelAndView("mobile/wttc/wttc");
		return mv;
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
