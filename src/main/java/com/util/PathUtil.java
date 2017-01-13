package com.util;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

public class PathUtil {

	//文件上传存储路径
//	public static final String UP_LOAD_PATH = "D:/03 项目工程/03 eclipse/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/Feedback/resource/images/";
	public static final String UP_LOAD_PATH = "F:/Feedbackupload/resources/";
	public static final String HOST = "//172.16.77.150/import/";
	public static final String DRWJ_PATH = "F:/Feedbackupload/import/";
	public static final String WXStartUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx46be78f8feff44a2&redirect_uri=";
	public static final String WXEndUrl = "&response_type=code&scope=snsapi_base&state=DefaultEngine#wechat_redirect";
	public static String getBasePath(HttpServletRequest request){
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":"
				+ request.getServerPort() + path;
		return basePath;
	}
	public static String getWXUrl(String url){
		url = URLEncoder.encode(url);
		url = WXStartUrl + url + WXEndUrl;
		return url;
	}
}
