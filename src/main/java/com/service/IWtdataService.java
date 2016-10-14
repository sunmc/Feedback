package com.service;

import java.util.List;

import com.bean.Wtdata;
import com.util.bean.Result;

public interface IWtdataService {

	public Result<List<Wtdata>> selectByFlag(String flag);
	
	public Result<String> insert(Wtdata wtdata);
}
