package com.autotestplatform.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.autotestplatform.dto.common.ExampleDto;
import com.autotestplatform.entity.Example;

@Mapper
public interface ExampleMapper {
    int deleteByPrimaryKey(Integer exampleId);

    int insert(Example record);

    int insertSelective(Example record);

    Example selectByPrimaryKey(Integer exampleId);

    int updateByPrimaryKeySelective(Example record);

    int updateByPrimaryKey(Example record);

    List<ExampleDto> selectList(Map<String, Object> map);

    Integer selectCount(@Param("useCaseId") Integer useCaseId);

    List<Integer> selectOldExampleId(@Param("useCaseId") Integer useCaseId);
}
