package com.wuzl.service.impl;

import com.wuzl.domain.PO.MyUserDetails;
import com.wuzl.domain.PO.UserPO;
import com.wuzl.domain.VO.UserVO;
import com.wuzl.mapper.UserLoginMapper;
import com.wuzl.service.UserLoginService;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户登录服务实现
 */
@Service
public class UserLoginServiceImpl implements UserLoginService {

    @Resource
    private UserLoginMapper userLoginMapper;

    /**
     * 登录方法实现
     * @param userVo
     * @return
     */
    @Override
    public String login(UserVO userVo) {
        MyUserDetails myUserDetails = loadUserByUsername(userVo.getUsername());
        return "";
    }

    public MyUserDetails loadUserByUsername(String username) {
        // 根据账号获取用户信息
        UserPO userPo = userLoginMapper.getUserInfo(username);
        MyUserDetails myUserDetails = new MyUserDetails();
        myUserDetails.setUserPO(userPo);
        return myUserDetails;
    }
}
