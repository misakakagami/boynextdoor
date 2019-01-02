package com.autotestplatform.dao;

import org.apache.ibatis.annotations.Mapper;

import com.autotestplatform.entity.IdentityRuleRelation;

@Mapper
public interface IdentityRuleRelationMapper {
    int deleteByPrimaryKey(Integer identityRuleRelationId);

    int insert(IdentityRuleRelation record);

    int insertSelective(IdentityRuleRelation record);

    IdentityRuleRelation selectByPrimaryKey(Integer identityRuleRelationId);

    int updateByPrimaryKeySelective(IdentityRuleRelation record);

    int updateByPrimaryKey(IdentityRuleRelation record);
}