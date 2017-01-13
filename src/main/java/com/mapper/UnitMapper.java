package com.mapper;

import java.util.List;

import com.bean.Unit;

public interface UnitMapper {
    int deleteByPrimaryKey(String objectid);

    int insert(Unit record);

    int insertSelective(Unit record);

    Unit selectByPrimaryKey(String objectid);

    int updateByPrimaryKeySelective(Unit record);

    int updateByPrimaryKey(Unit record);
    
    List<Unit> selectAll();
}