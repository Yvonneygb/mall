package com.shopping.shoppingMall.controller.pc;

import com.lckj.core.orm.condition.SimpleCondition;
import com.shopping.shoppingMall.common.entity.PageVO;
import com.shopping.shoppingMall.common.entity.ResponseVO;
import com.shopping.shoppingMall.entity.CouponVO;
import com.shopping.shoppingMall.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @Description
 * @Author RLinux
 * @Email RLinux_zwh@163.com
 * @Since 2019/7/26 11:07
 * @Version 1.0
 */
@Controller
@RequestMapping("/coupon/")
public class CouponController {
    @Autowired
    CouponService couponService;

    @GetMapping("/list")
    @ResponseBody
    public ResponseVO userList(PageVO pageVO) {
        ResponseVO responseVO = new ResponseVO();
        try {
            PageVO vo = couponService.findListHasAccountPage(0, pageVO);
            responseVO = responseVO.ok(vo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseVO;
    }


    /**
     * 读取
     *
     * @param couponVO 优惠券实体
     * @param model   数据模型
     * @return 优惠券实体
     */
    @GetMapping("/read")
    @ResponseBody
    public CouponVO read(CouponVO couponVO, Model model) {
        ResponseVO responseVO = new ResponseVO();
        try {
            return couponService.selectByPrimaryKey(couponVO.getId());
        } catch (Exception e) {
            e.printStackTrace();
            responseVO.error("获取失败");        }
        return couponVO;
    }

    /**
     * 根据有效时间读取
     *
     * @param couponVO 优惠券实体
     * @param model   数据模型
     * @return 优惠券实体
     */
    @GetMapping("/readByEffectiveTime")
    @ResponseBody
    public ResponseVO readByEffectiveTime(CouponVO couponVO, PageVO pageVO, Model model) {
        ResponseVO responseVO = new ResponseVO();
        SimpleCondition condition = new SimpleCondition();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        if(couponVO.getBeginTime() == null)
        {
            condition.andDateRangeOrNull("endTime", simpleDateFormat.format(new Date(System.currentTimeMillis())), "");// 只查询数据
        }
        System.out.println(condition.toString());

        try {
            PageVO vo = couponService.findByConditionPage(condition, pageVO);
            responseVO = responseVO.ok(vo);
        } catch (Exception e) {
            e.printStackTrace();
            responseVO.error("获取失败");
        }
        return responseVO;
    }


    /**
     * 条件查询
     *
     * @param couponVO 优惠券实体
     * @param model   数据模型
     * @return 优惠券实体
     */
    @GetMapping("/search")
    @ResponseBody
    public ResponseVO search(CouponVO couponVO, PageVO pageVO, Model model) {
        ResponseVO responseVO = new ResponseVO();
        SimpleCondition condition = new SimpleCondition();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        condition.andLike("name", couponVO.getName());
        if(couponVO.getBeginTime() != null)
        {
            condition.andDateRange("beginTime", simpleDateFormat.format(couponVO.getBeginTime()), "");
        }
        if(couponVO.getEndTime() != null)
        {
            condition.andDateRange("endTime", "", simpleDateFormat.format(couponVO.getEndTime()));
        }
        condition.andLike("link", couponVO.getLink());
        try {
            PageVO vo = couponService.findByConditionPage(condition, pageVO);
            responseVO = responseVO.ok(vo);
        } catch (Exception e) {
            e.printStackTrace();
            responseVO = responseVO.error("获取失败");
        }
        return responseVO;
    }

    @PostMapping("/update")
    @ResponseBody
    public ResponseVO updateCouponVO(CouponVO couponVO , Boolean flag_img_change) {
        ResponseVO responseVO = new ResponseVO();
        try {
            couponService.updateCouponVO(couponVO);
            responseVO = responseVO.ok(couponVO.getId());
        } catch (Exception e) {
            e.printStackTrace();
            responseVO = responseVO.error("更新失败");
        }
        return responseVO;
    }

    @PostMapping("/del")
    @ResponseBody
    public ResponseVO deleteCouponVO(String ids) {
        ResponseVO responseVO = new ResponseVO();
        try {
            couponService.deleteCouponVO(ids);
            responseVO.ok("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            responseVO.error("删除失败");
        }
        return responseVO;
    }


    @PostMapping("/add")
    @ResponseBody
    public ResponseVO addCouponVO(CouponVO couponVO) {

        ResponseVO responseVO = new ResponseVO();
        try {
            couponVO.setAddDate(new Date(System.currentTimeMillis()));
            couponService.save(couponVO);
            responseVO = responseVO.ok(couponVO.getId());
        } catch (Exception e) {
            System.out.println("新增失败" + e);
            responseVO.error("新增失败" + e);
        }
        return responseVO;
    }
}

