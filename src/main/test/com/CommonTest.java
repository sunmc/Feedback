package com;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import com.bean.ProjectIssueManage;

public class CommonTest {

	public static void main(String []args) throws UnsupportedEncodingException{
		//给流程接收人发微信
//		NewsMessage nm = new NewsMessage();
//		Articles articles = new Articles();
//		articles.title = "您有新的待阅读任务";
//		articles.description = "[项目编号:123][产品名称:123][123][发起人:123]";
//		articles.url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx46be78f8feff44a2&redirect_uri=bpm.mesnac.com:8087/Feedback/wttc.do&response_type=code&scope=snsapi_base&state=DefaultEngine#wechat_redirect";
//		nm.news.articles.add(articles);
//		nm.touser = "sunmc";
//		nm.msgtype = "news";
//		nm.agentid = 27;
//		String res = WXService.sendNewsMessage(nm);
//		System.out.println(res);
		
//		DgsData data = new DgsData("admin", "a1a97d559f25c58a1b7377a562d674ef", "C:\\test\\测试.xlsx", "C:\\test\\解密\\测试.xlsx", "1", "BPM", "BPIT0304");
//		Gson gson = new Gson();
//		String datas = gson.toJson(data);
//		String s = HttpUtil.sendPost("http://172.16.251.141:8100/DGS/dg/interface/dgDecrypt", datas);
//		s=s+"";
		
//		PDMMapper pdm = new PDMMapper();
//		pdm.selectByWth("123");
		
//		SAPUtil sap = new SAPUtil();
//		sap.execute("ZBPM_GET_T001W_INFO", new HashMap<String, String>());
		ProjectIssueManage project = new ProjectIssueManage();
		String a = "aaa";
		project.setZrr(a);
		a = "bbb";
		project.setFazxr(a);
		System.out.println(project.getZrr() + ":" + project.getFazxr());
	}
}
