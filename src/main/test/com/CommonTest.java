package com;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.qq.weixin.mp.aes.WXService;
import com.qq.weixin.mp.aes.bean.Articles;
import com.qq.weixin.mp.aes.bean.NewsMessage;

public class CommonTest {

	public static void main(String []args){
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
		String cpm = "S-2016070023";
		cpm = cpm.substring(6,8);
		System.out.println(cpm);
	}
}
