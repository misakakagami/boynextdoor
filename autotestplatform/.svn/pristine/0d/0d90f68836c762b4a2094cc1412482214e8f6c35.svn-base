package com.autotestplatform.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.autotestplatform.dto.common.ModelIdNameDto;
import com.autotestplatform.dto.model.list.ModelListOutDetailDto;
import com.autotestplatform.entity.Model;

@Mapper
public interface ModelMapper {
    int deleteByPrimaryKey(Integer modelId);

    int insert(Model record);

    int insertSelective(Model record);

    Model selectByPrimaryKey(Integer modelId);

    int updateByPrimaryKeySelective(Model record);

    int updateByPrimaryKey(Model record);

    /**
     * @Description：查找数量
     * @param map
     * @return: 返回结果描述
     * @return Integer: 返回值类型
     * @throws
     */
    Integer selectListCountBySelective(Map<String, Object> map);

    /**
     * @Description：查找列表
     * @param map
     * @return: 返回结果描述
     * @return List<ModelDto>: 返回值类型
     * @throws
     */
    List<ModelListOutDetailDto> selectListBySelective(Map<String, Object> map);

    List<ModelIdNameDto> selectAllIdNameList();

    List<ModelIdNameDto> selectModelIdNameList(@Param("projectId") Integer projectId);

    Integer selectProjectIdByModelId(Integer modelId);
}
