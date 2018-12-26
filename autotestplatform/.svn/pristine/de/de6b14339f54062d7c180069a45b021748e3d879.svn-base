package com.autotestplatform.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.autotestplatform.entity.Config;

@Mapper
public interface ConfigMapper {
    int deleteByPrimaryKey(Integer configId);

    int insert(Config record);

    int insertSelective(Config record);

    Config selectByPrimaryKey(Integer configId);

    int updateByPrimaryKeySelective(Config record);

    int updateByPrimaryKey(Config record);

    /**
     * @Description：条件查询（可选条件：configName,configStatus,userId
     * @param map
     * @return: 返回结果描述
     * @return Config: 返回值类型
     * @throws
     */
    List<Config> selectBySelective(Map<String, String> map);
}
