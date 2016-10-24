package com.controller;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.qq.weixin.mp.aes.WXService;

@Component
public class TimeTask {

	@Scheduled(cron="0 0/90 * * * ? ") //间隔5秒执行
	public void getWXToken(){
		WXService.getToken();
	}
}
