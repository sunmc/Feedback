package com;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bean.Implementation;
import com.bean.ProjectIssueManage;
import com.bean.User;
import com.service.IProjectIssueManageService;

@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类  
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})  
public class Test {
	@Autowired
	public IProjectIssueManageService manageMapper;
	@SuppressWarnings({ "unchecked", "unused" })
	@org.junit.Test
	public void Test() throws JsonGenerationException, JsonMappingException, IOException{
//		Result<String> res = new ExcelUtil().readJsonFromExcel("D:/test/jiemi/全钢成型系统事业部.xls");
//		List<User> users = gson.fromJson(res.getData(), new TypeToken<List<User>>(){}.getType());
//		System.out.println(res.getData());
		ProjectIssueManage p = new ProjectIssueManage();
		List<Implementation> ips = new ArrayList<>();
		Implementation p1 = new Implementation();
		Implementation p2 = new Implementation();
		Implementation p3 = new Implementation();
		p3.setJjcs("123");
		Implementation p4 = new Implementation();
		ips.add(p1);
		ips.add(p2);
		ips.add(p3);
		ips.add(p4);
		p.setImplementations(ips);
		User u = new User();
		manageMapper.wtfp(p, u);
		System.out.println(1);
		
	}
                     
	
}
