package com.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bean.User;
import com.bean.Wtdata;
import com.bean.WtdataList;
import com.bean.Wtdatajson;
import com.service.IWtdataService;
import com.util.bean.Result;

@Controller
@RequestMapping("wtdata")
public class WtdataController  extends BaseController{

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
	@ResponseBody
	@RequestMapping("getwd")
	public Result<List<Wtdata>> getWtdata2(String belong){
		Result<List<Wtdata>> res = wtdataService.selectByFlag(belong);
		return res;
	}
	@RequestMapping("edit")
	public ModelAndView editWtdata(){
		ModelAndView model = new ModelAndView("mobile/admin/wtdata/edit");
		return model;
	}
	
	@ResponseBody
	@RequestMapping("wtlb")
	public Result<List<String>> getWtlb(){
		Result<List<String>> res = wtdataService.selectWtlb();
		return res;
	}
	@ResponseBody
	@RequestMapping("save")
	public Result<List<Wtdata>> saveData(WtdataList wtdataList, HttpSession session){
		User user = (User)session.getAttribute("user");
		Result<List<Wtdata>> res = wtdataService.insertOrUpdate(wtdataList.getWds(), user);
		return res;
	}
	
}
