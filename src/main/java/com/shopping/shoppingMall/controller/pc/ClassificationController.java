package com.shopping.shoppingMall.controller.pc;

import com.lckj.core.orm.condition.SimpleCondition;
import com.shopping.shoppingMall.common.entity.PageVO;
import com.shopping.shoppingMall.common.entity.ResponseVO;
import com.shopping.shoppingMall.entity.ClassificationVO;
import com.shopping.shoppingMall.service.ClassificationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.sql.Timestamp;

@Controller
@RequestMapping("/classification/")
public class ClassificationController {

    @Autowired
    ClassificationService classificationService;

    @GetMapping("/list")
    @ResponseBody
    public ResponseVO userList(PageVO pageVO){
        ResponseVO responseVO = new ResponseVO();
        try {
            PageVO vo = classificationService.findListHasAccountPage(0, pageVO);
            responseVO = responseVO.ok(vo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseVO;
    }

    /**
     * 条件查询
     *
     * @param classificationVO 海报实体
     * @param model   数据模型
     * @return 分类实体
     */
    @GetMapping("/search")
    @ResponseBody
    public ResponseVO search(ClassificationVO classificationVO, PageVO pageVO, Model model) {
        ResponseVO responseVO = new ResponseVO();
        SimpleCondition condition = new SimpleCondition();
        condition.andLike("className", classificationVO.getClassName());// 只查询数据
        try {
            PageVO vo = classificationService.findByConditionPage(condition, pageVO);
            responseVO = responseVO.ok(vo);
        } catch (Exception e) {
            e.printStackTrace();
            responseVO.error("获取失败");
        }
        return responseVO;
    }


    /**
     * 读取
     *
     * @param classificationVO 分类实体
     * @param model 数据模型
     * @return 分类实体
     */
    @GetMapping("/read")
    @ResponseBody
    public ClassificationVO read(ClassificationVO classificationVO, Model model) {
        try {
            return classificationService.selectByPrimaryKey(classificationVO.getId());
        } catch (Exception ex) {
//            super.handleException(classificationVO, ExceptionConstant.OPT_TYPE_READ);
//            logger.error(classificationVO.getMessage(), ex);
        }
        return classificationVO;
    }

    @PostMapping("/update")
    @ResponseBody
    public ResponseVO updateClassificationVO(ClassificationVO classificationVO){
        ResponseVO responseVO = new ResponseVO();
        try {
            classificationService.updateClassificationVO(classificationVO);
            responseVO.ok("更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            responseVO.error("更新失败");
        }
        return responseVO;
    }

    @PostMapping("/del")
    @ResponseBody
    public ResponseVO deleteClassificationVO(String ids){
        ResponseVO responseVO = new ResponseVO();
        try {
            classificationService.deleteClassificationVO(ids);
            responseVO.ok("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            responseVO.error("删除失败");
        }
        return responseVO;
    }


    @PostMapping("/add")
    @ResponseBody
    public ResponseVO addClassificationVO(ClassificationVO classificationVO){
        ResponseVO responseVO = new ResponseVO();
        try {
            classificationVO.setAddDate(new Timestamp(System.currentTimeMillis()));
            classificationService.save(classificationVO);
            responseVO.ok("新增成功");
        } catch (Exception e) {
            System.out.println("新增失败" + e);
            responseVO.ok("新增失败" + e);
        }
        return responseVO;
    }
}
