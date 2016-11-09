package com.mapper;

import com.bean.InstanceContext;

public interface InstanceContextMapper {
    int deleteByPrimaryKey(String objectid);

    int insert(InstanceContext record);

    int insertSelective(InstanceContext record);

    InstanceContext selectByPrimaryKey(String objectid);

    int updateByPrimaryKeySelective(InstanceContext record);

    int updateByPrimaryKey(InstanceContext record);
    
    String getID();
    
    Integer getOrderNumber(String schema, String ymd);
    
    void updateOrderNumber(String schema, String ymd, int order);
    
    InstanceContext selectByBizobjectid(String bizobjectid);
    
}