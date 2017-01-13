package com.mapper;

import java.util.List;

import com.bean.Wtdata;

public interface WtdataMapper {
    int deleteByPrimaryKey(String objectid);

    int insert(Wtdata record);

    int insertSelective(Wtdata record);

    Wtdata selectByPrimaryKey(String objectid);
    
    List<Wtdata> selectByFlag(String belongTo);
    
    List<Wtdata> selectAll();
    
    List<String> selectWtlb();
    
    Wtdata selectByFlagValue(String belongTo, String value);

    int updateByPrimaryKeySelective(Wtdata record);

    int updateByPrimaryKey(Wtdata record);
}