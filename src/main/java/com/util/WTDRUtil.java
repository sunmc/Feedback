package com.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import com.Test;
import com.bean.ProjectIssueManage;
import com.bean.User;
import com.util.bean.Result;

public class WTDRUtil {

	public static Result<List<ProjectIssueManage>> wtdr(Map<Integer,Map<Integer, Object>> wts,Map<String,User> userMap,Map<String,String> dataMap){
		Result<List<ProjectIssueManage>> res = new Result<>();
		List<ProjectIssueManage> data = new ArrayList<>();
		String wxcjmds_zyq = "重信;云发;达伟;创新;澳斯特;龙益;盛泰;德瑞高;浩瀚;春江;博朗克;珺信;三富;思达瑞;海英特;诺鼎;海意;欧普特;顺达;中锐聚合;鲁海;嘉润田;青岛生建;张家口;透平;青起;君联;普阳";
		String wxcjmds_gly = "金文科技;澳星";
		for(int i = 2 ; i <= wts.size(); i++){
			Map<Integer, Object> wt = wts.get(i);
			ProjectIssueManage project = new ProjectIssueManage();
			project.setXh(Integer.parseInt((String)wt.get(0)));//序号
			Date tbrq = null;//提报日期
			try{
				tbrq = (Date)wt.get(1);
			}catch(Exception e){
				String s = wt.get(1).toString();
				if(wt.get(1) != null && !!StringUtil.isNullOrEmpty(s)){
					res.setFlag(false);
					res.setMessage("第" + i + "行提日期格式错误：【" + tbrq +"】！，请修改日期格式为2016-12-12这样的格式");
					return res;
				}
			}
			project.setTbrq(tbrq);
			project.setKhmc((String)wt.get(2));//客户名称
			project.setCpmc((String)wt.get(3));//产品名称
			project.setXmbh((String)wt.get(4));//项目编号
			String tbr = (String)wt.get(5);//提报人
			tbr = tbr.trim();
			if(userMap.containsKey(tbr)){
				User utbr = userMap.get(tbr);
				tbr = utbr.getObjectid();
				project.setFqr(utbr);
				project.setCreateby(tbr);
				if(tbrq!=null){
					project.setCreatetime(tbrq);
				}else{
					project.setCreatetime(new Date());
				}
				
			}else{
				if(!Test.users.contains(tbr)){
					Test.users.add(tbr);
				}
				res.setFlag(false);
				res.setMessage("第" + i + "行提报人：【" + tbr+"】不存在！，请维护用户");
				return res;
			}
			project.setTbr(tbr);
			String xmjd = (String)wt.get(6);//项目阶段
			if(dataMap.containsKey(xmjd)){
				xmjd = dataMap.get(xmjd);
			}else if(!StringUtil.isNullOrEmpty(xmjd)){
				xmjd = "其他";
			}
			project.setXmjd(xmjd);
			String jjcd = (String)wt.get(7);//紧急程度
			System.out.println(jjcd + i);
			if(!StringUtil.isNullOrEmpty(jjcd) && dataMap.containsKey(jjcd)){
				jjcd = dataMap.get(jjcd);
			}else if(!StringUtil.isNullOrEmpty(jjcd)){
				jjcd = "1";
			}
			project.setJjcd(jjcd);
			project.setBtmc((String)wt.get(8));//部套名称
			project.setWtjth((String)wt.get(9));//问题件图号
			project.setWtms((String)wt.get(10));//问题描述
			String zrlb = (String)wt.get(12);//责任类别
			if(!StringUtil.isNullOrEmpty(zrlb) && dataMap.containsKey(zrlb)){
				zrlb = dataMap.get(zrlb);
			}else if(!StringUtil.isNullOrEmpty(zrlb)){
				zrlb = "其他";
			}
			project.setZrlb(zrlb);
			String wtlb = (String)wt.get(13);//问题类别
			if(!StringUtil.isNullOrEmpty(wtlb) && dataMap.containsKey(wtlb)){
				wtlb = dataMap.get(wtlb);
			}else if(!StringUtil.isNullOrEmpty(wtlb)){
				wtlb = "其他";
			}
			project.setWtlb(wtlb);//问题类别
			project.setYyfx((String)wt.get(14));//原因分析
			project.setClfa((String)wt.get(15));//处理方案
			String zrr = (String)wt.get(16);//责任人
			zrr = zrr.trim();
			if(userMap.containsKey(zrr)){
				zrr = userMap.get(zrr).getObjectid();
			}else{
				if(wxcjmds_zyq.contains(zrr)){
					project.setWxcj(zrr);
					zrr = userMap.get("张玥琪").getObjectid();
				}else if(wxcjmds_gly.contains(zrr)){
					project.setWxcj(zrr);
					zrr = userMap.get("顾利英").getObjectid();
				}
				else{
					res.setFlag(false);
					res.setMessage("第" + i + "行责任人：【" + zrr+"】不存在！，请维护用户");
					return res;
				}
				
			}
			project.setZrr(zrr);
			String fazxr = (String)wt.get(17);//方案执行人
			fazxr = fazxr.trim();
			if(userMap.containsKey(fazxr)){
				fazxr = userMap.get(fazxr).getObjectid();
			}else{
				if(!StringUtil.isNullOrEmpty(fazxr)){
					if(wxcjmds_zyq.contains(fazxr)){
						project.setWxcj(fazxr);
						fazxr = userMap.get("张玥琪").getObjectid();
					}else if(wxcjmds_gly.contains(fazxr)){
						project.setWxcj(fazxr);
						fazxr = userMap.get("顾利英").getObjectid();
					}else{
						res.setFlag(false);
						res.setMessage("第" + i + "行方案执行人：【" + fazxr+"】不存在！，请维护用户");
						return res;	
					}
									
				}
			}
			project.setFazxr(fazxr);
			Date yqwcsj = null;//要求完成时间
			try{
				yqwcsj = (Date)wt.get(18);
			}catch(Exception e){
				String s = wt.get(18).toString();
				if(wt.get(18) != null && !StringUtil.isNullOrEmpty(s)){
					res.setFlag(false);
					res.setMessage("第" + i + "行要求完成时间格式错误：【" + yqwcsj +"】！，请修改日期格式为2016-12-12这样的格式");
					return res;
				}
			}
			project.setYqwcsj(yqwcsj);
			Date sjwcsj = null;//实际完成时间
			try{
				sjwcsj = (Date)wt.get(19);
			}catch(Exception e){
				String s = wt.get(19).toString();
				if(wt.get(19) != null && !StringUtil.isNullOrEmpty(s)){
					res.setFlag(false);
					res.setMessage("第" + i + "行实际完成时间格式错误：【" + sjwcsj +"】！，请修改日期格式为2016-12-12这样的格式");
					return res;
				}
			}
			project.setSjwcsj(sjwcsj);
			project.setWcqk((String)wt.get(20));//完成情况
			project.setLsjdh((String)wt.get(21));//临时件单号
			project.setSfbgtz((String)wt.get(22));//是否变更图纸
			String tzbgzrr = (String)wt.get(23);//图纸变更责任人
			if(userMap.containsKey(tzbgzrr)){
				tzbgzrr = userMap.get(tzbgzrr).getObjectid();
			}else{
				if(!StringUtil.isNullOrEmpty(tzbgzrr)){
					res.setFlag(false);
					res.setMessage("第" + i + "行图纸变更责任人：【" + tzbgzrr+"】不存在！，请维护用户");
					return res;
				}
				
			}
			project.setTzbgzrr(tzbgzrr);
			Date sjcnbgsj = null;//设计承诺变更时间
			try{
				sjcnbgsj = (Date)wt.get(24);
			}catch(Exception e){
				String s = wt.get(24).toString();
				if(wt.get(24) != null && !StringUtil.isNullOrEmpty(s)){
					res.setFlag(false);
					res.setMessage("第" + i + "行设计承诺变更时间格式错误：【" + sjcnbgsj +"】！，请修改日期格式为2016-12-12这样的格式");
					return res;
				}
			}
			project.setSjcnbgsj(sjcnbgsj);
			Date sjtzbgsj = null;//实际图纸变更时间
			try{
				sjtzbgsj = (Date)wt.get(25);
			}catch(Exception e){
				String s = wt.get(25).toString();
				if(wt.get(25) != null && !StringUtil.isNullOrEmpty(s)){
					res.setFlag(false);
					res.setMessage("第" + i + "行实际图纸变更时间格式错误：【" + sjtzbgsj +"】！，请修改日期格式为2016-12-12这样的格式");
					return res;
				}
			}
			project.setSjtzbgsj(sjtzbgsj);
			project.setSjtzbgdh((String)wt.get(26));//设计图纸变更单号
			project.setBgwcqk((String)wt.get(27));//变更完成情况
			project.setCpjx((String)wt.get(28));//产品机型 
			project.setWtsfcffs((String)wt.get(29));//问题是否重复发生
			project.setSjqd((String)wt.get(30));//收集渠道
			String sl = (String)wt.get(31);
			if(!StringUtil.isNullOrEmpty(sl)){
				try{
					project.setWts(Integer.parseInt(sl));//问题数
				}catch(Exception e){
					
				}
			}
			project.setBz((String)wt.get(32));//备注
			data.add(project);
		}
		res.setFlag(true);
		res.setData(data);
		return res;
	}

	private static Date rqzh(String rq) {
		System.out.println(rq);
		Date tbrq = null;
		int year = 2016,month = 1, day = 1;
		if(StringUtil.isNullOrEmpty(rq) || rq.trim().length() < 4){
			return null;
		}
		try{
			if(rq.contains("/")){
				if(rq.length() > 9){
					year = Integer.parseInt(rq.substring(0, rq.indexOf("/")));
					month = Integer.parseInt(rq.substring(rq.indexOf("/") + 1, rq.lastIndexOf("/")));
					day = Integer.parseInt(rq.substring(rq.lastIndexOf("/") + 1));
				}else{
					year = 2016;
					month = Integer.parseInt(rq.substring(0, rq.indexOf("/")));
					day = Integer.parseInt(rq.substring(rq.lastIndexOf("/") + 1));
				}
			}else if(rq.contains("月") && rq.contains("日")){
				month = Integer.parseInt(rq.substring(0, rq.indexOf("月")));
				day = Integer.parseInt(rq.substring(rq.indexOf("月") + 1, rq.indexOf("日")));
			}else if(rq.contains(".")){
				year = Integer.parseInt(rq.substring(0, rq.indexOf(".")));
				month = Integer.parseInt(rq.substring(rq.indexOf(".") + 1, rq.lastIndexOf(".")));
				day = Integer.parseInt(rq.substring(rq.lastIndexOf(".") + 1));
			}else{
				try{
					int d = Integer.parseInt(rq);
					int c = d - 42703;//42703是11月29日
					GregorianCalendar gc = new GregorianCalendar();
					gc.set(Calendar.YEAR,2016);//设置年
					gc.set(Calendar.MONTH, 10);//这里0是1月..以此向后推
					gc.set(Calendar.DAY_OF_MONTH, 29);//设置天
					gc.add(Calendar.DAY_OF_YEAR, c);
					return gc.getTime();
				}catch(Exception e){
					
				}
			}
			tbrq = new Date();
			GregorianCalendar gc = new GregorianCalendar();
			gc.set(Calendar.YEAR,year);//设置年
			gc.set(Calendar.MONTH, month);//这里0是1月..以此向后推
			gc.set(Calendar.DAY_OF_MONTH, day);//设置天
			tbrq = gc.getTime();
		}catch(Exception e){
			
		}
		return tbrq;
	}
}
