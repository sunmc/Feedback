package com;


import java.io.IOException;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;
import com.mapper.InstanceContextMapper;
import com.qq.weixin.mp.aes.WXService;
import com.qq.weixin.mp.aes.bean.Articles;
import com.qq.weixin.mp.aes.bean.NewsMessage;
import com.qq.weixin.mp.aes.bean.UserInfo;
import com.service.IUserService;
import com.service.IWTTCService;
import com.service.IWtdataService;
import com.service.IXMXXService;

@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类  
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})  
public class Test {
	private static Logger logger = Logger.getLogger(Test.class);  
	@Autowired
	public IWtdataService wtdataService;
	Gson gson = new Gson();
	@Autowired
	public IXMXXService xmxxService;
	@Autowired
	public IWTTCService wttcService;
	@Autowired
	public IUserService userService;
	@Autowired
	public InstanceContextMapper instanceMapper;
	@SuppressWarnings({ "unchecked", "unused" })
	@org.junit.Test
	public void Test() throws JsonGenerationException, JsonMappingException, IOException{
//		Result<String> res = new ExcelUtil().readJsonFromExcel("D:/test/jiemi/全钢成型系统事业部.xls");
//		List<User> users = gson.fromJson(res.getData(), new TypeToken<List<User>>(){}.getType());
//		System.out.println(res.getData());
	
	}

	
}
