package com.service;

import java.util.List;

import com.bean.User;
import com.bean.Wtdata;
import com.util.bean.Result;

public interface IWtdataService {

	public Result<List<Wtdata>> selectByFlag(String flag);
	
	public Result<String> insert(Wtdata wtdata);
	
	public Result<List<String>> selectWtlb();
	
	public Result<List<Wtdata>> insert(List<Wtdata> wts);
	
	public Result<List<Wtdata>> insertOrUpdate(List<Wtdata> wts, User user);
}
