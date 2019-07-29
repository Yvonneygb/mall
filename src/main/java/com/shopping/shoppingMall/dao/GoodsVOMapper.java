package com.shopping.shoppingMall.dao;

import com.lckj.core.model.PagerVO;
import com.lckj.core.orm.MybatisMapper;
import com.lckj.core.orm.condition.Condition;
import com.shopping.shoppingMall.common.entity.PageVO;
import com.shopping.shoppingMall.entity.GoodsVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsVOMapper extends MybatisMapper<GoodsVO> {
    int deleteByPrimaryKey(Integer id);

    int insert(GoodsVO record);

    int insertSelective(GoodsVO record);

    GoodsVO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GoodsVO record);

    int updateByPrimaryKey(GoodsVO record);

    List<GoodsVO> findListHasAccount(@Param("flag") int flag);

    PageVO findByConditionPage(Condition condition, PageVO pageVO);

    List<GoodsVO> findByCondition(Condition condition, PageVO pageVO);
}