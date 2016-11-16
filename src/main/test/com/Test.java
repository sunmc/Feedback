package com;


import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mapper.ProjectIssueManageMapper;
import com.qq.weixin.mp.aes.WXService;
import com.qq.weixin.mp.aes.bean.Articles;
import com.qq.weixin.mp.aes.bean.NewsMessage;

@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类  
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})  
public class Test {
	@Autowired
	public ProjectIssueManageMapper manageMapper;
	@SuppressWarnings({ "unchecked", "unused" })
	@org.junit.Test
	public void Test() throws JsonGenerationException, JsonMappingException, IOException{
//		Result<String> res = new ExcelUtil().readJsonFromExcel("D:/test/jiemi/全钢成型系统事业部.xls");
//		List<User> users = gson.fromJson(res.getData(), new TypeToken<List<User>>(){}.getType());
//		System.out.println(res.getData());
		NewsMessage nm = new NewsMessage();
		Articles articles = new Articles();
		articles.title = "您有新的待判定问题";
		articles.description = "ceshi ";
		articles.url = "bpm.mesnac.com";
		nm.news.articles.add(articles);
		nm.touser = "zhangpeipei";
		nm.msgtype = "news";
		nm.agentid = 27;
		WXService.sendNewsMessage(nm);
		System.out.println("");
	}
                     
	
}
