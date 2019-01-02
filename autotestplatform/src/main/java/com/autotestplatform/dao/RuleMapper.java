package com.autotestplatform.dao;

import org.apache.ibatis.annotations.Mapper;

import com.autotestplatform.entity.Rule;

@Mapper
public interface RuleMapper {
    int deleteByPrimaryKey(Integer ruleId);

    int insert(Rule record);

    int insertSelective(Rule record);

    Rule selectByPrimaryKey(Integer ruleId);

    int updateByPrimaryKeySelective(Rule record);

    int updateByPrimaryKey(Rule record);
}