package com.mapper;

import java.util.List;

import com.bean.Wtdata;

public interface WtdataMapper {
    int deleteByPrimaryKey(String objectid);

    int insert(Wtdata record);

    int insertSelective(Wtdata record);

    Wtdata selectByPrimaryKey(String objectid);
    
    List<Wtdata> selectByFlag(String belongTo);

    int updateByPrimaryKeySelective(Wtdata record);

    int updateByPrimaryKey(Wtdata record);
}