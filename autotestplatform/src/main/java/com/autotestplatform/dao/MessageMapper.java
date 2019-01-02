package com.autotestplatform.dao;

import org.apache.ibatis.annotations.Mapper;

import com.autotestplatform.entity.Message;

@Mapper
public interface MessageMapper {
    int deleteByPrimaryKey(Integer messageId);

    int insert(Message record);

    int insertSelective(Message record);

    Message selectByPrimaryKey(Integer messageId);

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKey(Message record);
}