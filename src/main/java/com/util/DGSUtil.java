package com.util;

import com.bean.DgsData;
import com.google.gson.Gson;
import com.util.bean.Result;

public class DGSUtil {

	public static Result<String> DECFile(String srcFile, String dstFile){
		Result<String> res = new Result<String>();
		DgsData data = new DgsData("bpmjiemi", "a1a97d559f25c58a1b7377a562d674ef", srcFile, dstFile, 1,"00000000", "Feedback", "123");
		Gson gson = new Gson();
		String datas = gson.toJson(data);
		System.out.println(datas);
		String s = HttpUtil.sendPost("http://172.16.251.141:8100/DGS/dg/interface/dgDecrypt", datas);
		if(s.contains("\"success\":true")){
			res.setFlag(true);
		}else{
			res.setFlag(false);
			res.setMessage(s);
		}
		return res;
	}
}
