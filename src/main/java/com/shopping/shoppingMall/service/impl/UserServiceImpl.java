package com.shopping.shoppingMall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lckj.core.model.PagerVO;
import com.lckj.core.orm.MybatisMapper;
import com.lckj.core.service.AbstractService;
import com.shopping.shoppingMall.common.entity.PageVO;
import com.shopping.shoppingMall.dao.UserVOMapper;
import com.shopping.shoppingMall.entity.UserVO;
import com.shopping.shoppingMall.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl extends AbstractService<UserVO> implements UserService {

        @Resource
        UserVOMapper userVOMapper;

        /** 获取Mapper接口 */
        @Override
        protected MybatisMapper<UserVO> getMapper() {
    return this.userVOMapper;
}


        @Override
        public UserVO selectByPrimaryKey(int id) {
                return userVOMapper.selectByPrimaryKey(id);
                }



        @Override
        public int save(UserVO userVO) {
                return userVOMapper.insertSelective(userVO);
                }

        @Override
        public List<UserVO> findListHasAccount(int flag, PageVO pageVO) {
                PageHelper.startPage(pageVO.getPageNum(),pageVO.getPageSize(),false,false,false);
                return userVOMapper.findListHasAccount(flag);
        }


        @Override
        public PageVO findListHasAccountPage(int flag, PageVO pageVO) {
                PageHelper.startPage(pageVO.getPageNum(), pageVO.getPageSize());
                List<UserVO> list = userVOMapper.findListHasAccount(flag);
                PageInfo<UserVO> pageInfo = new PageInfo<>( list);
                PageHelper.clearPage();
                PageVO vo = new PageVO();
                vo.setPageSize(pageVO.getPageSize());
                vo.setPageNum(pageVO.getPageNum());
                vo.setRows(pageInfo.getList());
                vo.setTotal(pageInfo.getTotal());
                return vo;
        }

        @Override
        public void deleteUserVO(String ids) {
                List<String> list = Arrays.asList(ids.split(","));
                for(int i = 0; i < list.size(); i++){
                        userVOMapper.deleteByPrimaryKey(Integer.valueOf(list.get(i)));
                }
        }

        @Override
        public void updateUserVO(UserVO userVO) {
                userVOMapper.updateByPrimaryKey(userVO);
                }

        @Override
        public UserVO findById(int id) {
                return userVOMapper.selectByPrimaryKey(id);
                }

        @Override
        public UserVO findByAccount(String account) {
        return userVOMapper.findByAccount(account);
}
}
