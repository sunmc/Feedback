package com.mapper;

import com.bean.Attachment;

public interface AttachmentMapper {
    int insert(Attachment record);

    int insertSelective(Attachment record);
}