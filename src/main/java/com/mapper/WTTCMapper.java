package com.mapper;

import com.bean.WTTC;

public interface WTTCMapper {
    int deleteByPrimaryKey(String objectid);

    int insert(WTTC record);

    int insertSelective(WTTC record);

    WTTC selectByPrimaryKey(String objectid);

    int updateByPrimaryKeySelective(WTTC record);

    int updateByPrimaryKey(WTTC record);
    
    String getID();
}