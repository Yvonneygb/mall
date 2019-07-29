package com.shopping.shoppingMall.service;

import com.lckj.core.model.PagerVO;
import com.lckj.core.orm.condition.Condition;
import com.shopping.shoppingMall.common.entity.PageVO;
import com.shopping.shoppingMall.entity.GoodsVO;

import java.util.List;

public interface GoodsService {
    //通过主键找到用户
    GoodsVO selectByPrimaryKey(int id);

    //新增用户
    int save(GoodsVO goodsVO);

    //找到有账号的用户集合 1有，0无, 有账号的是管理员
    List<GoodsVO> findListHasAccount(int flag, PageVO pageVO);

    PageVO findListHasAccountPage(int flag, PageVO pageVO);

    //批量删除用户
    void deleteGoodsVO(String ids);

    //更新
    void updateGoodsVO(GoodsVO goodsVO);

    //通过主键找到对象
    GoodsVO findById(int id);

    // 通过分类ID找商品
    List<GoodsVO> findByCondition(Condition condition, PageVO pageVO);

    // 通过条件找结果
    PageVO findByConditionPage(Condition condition, PageVO pageVO);

}
