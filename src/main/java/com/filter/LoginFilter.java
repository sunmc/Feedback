package com.filter;

import java.io.IOException;
import java.net.URLEncoder;

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

import com.bean.User;
import com.google.gson.Gson;
import com.qq.weixin.mp.aes.WXService;
import com.qq.weixin.mp.aes.bean.UserId;
import com.qq.weixin.mp.aes.bean.UserInfo;
import com.service.IUserService;
import com.service.impl.UserService;
import com.util.SpringContextUtil;
import com.util.StringUtil;
import com.util.bean.Result;

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
		String params = ((HttpServletRequest) request).getQueryString(); 
		if(excludedPages.indexOf(path) >=0){
			chain.doFilter(request, response);
			return;
		}
		
		//获取session中的用户
		User user = (User)srequest.getSession().getAttribute("user");
		boolean isWX = false;
		if(user == null){
			//
			String zh = request.getParameter("zh");
			if(!StringUtil.isNullOrEmpty(zh)){
				UserInfo wxuser = WXService.getUserInfo(zh);
				Gson gson = new Gson();
				log.debug("wxuser:" + gson.toJson(wxuser));
				if(wxuser != null){
					IUserService userService = (UserService)SpringContextUtil.getBean("userService");
					Result<User> res = userService.syncWXUser(wxuser);
					if(res.isFlag()){ 
						srequest.getSession().setAttribute("user", res.getData());
						log.debug("微信用户:" + wxuser.userid + "-" + wxuser.name);
					}
					isWX = true;
				}else{
					log.debug("微信用户信息获取失败");
				}
			}
			//
			String code = request.getParameter("code");
			log.debug("code"+code);
			if(!StringUtil.isNullOrEmpty(code)){
				UserId ui = WXService.getUserId(code);
				log.debug("ui"+ui.toString());
				if(!StringUtil.isNullOrEmpty(ui.UserId)){
					UserInfo wxuser = WXService.getUserInfo(ui.UserId);
					Gson gson = new Gson();
					log.debug("wxuser:" + gson.toJson(wxuser));
					if(wxuser != null){
						IUserService userService = (UserService)SpringContextUtil.getBean("userService");
						Result<User> res = userService.syncWXUser(wxuser);
						if(res.isFlag()){ 
							srequest.getSession().setAttribute("user", res.getData());
							log.debug("微信用户:" + wxuser.userid + "-" + wxuser.name);
						}
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
			((HttpServletResponse)response).sendRedirect("/Feedback/User/login.do?url="+URLEncoder.encode(path + "?" + params)); 
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
