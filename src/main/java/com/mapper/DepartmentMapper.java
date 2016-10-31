package com.mapper;

import com.bean.Department;

public interface DepartmentMapper {
    int deleteByPrimaryKey(String objectid);

    int insert(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(String objectid);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);
}