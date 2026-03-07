package com.wuzl.service;


import com.wuzl.domain.PO.MyUserDetails;
import com.wuzl.domain.VO.UserVO;

/**
 * 用户登录服务接口
 */
public interface UserLoginService {
    /**
     * 登录方法
     * @param userVo
     * @return
     */
    String login(UserVO userVo);

    MyUserDetails loadUserByUsername(String username);
}
