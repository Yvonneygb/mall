package com.shopping.shoppingMall.dao;

import com.lckj.core.orm.condition.Condition;
import com.shopping.shoppingMall.common.entity.PageVO;
import com.shopping.shoppingMall.entity.ClassificationVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClassificationVOMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(ClassificationVO record);

    int insertSelective(ClassificationVO record);

    ClassificationVO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ClassificationVO record);

    int updateByPrimaryKey(ClassificationVO record);


    List<ClassificationVO> findListHasAccount(@Param("flag") int flag);

    PageVO findByConditionPage(Condition condition, PageVO pageVO);

    List<ClassificationVO> findByCondition(Condition condition, PageVO pageVO);
}