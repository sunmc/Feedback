package com.util;

import java.io.File;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import com.util.bean.Result;


/**
 * 上传工具
 * 
 * @author sunmc
 * @since 2015/11/16
 *
 */
public class UploadUtil {

	// 上传图片
	public static Result<Boolean> uploadImage(String pathname, MultipartFile file) {

		Result<Boolean> res = new Result<Boolean>();
		File targetFile = new File(pathname, file.getOriginalFilename());
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		// 保存
		try {
			file.transferTo(targetFile);
			res.setFlag(true);
			res.setData(true);
		} catch (Exception e) {
			e.printStackTrace();
			res.setFlag(false);
			res.setMessage(e.getMessage());
		}
		return res;
	}

	// 上传文件
	public static Result<String> uploadFile(String userId, MultipartFile file) {
		Result<String> res = new Result<String>();
		// 存储路径
		String path =  DateUtil.nowYMD() + "/" + userId + "a/";
		String fileName = file.getOriginalFilename();
		int index = fileName.lastIndexOf(".");
		String type = null;
		if (index != -1) {
			type = fileName.substring(index);
			fileName = DateUtil.nowLong() + type;
		}
		File targetFile = new File(PathUtil.UP_LOAD_PATH + path, fileName);

		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		// 保存
		try {
			file.transferTo(targetFile);
			res.setFlag(true);
			res.setData(path + fileName);
		} catch (Exception e) {
			e.printStackTrace();
			res.setFlag(false);
			res.setMessage(e.getMessage());
		}
		return res;
	}

	// 上传文件
	public static Result<String> uploadProject(MultipartFile file) {
		Result<String> res = new Result<String>();
		// 存储路径
		String path = "/root/";
		String fileName = file.getOriginalFilename();
		File targetFile = new File(PathUtil.UP_LOAD_PATH + path, fileName);

		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		// 保存
		try {
			file.transferTo(targetFile);
			res.setFlag(true);
			res.setData(path + fileName);
		} catch (Exception e) {
			e.printStackTrace();
			res.setFlag(false);
			res.setMessage(e.getMessage());
		}
		return res;
	}
}
