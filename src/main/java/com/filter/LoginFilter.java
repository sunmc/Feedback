package com.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qq.weixin.mp.aes.WXService;
import com.qq.weixin.mp.aes.WXService.UserId;
import com.qq.weixin.mp.aes.WXService.UserInfo;
import com.util.StringUtil;

public class LoginFilter implements Filter{

	private String excludedPages;
	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest srequest = (HttpServletRequest)request;
		HttpServletResponse sresponse = (HttpServletResponse)response;
		
		//如果是登录则不拦截
		String path = ((HttpServletRequest) request).getServletPath();
		if(excludedPages.indexOf(path) >=0){
			chain.doFilter(request, response);
			return;
		}
		
		//获取session中的用户
		UserInfo user = (UserInfo)srequest.getSession().getAttribute("user");
		boolean isWX = false;
		if(user == null){
			String code = request.getParameter("code");
			log.debug("code"+code);
			if(!StringUtil.isNullOrEmpty(code)){
				UserId ui = WXService.getUserId(code);
				log.debug("ui"+ui.toString());
				if(!StringUtil.isNullOrEmpty(ui.UserId)){
					user = WXService.getUserInfo(ui.UserId);
					if(user != null){
						srequest.getSession().setAttribute("user", user);
						log.debug("微信用户:" + user.userid + "-" + user.name);
						isWX = true;
					}else{
						log.debug("微信用户信息获取失败");
					}
				}
			}
		}else{
			isWX = true;
		}
		if(!isWX){
			((HttpServletResponse)response).setHeader("Cache-Control", "no-store");  
			((HttpServletResponse)response).setDateHeader("Expires", 0);  
			((HttpServletResponse)response).setHeader("Prama", "no-cache");  
			((HttpServletResponse)response).sendRedirect("/Feedback/User/login.do"); 
		}else{
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		excludedPages = fConfig.getInitParameter("excludedPages");  
		if(excludedPages == null){
			excludedPages = "";
		}
	}

}
