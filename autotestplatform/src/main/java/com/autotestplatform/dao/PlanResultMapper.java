package com.autotestplatform.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.autotestplatform.dto.common.PlanResultDto;
import com.autotestplatform.entity.PlanResult;

@Mapper
public interface PlanResultMapper {
    int deleteByPrimaryKey(Integer planResultId);

    int insert(PlanResult record);

    int insertSelective(PlanResult record);

    int updateSelective(PlanResult record);

    PlanResult selectByPrimaryKey(Integer planResultId);

    List<PlanResultDto> selectPlanResultList(Map<String, Object> map);

    Integer selectPlanResultListCount(@Param("planId") Integer planId);
    
    List<PlanResult> selectAllPlanResultList(@Param("planId") Integer planId);

}
