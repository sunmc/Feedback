package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.util.UploadUtil;
import com.util.bean.Result;

@Controller
@RequestMapping("/upload")
public class UploadController  extends BaseController{

	// 上传文件
	@ResponseBody
	@RequestMapping(value = "/file", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public Result<String> uploadFile(@RequestParam(value = "file", required = true) MultipartFile file,
			@RequestParam(value = "userId", required = false) String userId) {
		Result<String> res = UploadUtil.uploadFile(userId, file);
		return res;
	}

	// 上传工程
	@RequestMapping(value = "/project", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public Result<String> uploadProject(@RequestParam(value = "file", required = true) MultipartFile file,
			@RequestParam(value = "userId", required = false) String userId) {
		ModelAndView mv = new ModelAndView("/admin/upload/UploadSuccess");
		Result<String> res = UploadUtil.uploadProject(file);
		mv.addObject("success", res.getData());
		return res;
	}

	// 上传图片 不改名
	@RequestMapping(value = "/image", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public ModelAndView uploadFileN(@RequestParam(value = "file", required = true) MultipartFile file) {
		ModelAndView mv = new ModelAndView("/admin/upload/UploadSuccess");
		Result<Boolean> res = UploadUtil.uploadImage("/home/zq/resources/upload", file);
		mv.addObject("success", res.getData());
		return mv;
	}

	@RequestMapping("/upload")
	public ModelAndView upload() {
		ModelAndView mv = new ModelAndView("uploadProject");
		mv.addObject("uploadProjectPath", "http://localhost:8080/Feedback/upload/project.do");
		return mv;
	}
}
