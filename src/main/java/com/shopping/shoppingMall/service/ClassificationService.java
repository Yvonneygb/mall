package com.shopping.shoppingMall.service;

import com.lckj.core.orm.condition.Condition;
import com.shopping.shoppingMall.common.entity.PageVO;
import com.shopping.shoppingMall.entity.ClassificationVO;

import java.util.List;

public interface ClassificationService {


    //通过主键找到用户
    ClassificationVO selectByPrimaryKey(int id);

    //新增用户
    int save(ClassificationVO classificationVO);

    //找到有账号的用户集合 1有，0无, 有账号的是管理员
    List<ClassificationVO> findListHasAccount(int flag, PageVO pageVO);

    PageVO findListHasAccountPage(int flag, PageVO pageVO);
    //批量删除用户
    void deleteClassificationVO(String ids);

    //更新
    void updateClassificationVO(ClassificationVO classificationVO);

    //通过主键找到对象
    ClassificationVO findById(int id);

    // 通过条件找结果
    PageVO findByConditionPage(Condition condition, PageVO pageVO);
}
