package com.shopping.shoppingMall.controller.pc;

import com.lckj.core.model.PagerVO;
import com.lckj.core.orm.condition.SimpleCondition;
import com.shopping.shoppingMall.common.entity.PageVO;
import com.shopping.shoppingMall.common.entity.ResponseVO;
import com.shopping.shoppingMall.entity.GoodsVO;
import com.shopping.shoppingMall.service.GoodsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
@RequestMapping("/goods/")
public class GoodsController {

    @Autowired
    GoodsService goodsService;

    @GetMapping("/list")
    @ResponseBody
    public ResponseVO userList(PageVO pageVO) {
        ResponseVO responseVO = new ResponseVO();
        try {
            PageVO vo = goodsService.findListHasAccountPage(0, pageVO);
            responseVO = responseVO.ok(vo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseVO;
    }


    /**
     * 读取
     *
     * @param goodsVO 分类实体
     * @param model   数据模型
     * @return 分类实体
     */
    @GetMapping("/read")
    @ResponseBody
    public GoodsVO read(GoodsVO goodsVO, Model model) {
        ResponseVO responseVO = new ResponseVO();
        try {
            return goodsService.selectByPrimaryKey(goodsVO.getId());
        } catch (Exception e) {
            e.printStackTrace();
            responseVO.error("获取失败");        }
        return goodsVO;
    }

    /**
     * 根据分类读取
     *
     * @param goodsVO 分类实体
     * @param model   数据模型
     * @return 分类实体
     */
    @GetMapping("/readByClassId")
    @ResponseBody
    public ResponseVO readByClassId(GoodsVO goodsVO, PageVO pageVO, Model model) {
        ResponseVO responseVO = new ResponseVO();
        SimpleCondition condition = new SimpleCondition();
        condition.andEqual("goodsClassId", goodsVO.getGoodsClassId());// 只查询数据
        try {
            PageVO vo = goodsService.findByConditionPage(condition, pageVO);
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
     * @param goodsVO 分类实体
     * @param model   数据模型
     * @return 分类实体
     */
    @GetMapping("/search")
    @ResponseBody
    public ResponseVO search(GoodsVO goodsVO, PageVO pageVO, Model model) {
        ResponseVO responseVO = new ResponseVO();
        SimpleCondition condition = new SimpleCondition();
        condition.andLike("goodsName", goodsVO.getGoodsName());// 只查询数据
        condition.andEqual("goodsClassId", goodsVO.getGoodsClassId());// 只查询数据
        condition.andLike("link", goodsVO.getLink());// 只查询数据
        try {
            PageVO vo = goodsService.findByConditionPage(condition, pageVO);
            responseVO = responseVO.ok(vo);
        } catch (Exception e) {
            e.printStackTrace();
            responseVO.error("获取失败");
        }
        return responseVO;
    }

    @PostMapping("/update")
    @ResponseBody
    public ResponseVO updateGoodsVO(GoodsVO goodsVO , Boolean flag_img_change) {
        ResponseVO responseVO = new ResponseVO();
        try {
            if(!flag_img_change)
            {
                goodsVO.setImage(goodsVO.getImage());
            }
            goodsService.updateGoodsVO(goodsVO);

            System.out.println(goodsVO.getId());
            responseVO = responseVO.ok(goodsVO.getId());
        } catch (Exception e) {
            e.printStackTrace();
            responseVO.error("更新失败");
        }
        return responseVO;
    }

    @PostMapping("/del")
    @ResponseBody
    public ResponseVO deleteGoodsVO(String ids) {
        ResponseVO responseVO = new ResponseVO();
        try {
            goodsService.deleteGoodsVO(ids);
            responseVO.ok("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            responseVO.error("删除失败");
        }
        return responseVO;
    }


    @PostMapping("/add")
    @ResponseBody
    public ResponseVO addGoodsVO(GoodsVO goodsVO) {

        ResponseVO responseVO = new ResponseVO();
        try {
            goodsVO.setAddDate(new Date(System.currentTimeMillis()));
            goodsService.save(goodsVO);
            responseVO = responseVO.ok(goodsVO.getId());
        } catch (Exception e) {
            System.out.println("新增失败" + e);
            responseVO.error("新增失败" + e);
        }
        return responseVO;
    }
}

