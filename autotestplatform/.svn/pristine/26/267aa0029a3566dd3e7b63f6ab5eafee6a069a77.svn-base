package com.autotestplatform.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.autotestplatform.dto.common.ProjectIdNameDto;
import com.autotestplatform.dto.project.list.ProjectListOutDetailDto;
import com.autotestplatform.entity.Project;

@Mapper
public interface ProjectMapper {
    int deleteByPrimaryKey(Integer projectId);

    int insert(Project record);

    int insertSelective(Project record);

    Project selectByPrimaryKey(Integer projectId);

    int updateByPrimaryKeySelective(Project record);

    int updateByPrimaryKey(Project record);

    /**
     * @Description：总数
     * @param map
     * @return: 返回结果描述
     * @return Integer: 返回值类型
     * @throws
     */
    Integer selectListCountBySelective(Map<String, Object> map);

    /**
     * @Description：列表内容
     * @param map
     * @return: 返回结果描述
     * @return List<ProjectDto>: 返回值类型
     * @throws
     */
    List<ProjectListOutDetailDto> selectListBySelective(Map<String, Object> map);

    List<ProjectIdNameDto> selectAllIdAndName();

}
