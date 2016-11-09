package com.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.bean.ProjectIssueManage;
import com.bean.User;
import com.service.IProjectIssueManageService;
import com.util.bean.Result;

@Controller
@RequestMapping("wttc")
public class WTTCController {

	@Autowired
	private IProjectIssueManageService projectService;
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping
	public ModelAndView index(String code, String state,HttpSession session){
		
		ModelAndView mv = new ModelAndView("mobile/wttc/wttc");
		return mv;
	}
	@RequestMapping("submit")
	public ModelAndView submit(ProjectIssueManage project, HttpSession session){
		User user = (User)session.getAttribute("user");
		Result<ProjectIssueManage> res = projectService.insert(project, user);
		return null;
	}
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
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
