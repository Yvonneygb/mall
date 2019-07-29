package com.shopping.shoppingMall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lckj.core.orm.condition.Condition;
import com.shopping.shoppingMall.common.entity.PageVO;
import com.shopping.shoppingMall.dao.ClassificationVOMapper;
import com.shopping.shoppingMall.entity.ClassificationVO;
import com.shopping.shoppingMall.service.ClassificationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Service
public class ClassificationServiceImpl implements ClassificationService {
    @Resource
    ClassificationVOMapper classificationVOMapper;


    @Override
    public ClassificationVO selectByPrimaryKey(int id) {
        return classificationVOMapper.selectByPrimaryKey(id);
    }



    @Override
    public int save(ClassificationVO classificationVO) {
        return classificationVOMapper.insertSelective(classificationVO);
    }

    @Override
    public List<ClassificationVO> findListHasAccount(int flag, PageVO pageVO) {
        PageHelper.startPage(pageVO.getPageNum(),pageVO.getPageSize(),false,false,false);
        return classificationVOMapper.findListHasAccount(flag);
    }

    @Override
    public PageVO findListHasAccountPage(int flag, PageVO pageVO) {
        PageHelper.startPage(pageVO.getPageNum(), pageVO.getPageSize());
        List<ClassificationVO> list = classificationVOMapper.findListHasAccount(flag);
        PageInfo<ClassificationVO> pageInfo = new PageInfo<>( list);
        PageHelper.clearPage();
        PageVO vo = new PageVO();
        vo.setPageSize(pageVO.getPageSize());
        vo.setPageNum(pageVO.getPageNum());
        vo.setRows(pageInfo.getList());
        vo.setTotal(pageInfo.getTotal());
        return vo;
    }

    @Override
    public void deleteClassificationVO(String ids) {
        List<String> list = Arrays.asList(ids.split(","));
        for(int i = 0; i < list.size(); i++){
            classificationVOMapper.deleteByPrimaryKey(Integer.valueOf(list.get(i)));
        }
    }

    @Override
    public void updateClassificationVO(ClassificationVO classificationVO) {
        classificationVOMapper.updateByPrimaryKey(classificationVO);
    }

    @Override
    public ClassificationVO findById(int id) {
        return classificationVOMapper.selectByPrimaryKey(id);
    }

    public List<ClassificationVO> findByCondition(Condition condition, PageVO pageVO) {
        PageHelper.startPage(pageVO.getPageNum(),pageVO.getPageSize(),false,false,false);
        return classificationVOMapper.findByCondition(condition , pageVO);
    }

    @Override
    public PageVO findByConditionPage(Condition condition, PageVO pageVO) {
        PageHelper.startPage(pageVO.getPageNum(), pageVO.getPageSize());
        List<ClassificationVO> list = classificationVOMapper.findByCondition(condition , pageVO);
        PageInfo<ClassificationVO> pageInfo = new PageInfo<>( list);
        PageHelper.clearPage();
        PageVO vo = new PageVO();
        vo.setPageSize(pageVO.getPageSize());
        vo.setPageNum(pageVO.getPageNum());
        vo.setRows(pageInfo.getList());
        vo.setTotal(pageInfo.getTotal());
        return vo;
    }
}
