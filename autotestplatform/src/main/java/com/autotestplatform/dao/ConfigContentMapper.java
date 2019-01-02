package com.autotestplatform.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.autotestplatform.entity.ConfigContent;

@Mapper
public interface ConfigContentMapper {
    int deleteByPrimaryKey(Integer configContentId);

    int insert(ConfigContent record);

    int insertSelective(ConfigContent record);

    ConfigContent selectByPrimaryKey(Integer configContentId);

    int updateByPrimaryKeySelective(ConfigContent record);

    int updateByPrimaryKey(ConfigContent record);

    List<ConfigContent> selectListBySelective(Map<String, String> map);

    /**
     * @Description：批量增加
     * @param list: 返回结果描述
     * @return void: 返回值类型
     * @throws
     */
    void batchInsert(@Param("ccList") List<ConfigContent> list);
}
