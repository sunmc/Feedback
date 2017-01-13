package com.mapper;

import java.util.List;

import com.bean.User;

public interface UserMapper {
    int deleteByPrimaryKey(String objectid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String objectid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    List<User> getList(User user);
    
    User loginValidate(String code, String passwd);
    
    List<User> search(String text);
    
    List<User> selectByWorkItem(String bizobjectid, String activityCode);
    
    List<User> selectByName(String xm);
}