package com.autotestplatform.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.autotestplatform.entity.SysLog;

@Mapper
public interface SysLogMapper {
    int deleteByPrimaryKey(Integer sysLogId);

    int insert(SysLog record);

    int insertSelective(SysLog record);

    SysLog selectByPrimaryKey(Integer sysLogId);

    int updateByPrimaryKeySelective(SysLog record);

    int updateByPrimaryKey(SysLog record);
    
    List<SysLog> selectByPlanIdAndPlanResultId(@Param("planId") Integer planId,@Param("planResultId") Integer planResultId);
}