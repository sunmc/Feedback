package com.service.impl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bean.User;
import com.bean.Wtdata;
import com.mapper.InstanceContextMapper;
import com.mapper.WtdataMapper;
import com.service.IWtdataService;
import com.util.StringUtil;
import com.util.bean.Result;

@Service
public class WtdataService implements IWtdataService {

	@Autowired
	private WtdataMapper wtdataMapper;
	@Autowired
	private InstanceContextMapper instanceMapper;
	
	private Logger log = LoggerFactory.getLogger(WtdataService.class);
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

	@Override
	public Result<List<String>> selectWtlb() {
		Result<List<String>> res = new Result<>();
		try{
			List<String> data = wtdataMapper.selectWtlb();
			res.setFlag(true);
			res.setData(data);
		}catch(Exception e){
			e.printStackTrace();
			res.setFlag(false);
			res.setMessage(e.getMessage());
		}
		return res;
	}

	@Override
	public Result<List<Wtdata>> insert(List<Wtdata> wts) {
		Result<List<Wtdata>> res = new Result<>();
		try{
			for(Wtdata d : wts){
				if(StringUtil.isNullOrEmpty(d.getObjectid()) && StringUtil.isNullOrEmpty(d.getValue())){
					
				}else{
					if(StringUtil.isNullOrEmpty(d.getObjectid())){
						String objectid = instanceMapper.getID();
						d.setObjectid(objectid);
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			
		}
		return null;
	}

	@Override
	public Result<List<Wtdata>> insertOrUpdate(List<Wtdata> wts, User user) {
		Result<List<Wtdata>> res = new Result<List<Wtdata>>();
		if(wts == null || wts.size() == 0){
			res.setFlag(false);
			res.setMessage("输入为空");
			return res;
		}
		try{
			Iterator<Wtdata> iterator = wts.iterator();
			Date now = new Date();
			while(iterator.hasNext()){
				Wtdata wd = iterator.next();
				if(StringUtil.isNullOrEmpty(wd.getValue()) && StringUtil.isNullOrEmpty(wd.getText())){
					//此项数据为无效数据，从列表中剔除
					iterator.remove();
				}else if(!StringUtil.isNullOrEmpty(wd.getObjectid())){
					//此项数据为修改项
					wd.setUpdateby(user.getObjectid());
					wd.setUpdatetime(now);
					wtdataMapper.updateByPrimaryKeySelective(wd);
				}else if(StringUtil.isNullOrEmpty(wd.getObjectid())){
					//此项数据为新增项
					String objectid = instanceMapper.getID();
					wd.setObjectid(objectid);
					wd.setCreateby(user.getObjectid());
					wd.setCreatetime(now);
					wd.setDeleteflag(0);
					wd.setState(0);
					wtdataMapper.insertSelective(wd);
				}
			}
			res.setFlag(true);
			res.setData(wts);
		}catch(Exception e){
			res.setFlag(false);
			res.setMessage(e.getMessage());
			
		}
		
		return res;
	}

}
