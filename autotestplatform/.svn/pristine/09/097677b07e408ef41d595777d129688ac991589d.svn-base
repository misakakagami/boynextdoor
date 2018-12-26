package com.autotestplatform.dao;

import org.apache.ibatis.annotations.Mapper;

import com.autotestplatform.entity.User;

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectByUserName(String userName);

    /**
     * @Description：根据用户名查找用户是否存在
     * @param userName
     * @return: 返回结果描述
     * @return Integer: 返回值类型
     * @throws
     */
    Integer selectUserCountByUserName(String userName);
}
