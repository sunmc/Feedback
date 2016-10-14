package com.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bean.Wtdata;
import com.mapper.WtdataMapper;
import com.service.IWtdataService;
import com.util.bean.Result;

@Service
public class WtdataService implements IWtdataService {

	@Autowired
	private WtdataMapper wtdataMapper;
	@Override
	public Result<List<Wtdata>> selectByFlag(String flag) {
		Result<List<Wtdata>> res = new Result<>();
		try{
			List<Wtdata> data = wtdataMapper.selectByFlag(flag);
			res.setData(data);
			res.setFlag(true);
		}catch(Exception e){
			res.setFlag(false);
			res.setMessage(e.getMessage());
		}
		return res;
	}

	@Override
	public Result<String> insert(Wtdata wtdata) {
		Result<String> res = new Result<String>();
		try{
			wtdataMapper.insert(wtdata);
			res.setData("添加成功");
			res.setFlag(true);
		}catch(Exception e){
			res.setFlag(false);
			res.setMessage(e.getMessage());
		}
		return res;
	}

}
