package com.autotestplatform.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.autotestplatform.entity.UseCasePlanRelation;

@Mapper
public interface UseCasePlanRelationMapper {
    int deleteByPrimaryKey(Integer useCasePlanRelationId);

    int insert(UseCasePlanRelation record);

    int insertSelective(UseCasePlanRelation record);

    UseCasePlanRelation selectByPrimaryKey(Integer useCasePlanRelationId);

    int updateByPrimaryKeySelective(UseCasePlanRelation record);

    int updateByPrimaryKey(UseCasePlanRelation record);

    List<UseCasePlanRelation> selectListByPlanId(Integer planId);

    UseCasePlanRelation selectByPlanIdAndUseCaseId(@Param("planId") Integer planId,
                                                   @Param("useCaseId") Integer useCaseId);

    int deletePlanIdAndUseCaseId(@Param("useCasePlanRelationId") Integer useCasePlanRelationId);

}
