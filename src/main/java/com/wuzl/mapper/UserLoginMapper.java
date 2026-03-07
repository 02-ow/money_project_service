package com.wuzl.mapper;


import com.wuzl.domain.PO.UserPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户相关持久层
 */
@Mapper
public interface UserLoginMapper {
    /**
     * 获取用户信息
     * @param username
     * @return
     */
    UserPO getUserInfo(String username);

    /**
     * 保存用户
     * @param userPO
     */
    void saveUser(UserPO userPO);
}
