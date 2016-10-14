package com.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bean.Wtdata;
import com.bean.Wtdatajson;
import com.service.IWtdataService;
import com.util.bean.Result;

@Controller
@RequestMapping("wtdata")
public class WtdataController {

	@Autowired
	private IWtdataService wtdataService;
	@ResponseBody
	@RequestMapping
	public Result<List<Wtdatajson>> getWtdata(String belong){
		Result<List<Wtdata>> res1 = wtdataService.selectByFlag(belong);
		Result<List<Wtdatajson>> res = new Result<>();
		List<Wtdatajson> data = new ArrayList<>();
		if(res1.isFlag()){
			for(Wtdata d : res1.getData()){
				Wtdatajson wd = new Wtdatajson();
				wd.setValue(d.getValue());
				wd.setText(d.getText());
				data.add(wd);
			}
			res.setFlag(true);
			res.setData(data);
		}else{
			res.setFlag(false);
			res.setMessage(res1.getMessage());
		}
		return res;
	}
}
