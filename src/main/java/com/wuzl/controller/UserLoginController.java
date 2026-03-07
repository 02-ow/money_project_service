package com.wuzl.controller;


import com.wuzl.common.ResultBean;
import com.wuzl.domain.VO.UserRegisterVO;
import com.wuzl.domain.VO.UserVO;
import com.wuzl.service.UserLoginService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * 用户相关接口
 */
@RestController
@RequestMapping("user")
public class UserLoginController {
    @Resource
    private UserLoginService userLoginService;

    /**
     * 用户登录
     */
    @PostMapping("login")
    public ResultBean<String> login(UserVO userVo){
        String token = userLoginService.login(userVo);
        return ResultBean.success(token);
    }

    /**
     * 用户注册
     */
    @PostMapping("register")
    public ResultBean<String> register(UserRegisterVO userRegisterVO){
        userLoginService.register(userRegisterVO);
        return ResultBean.success();
    }
}
