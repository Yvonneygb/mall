package com.shopping.shoppingMall.dao;

import com.lckj.core.orm.MybatisMapper;
import com.shopping.shoppingMall.entity.GoodsVO;
import com.shopping.shoppingMall.entity.UserVO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserVOMapper extends MybatisMapper<UserVO> {
    int deleteByPrimaryKey(Integer id);

    int insert(UserVO record);

    int insertSelective(UserVO record);

    UserVO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserVO record);

    int updateByPrimaryKey(UserVO record);

    List<UserVO> findListHasAccount(@Param("flag") int flag);

    UserVO findByAccount(String account);
}