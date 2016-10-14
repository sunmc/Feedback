package com.mapper;

import java.util.List;

import com.bean.User;
import com.bean.UserWithBLOBs;

public interface UserMapper {
	
	List<User> getList(User user);
	
	List<User> search(String text);
	
	User loginValidate(String code, String passwd);
	
    int deleteByPrimaryKey(String objectid);

    int insert(UserWithBLOBs record);

    int insertSelective(UserWithBLOBs record);

    UserWithBLOBs selectByPrimaryKey(String objectid);

    int updateByPrimaryKeySelective(UserWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(UserWithBLOBs record);

    int updateByPrimaryKey(User record);
}