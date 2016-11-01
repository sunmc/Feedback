package com.mapper;

import java.util.List;

import com.bean.Post;

public interface PostMapper {
    int insert(Post record);

    int insertSelective(Post record);
    
    List<Post> getPostByName(String postname);
    
    List<Post> getPostByCode(String postcode);
}