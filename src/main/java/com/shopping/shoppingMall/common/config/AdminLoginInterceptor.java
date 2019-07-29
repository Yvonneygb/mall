package com.shopping.shoppingMall.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Description 管理员登陆拦截器
 * @Author RLinux
 * @Email RLinux_zwh@163.com
 * @Since 2019/6/19 23:06
 * @Version 1.0
 */
@Configuration
public class AdminLoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //得到session
        HttpSession session = httpServletRequest.getSession();
        // 得到对象
        Object admin = session.getAttribute("admin");

        // 判断对象是否存在
        if (admin == null){
            //这个方法返回false表示忽略当前请求，如果一个用户调用了需要登陆才能使用的接口，如果他没有登陆这里会直接忽略掉
            //当然你可以利用response给用户返回一些提示信息，告诉他没登陆
            //非法访问，没有登陆，跳转到登陆页面
            session.setAttribute("error", "非法访问");

            // 保存客户想要去的地址, 登录成功后则直接跳转,而不是到首页
            String goURL = httpServletRequest.getServletPath();//(获取到地址不包括参数)

            //判断参数是否为空，不null就获取参数
            if(httpServletRequest.getQueryString()!=null){
                goURL+="?"+httpServletRequest.getQueryString();
            }

            session.setAttribute("goURL", goURL);
            httpServletResponse.sendRedirect("/login");

            return false;
        }else {
            return true;    //如果session里有user，表示该用户已经登陆，放行，用户即可继续调用自己需要的接口
        }
    }
}
