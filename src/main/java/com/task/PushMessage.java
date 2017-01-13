package com.task;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.bean.OverTaskParam;
import com.bean.User;
import com.qq.weixin.mp.aes.WXService;
import com.qq.weixin.mp.aes.bean.Articles;
import com.qq.weixin.mp.aes.bean.NewsMessage;
import com.service.IProjectIssueManageService;
import com.service.IUserService;
import com.util.PathUtil;
import com.util.StringUtil;
import com.util.bean.Result;

@Component
public class PushMessage {
	
	@Autowired
	private IProjectIssueManageService projectService; 
	@Autowired
	private IUserService userService;

	  /**  
     * 定时计算。每天早 08:30 执行一次  
     */    
    @Scheduled(cron = "0 30 8 * * *")   
    public void show830(){  
    	projectService.sendDBTQ();  
    	projectService.sendTQWT();
    }  
    /**  
     * 定时计算。每天早 08:30 执行一次  
     */    
    @Scheduled(cron = "0 0 13 * * *")   
    public void show13(){  
    	projectService.sendDBTQ();  
    	projectService.sendTQWT();
    }   
    /**  
     * 心跳更新。启动时执行一次，之后每隔2秒执行一次  
     */    
    @Scheduled(fixedRate = 10000*7100)   
    public void print(){  
    	//更新微信token和ticket
    	WXService.getToken();
    	WXService.getTicket();
    }  
    
    
}
