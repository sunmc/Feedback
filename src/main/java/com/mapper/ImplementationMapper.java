package com.mapper;

import com.bean.Implementation;

public interface ImplementationMapper {
    int deleteByPrimaryKey(String objectid);

    int insert(Implementation record);

    int insertSelective(Implementation record);

    Implementation selectByPrimaryKey(String objectid);

    int updateByPrimaryKeySelective(Implementation record);

    int updateByPrimaryKey(Implementation record);
}