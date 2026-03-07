package com.wuzl.domain.VO;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户注册VO
 */
@Data
public class UserRegisterVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String password;
    private String againPassword;
    private String nickname;
}
