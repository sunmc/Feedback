package com;


import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bean.WorkItem;
import com.mapper.WorkItemMapper;

@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类  
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})  
public class Test {
	@Autowired
	public WorkItemMapper manageMapper;
	@SuppressWarnings({ "unchecked", "unused" })
	@org.junit.Test
	public void Test() throws JsonGenerationException, JsonMappingException, IOException{
//		Result<String> res = new ExcelUtil().readJsonFromExcel("D:/test/jiemi/全钢成型系统事业部.xls");
//		List<User> users = gson.fromJson(res.getData(), new TypeToken<List<User>>(){}.getType());
//		System.out.println(res.getData());
		String objectid = "AC34D2E2-AF46-416B-AB71-F990BED1A53D";
		List<WorkItem> wis = manageMapper.selectWorkFlowStatus(objectid);
		System.out.println("");
	}
                     
	
}
