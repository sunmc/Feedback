package com.service;

import com.bean.WTTC;
import com.util.bean.Result;

public interface IWTTCService {

	public Result<WTTC> insert(WTTC wttc);
}