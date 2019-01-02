package com.autotestplatform.dao;

import org.apache.ibatis.annotations.Mapper;

import com.autotestplatform.entity.Identity;

@Mapper
public interface IdentityMapper {
    int deleteByPrimaryKey(Integer identityId);

    int insert(Identity record);

    int insertSelective(Identity record);

    Identity selectByPrimaryKey(Integer identityId);

    int updateByPrimaryKeySelective(Identity record);

    int updateByPrimaryKey(Identity record);
}