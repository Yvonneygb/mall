package com.shopping.shoppingMall.service;

import com.lckj.core.model.PagerVO;
import com.lckj.core.orm.condition.Condition;
import com.shopping.shoppingMall.common.entity.PageVO;
import com.shopping.shoppingMall.entity.UserVO;

import java.util.List;

public interface UserService {
    //通过主键找到用户
    UserVO selectByPrimaryKey(int id);

    //新增用户
    int save(UserVO userVO);

    //找到有账号的用户集合 1有，0无, 有账号的是管理员
    List<UserVO> findListHasAccount(int flag, PageVO pageVO);

    PageVO findListHasAccountPage(int flag, PageVO pageVO);

    //批量删除用户
    void deleteUserVO(String ids);

    //更新
    void updateUserVO(UserVO userVO);

    //通过主键找到对象
    UserVO findById(int id);

    // 通过账号查询信息
    UserVO findByAccount(String account);
}
