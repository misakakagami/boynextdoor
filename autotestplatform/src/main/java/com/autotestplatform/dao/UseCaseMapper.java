package com.autotestplatform.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.autotestplatform.dto.common.UseCaseIdNameDto;
import com.autotestplatform.dto.plan.detail.PlanUseCaseListDetailDto;
import com.autotestplatform.dto.useCase.detail.UseCaseDetailOutDto;
import com.autotestplatform.dto.useCase.list.UseCaseListDetailDto;
import com.autotestplatform.entity.UseCase;

@Mapper
public interface UseCaseMapper {
    int deleteByPrimaryKey(Integer useCaseId);

    int insert(UseCase record);

    int insertSelective(UseCase record);

    UseCase selectByPrimaryKey(Integer useCaseId);

    int updateByPrimaryKeySelective(UseCase record);

    int updateByPrimaryKey(UseCase record);

    List<UseCase> selectUseCaseEntityList(Integer planId);

    List<UseCaseListDetailDto> selectUseCaseList(Map<String, Object> map);

    Integer selectUseCaseListCount(Map<String, Object> map);

    UseCaseDetailOutDto selectUseCaseDetailOutDto(Integer useCaseId);

    List<PlanUseCaseListDetailDto> selectUseCaseDetailOutDtoListByPlanId(Integer planId);

    List<UseCaseIdNameDto> findUseCaseIdName(@Param("modelId") Integer modelId);

}
