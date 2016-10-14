package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bean.XMXX;
import com.mapper.XMXXMapper;
import com.service.IXMXXService;
import com.util.bean.Result;

@Service
public class XMXXService implements IXMXXService {

	@Autowired
	private XMXXMapper xmxxMapper;
	@Override
	public Result<List<XMXX>> searchXM(String text) {
		Result<List<XMXX>> res = new Result<>();
		try{
			text = "%" + text + "%";
			List<XMXX> data = xmxxMapper.selectByXMBH(text);
			res.setFlag(true);
			res.setData(data);
		}catch(Exception e){
			res.setFlag(false);
			res.setMessage(e.getMessage());
		}
		return res;
	}

}
