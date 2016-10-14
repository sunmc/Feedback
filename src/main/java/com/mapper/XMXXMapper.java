package com.mapper;

import java.util.List;

import com.bean.XMXX;

public interface XMXXMapper {

	List<XMXX> selectByXMBH(String text);
}