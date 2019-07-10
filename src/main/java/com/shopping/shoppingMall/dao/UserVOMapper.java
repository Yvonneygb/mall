package com.shopping.shoppingMall.dao;

import com.shopping.shoppingMall.entity.UserVO;

public interface UserVOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserVO record);

    int insertSelective(UserVO record);

    UserVO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserVO record);

    int updateByPrimaryKey(UserVO record);
}