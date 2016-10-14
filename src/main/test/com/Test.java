package com;


import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bean.XMXX;
import com.service.IWtdataService;
import com.service.IXMXXService;
import com.util.bean.Result;

@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类  
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})  
public class Test {
	private static Logger logger = Logger.getLogger(Test.class);  
	@Autowired
	public IWtdataService wtdataService;
	
	@Autowired
	public IXMXXService xmxxService;
	@SuppressWarnings({ "unchecked", "unused" })
	@org.junit.Test
	public void Test() throws JsonGenerationException, JsonMappingException, IOException{
		
		Result<List<XMXX>> res = xmxxService.searchXM("201407048");
		Result<List<XMXX>> res1 = xmxxService.searchXM("元丰");
		Result<List<XMXX>> res2 = xmxxService.searchXM("导切机系统");
		/*String [] a = new String[]{"按期完成","处理中","拖期完成","拖期未完成"};
		for(int i = 1; i < a.length + 1;i++){
			Wtdata d = new Wtdata();
			d.setCreateby("sunmc");
			d.setCreatetime(new Date());
			d.setDeleteflag(0);
			d.setValue(i);
			d.setText(a[i-1]);
			d.setBelongto("完成情况");
			wtdataService.insert(d);
		}*/
	}

	
}
