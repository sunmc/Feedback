package com.service;

import java.util.List;

import com.bean.XMXX;
import com.util.bean.Result;

public interface IXMXXService {

	public Result<List<XMXX>> searchXM(String text);
}
