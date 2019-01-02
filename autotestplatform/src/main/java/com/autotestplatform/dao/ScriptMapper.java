package com.autotestplatform.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.autotestplatform.dto.common.ScriptDto;
import com.autotestplatform.entity.Script;

@Mapper
public interface ScriptMapper {
    int deleteByPrimaryKey(Integer scriptId);

    int insert(Script record);

    int insertSelective(Script record);

    Script selectByPrimaryKey(Integer scriptId);

    int updateByPrimaryKeySelective(Script record);

    int updateByPrimaryKey(Script record);

    List<ScriptDto> selectList(Map<String, Object> map);

    Integer selectCount(@Param("useCaseId") Integer useCaseId);

    List<Integer> selectOldScriptId(@Param("useCaseId") Integer useCaseId);
}
