package com;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bean.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mapper.ImplementationMapper;
import com.mapper.InstanceContextMapper;
import com.mapper.PostMapper;
import com.mapper.ProjectIssueManageMapper;
import com.mapper.UserMapper;
import com.mapper.WorkItemMapper;
import com.mapper.WtdataMapper;
import com.service.impl.ProjectIssueManageService;
import com.util.ExcelUtil;
import com.util.bean.Result;

@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类  
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})  
public class Test {
	@Autowired
	public UserMapper userMapper;
	@Autowired
	public WtdataMapper wtdataMapper;
	@Autowired
	public ProjectIssueManageMapper projectMapper;
	@Autowired
	public WorkItemMapper workItemMapper;
	@Autowired
	public InstanceContextMapper instanceMapper;
	@Autowired
	public PostMapper postMapper;
	@Autowired
	public ImplementationMapper implementationMapper;
	@Autowired
	public ProjectIssueManageService projectService;
	
	@SuppressWarnings({ "unchecked", "unused" })
	public static List<String> users = new ArrayList<>();
	@org.junit.Test
	public void Test() throws JsonGenerationException, JsonMappingException, IOException{
		Gson gson = new Gson();
		Result<String> res = new ExcelUtil().readJsonFromExcel("E:/03 项目工程/04 工作任务/33 全钢问题处理系统/制造中心.xls");
		List<User> users = gson.fromJson(res.getData(), new TypeToken<List<User>>(){}.getType());
		for(User user : users){
			String id = instanceMapper.getID();
			user.setCreateby("sunmc");
			user.setCreatetime(new Date());
			user.setState(0);
			user.setDelflag(0);
			user.setRole(0);
			user.setObjectid(id);
			userMapper.insertSelective(user);
		}
		System.out.println(res.getData());
		
//		SAPUtil sap = new SAPUtil();
//		Map<String, String> paramIn = new HashMap<>();
//		paramIn.put("I_MATNR", "020701040100000");
//		sap.execute("ZCO_GET_MATE_INFO", paramIn);
		
//		List<DA02Data> res = manageMapper.selectDA02Data("14160058");
//		String lsh = res.get(0).getDJBH();
//		List<WorkItem> wis = manageMapper.getDA02WorkItem(lsh);
//		System.out.println();
		//projectService.importProject("测试导入.xlsx");
	}
                     
	
}
