package com.shopping.shoppingMall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lckj.core.orm.MybatisMapper;
import com.lckj.core.orm.condition.Condition;
import com.lckj.core.service.AbstractService;
import com.shopping.shoppingMall.common.entity.PageVO;
import com.shopping.shoppingMall.dao.CouponVOMapper;
import com.shopping.shoppingMall.entity.CouponVO;
import com.shopping.shoppingMall.service.CouponService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @Description
 * @Author RLinux
 * @Email RLinux_zwh@163.com
 * @Since 2019/7/26 11:11
 * @Version 1.0
 */
@Service
public class CouponServiceImpl extends AbstractService<CouponVO> implements CouponService {
    @Resource
    CouponVOMapper couponMapper;

    /** 获取Mapper接口 */
    @Override
    protected MybatisMapper<CouponVO> getMapper() {
        return this.couponMapper;
    }


    @Override
    public CouponVO selectByPrimaryKey(int id) {
        return couponMapper.selectByPrimaryKey(id);
    }



    @Override
    public int save(CouponVO goodsVO) {
        return couponMapper.insertSelective(goodsVO);
    }

    @Override
    public List<CouponVO> findListHasAccount(int flag, PageVO pageVO) {
        PageHelper.startPage(pageVO.getPageNum(),pageVO.getPageSize(),false,false,false);
        return couponMapper.findListHasAccount(flag);
    }


    @Override
    public PageVO findListHasAccountPage(int flag, PageVO pageVO) {
        PageHelper.startPage(pageVO.getPageNum(), pageVO.getPageSize());
        List<CouponVO> list = couponMapper.findListHasAccount(flag);
        PageInfo<CouponVO> pageInfo = new PageInfo<>( list);
        PageHelper.clearPage();
        PageVO vo = new PageVO();
        vo.setPageSize(pageVO.getPageSize());
        vo.setPageNum(pageVO.getPageNum());
        vo.setRows(pageInfo.getList());
        vo.setTotal(pageInfo.getTotal());
        return vo;
    }

    @Override
    public void deleteCouponVO(String ids) {
        List<String> list = Arrays.asList(ids.split(","));
        for(int i = 0; i < list.size(); i++){
            couponMapper.deleteByPrimaryKey(Integer.valueOf(list.get(i)));
        }
    }

    @Override
    public void updateCouponVO(CouponVO goodsVO) {
        couponMapper.updateByPrimaryKey(goodsVO);
    }

    @Override
    public CouponVO findById(int id) {
        return couponMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<CouponVO> findByCondition(Condition condition, PageVO pageVO) {
        PageHelper.startPage(pageVO.getPageNum(),pageVO.getPageSize(),false,false,false);
        return couponMapper.findByCondition(condition , pageVO);
    }

    @Override
    public PageVO findByConditionPage(Condition condition, PageVO pageVO) {
        PageHelper.startPage(pageVO.getPageNum(), pageVO.getPageSize());
        List<CouponVO> list = couponMapper.findByCondition(condition , pageVO);
        PageInfo<CouponVO> pageInfo = new PageInfo<>( list);
        PageHelper.clearPage();
        PageVO vo = new PageVO();
        vo.setPageSize(pageVO.getPageSize());
        vo.setPageNum(pageVO.getPageNum());
        vo.setRows(pageInfo.getList());
        vo.setTotal(pageInfo.getTotal());
        return vo;
    }
}
