package com.autotestplatform.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.autotestplatform.dto.plan.list.PlanListDetailDto;
import com.autotestplatform.entity.Plan;

@Mapper
public interface PlanMapper {
    int deleteByPrimaryKey(Integer planId);

    int insert(Plan record);

    int insertSelective(Plan record);

    Plan selectByPrimaryKey(Integer planId);

    int updateByPrimaryKeySelective(Plan record);

    int updateByPrimaryKey(Plan record);
    
    /**
     * 
     * @Description：查询计划列表
     * @param map
     * @return: 返回结果描述
     * @return List<PlanListDetailDto>: 返回值类型
     * @throws
     */
    List<PlanListDetailDto> selectPlanList(Map<String, Object> map);
    
    /**
     * 
     * @Description：查询计划数量
     * @param map
     * @return: 返回结果描述
     * @return Integer: 返回值类型
     * @throws
     */
    Integer selectPlanListCount(Map<String, Object> map);
    
}