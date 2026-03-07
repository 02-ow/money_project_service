package com.wuzl.mapper;


import com.wuzl.domain.PO.UserPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户相关持久层
 */
@Mapper
public interface UserLoginMapper {

    UserPO getUserInfo(String username);
}
