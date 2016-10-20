package com.util;

import javax.servlet.http.HttpServletRequest;

public class PathUtil {

	//文件上传存储路径
//	public static final String UP_LOAD_PATH = "D:/03 项目工程/03 eclipse/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/Feedback/resource/images/";
	public static final String UP_LOAD_PATH = "F:/Feedbackupload/resources/";
	public static String getBasePath(HttpServletRequest request){
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":"
				+ request.getServerPort() + path;
		return basePath;
	}
}
