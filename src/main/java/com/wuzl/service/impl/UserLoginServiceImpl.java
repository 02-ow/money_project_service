package com.wuzl.service.impl;

import com.wuzl.domain.PO.MyUserDetails;
import com.wuzl.domain.PO.UserPO;
import com.wuzl.domain.VO.UserRegisterVO;
import com.wuzl.domain.VO.UserVO;
import com.wuzl.mapper.UserLoginMapper;
import com.wuzl.service.UserLoginService;
import com.wuzl.utils.JWTUtil;
import com.wuzl.utils.UserIDUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户登录服务实现
 */
@Service
public class UserLoginServiceImpl implements UserLoginService {

    @Resource
    private UserLoginMapper userLoginMapper;
    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private JWTUtil jwtUtil;

    /**
     * 登录方法实现
     * @param userVo
     * @return
     */
    @Override
    public String login(UserVO userVo) {
        MyUserDetails myUserDetails = loadUserByUsername(userVo.getUsername());
        boolean isLogin = passwordEncoder.matches(userVo.getPassword(), myUserDetails.getPassword());
        if (!isLogin) {
            throw new RuntimeException("密码错误");
        }
        // 实现登录
        UsernamePasswordAuthenticationToken authenticationToken =
            new UsernamePasswordAuthenticationToken(myUserDetails, null, null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        // 生成JWT token
        return jwtUtil.generateToken(myUserDetails);
    }

    public MyUserDetails loadUserByUsername(String username) {
        // 根据账号获取用户信息
        UserPO userPo = userLoginMapper.getUserInfo(username);
        MyUserDetails myUserDetails = new MyUserDetails();
        myUserDetails.setUserPO(userPo);
        return myUserDetails;
    }

    /**
     * 用户注册实现
     * @param userRegisterVO
     */
    @Override
    public void register(UserRegisterVO userRegisterVO) {
        // 相关验证
        boolean equals = userRegisterVO.getPassword().equals(userRegisterVO.getAgainPassword());
        if (!equals) {
            throw new RuntimeException("密码不一致");
        }
        UserPO userPO = new UserPO();
        BeanUtils.copyProperties(userRegisterVO,userPO);
        // 系统生成账号
        String username = UserIDUtil.generateUserID();
        userPO.setUsername(username);
        // 密码加密
        String password = passwordEncoder.encode(userRegisterVO.getPassword());
        userPO.setPassword(password);
        // 存入数据库
        userLoginMapper.saveUser(userPO);
    }
}
