package com.shopping.shoppingMall.service;

import com.lckj.core.orm.condition.Condition;
import com.shopping.shoppingMall.common.entity.PageVO;
import com.shopping.shoppingMall.entity.CouponVO;

import java.util.List;

/**
 * @Description
 * @Author RLinux
 * @Email RLinux_zwh@163.com
 * @Since 2019/7/26 11:10
 * @Version 1.0
 */
public interface CouponService {
    //通过主键找到用户
    CouponVO selectByPrimaryKey(int id);

    //新增用户
    int save(CouponVO goodsVO);

    //找到有账号的用户集合 1有，0无, 有账号的是管理员
    List<CouponVO> findListHasAccount(int flag, PageVO pageVO);

    PageVO findListHasAccountPage(int flag, PageVO pageVO);

    //批量删除用户
    void deleteCouponVO(String ids);

    //更新
    void updateCouponVO(CouponVO goodsVO);

    //通过主键找到对象
    CouponVO findById(int id);

    // 通过分类ID找商品
    List<CouponVO> findByCondition(Condition condition, PageVO pageVO);

    // 通过条件找结果
    PageVO findByConditionPage(Condition condition, PageVO pageVO);
}
