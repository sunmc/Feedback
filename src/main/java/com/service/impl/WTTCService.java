package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bean.WTTC;
import com.mapper.WTTCMapper;
import com.service.IWTTCService;
import com.util.bean.Result;

@Service
public class WTTCService implements IWTTCService{

	@Autowired
	private WTTCMapper wttcMapper;
	@Override
	public Result<WTTC> insert(WTTC wttc) {
		Result<WTTC> res = new Result<WTTC>();
		try{
			String id = wttcMapper.getID();
			wttc.setObjectid(id);
			wttcMapper.insert(wttc);
			res.setFlag(true);
			res.setData(wttc);
			System.out.println(id);
		}catch(Exception e){
			res.setFlag(false);
			res.setMessage(e.getMessage());
		}
		return res;
	}

}
