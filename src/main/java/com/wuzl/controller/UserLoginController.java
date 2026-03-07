package com.wuzl.controller;


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
    public String login(UserVO userVo){
        String token = userLoginService.login(userVo);
        return token;
    }
}
