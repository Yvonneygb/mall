package com.shopping.shoppingMall.dao;

import com.shopping.shoppingMall.entity.GoodsVO;

public interface GoodsVOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GoodsVO record);

    int insertSelective(GoodsVO record);

    GoodsVO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GoodsVO record);

    int updateByPrimaryKey(GoodsVO record);
}