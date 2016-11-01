package com.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bean.InstanceContext;
import com.bean.Post;
import com.bean.User;
import com.bean.WTTC;
import com.bean.WorkItem;
import com.mapper.InstanceContextMapper;
import com.mapper.PostMapper;
import com.mapper.WTTCMapper;
import com.mapper.WorkItemMapper;
import com.qq.weixin.mp.aes.WXService;
import com.qq.weixin.mp.aes.bean.Articles;
import com.qq.weixin.mp.aes.bean.NewsMessage;
import com.service.IWTTCService;
import com.util.bean.Common;
import com.util.bean.Result;

@Service
public class WTTCService implements IWTTCService{

	@Autowired
	private WTTCMapper wttcMapper;
	@Autowired
	private WorkItemMapper workItemMapper;
	@Autowired
	private InstanceContextMapper instanceMapper;
	@Autowired
	private PostMapper postMapper;
	@Override
	public Result<WTTC> insert(WTTC wttc, User user) {
		Result<WTTC> res = new Result<WTTC>();
		Date now = new Date();
		String ymd = new SimpleDateFormat("yyyyMMdd").format(now);
		try{
			//存储问题明细
			String id = instanceMapper.getID();
			wttc.setObjectid(id);
			wttcMapper.insert(wttc);
			wttc.setCreateby(user.getZh());
			wttc.setCreatetime(now);
			//流程实例生成instancecontext
			String instanceid = instanceMapper.getID();
			InstanceContext instanceContext = createInstance(wttc, user, now, ymd, id, instanceid, 0);
			instanceMapper.insertSelective(instanceContext);
			//流程节点生成workitem(问题提出)
			WorkItem workItem = createWorkItem(user.getObjectid(),user.getObjectid(),user.getObjectid(), now, id, instanceid, 0, 2, "WTTC", Common.wttc, "XMWTGL");
			workItemMapper.insertSelective(workItem);
			//流程节点生成workitem(问题判定)
			List<Post> posts = postMapper.getPostByCode("WTSL");
			for(Post p : posts){
				WorkItem wi = createWorkItem(user.getObjectid(),p.getOwner(),null, now, id, instanceid, 0, 1, "WTSL", Common.wtsl, "XMWTGL");
				workItemMapper.insertSelective(wi);
				//给流程接收人发微信
				NewsMessage nm = new NewsMessage();
				Articles articles = new Articles();
				articles.title = "您有新的待阅读任务";
				articles.description = "[项目编号:" + wttc.getXmbh() + "][产品名称:"+wttc.getCpmc() + "][" + instanceContext.getSequnceno() + "][发起人:" + user.getXm()+"]";
				articles.url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx46be78f8feff44a2&redirect_uri=http://bpm.mesnac.com:8087/Feedback/wtsl/wtsl.do?instanceid="+instanceid+"&response_type=code&scope=snsapi_base&state=DefaultEngine#wechat_redirect";
				nm.news.articles.add(articles);
				nm.touser = user.getZh();
				nm.msgtype = "news";
				nm.agentid = 27;
				WXService.sendNewsMessage(nm);
			}
			res.setFlag(true);
			res.setData(wttc);
//			System.out.println(id);
		}catch(Exception e){
			res.setFlag(false);
			res.setMessage(e.getMessage());
		}
		return res;
	}
	private InstanceContext createInstance(WTTC wttc, User user, Date now, String ymd, String id, String instanceid, int state) {
		InstanceContext instanceContext = new InstanceContext();
		//流水号生成
		Integer order = instanceMapper.getOrderNumber(InstanceContextMapper.schemaCode, ymd);
		if(order == null){
			order = 1;
		}else{
			order++;
		}
		instanceMapper.updateOrderNumber(InstanceContextMapper.schemaCode, ymd, order);
		String orders = order + "";
		while(orders.length() < 4){
			orders = "0" + orders;
		}
		String cpm = wttc.getWlbm();
		if(cpm != null && cpm.length() >= 2){
			cpm = cpm.substring(0, 2);
		}else{
			cpm = "fb";
		}
		orders = cpm + ymd + orders;
		instanceContext.setBizobjectid(id);
		instanceContext.setObjectid(instanceid);
		instanceContext.setCreatedtime(now);
		instanceContext.setOrgunit(user.getSzbm());
		instanceContext.setOriginator(user.getObjectid());
		instanceContext.setSequnceno(orders);
		instanceContext.setStarttime(now);
		instanceContext.setState(state);
		return instanceContext;
	}
	private WorkItem createWorkItem(String originator, String receiver, String finisher, Date now, String id, String instanceid, int eithororall, int state, String activitycode, String activityname, String schema) {
		String wiObjectId = instanceMapper.getID();
		WorkItem workItem = new WorkItem();
		workItem.setActivitycode(activitycode);
		workItem.setActivityname(activityname);
		workItem.setBizobjectid(id);
		workItem.setBizobjectschemacode(schema);
		workItem.setCreatetime(now);
		workItem.setEitherorall(eithororall);
		workItem.setFinisher(finisher);
		workItem.setFinishtime(now);
		workItem.setInstanceid(instanceid);
		workItem.setObjectid(wiObjectId);
		workItem.setOriginator(originator);
		workItem.setReceiver(receiver);
		workItem.setReceivetime(now);
		workItem.setStarttime(now);
		workItem.setState(state);
		return workItem;
	}

}
