package com.wuzl.domain.PO;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserPO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String Username;
    private String Password;
    private String nickname;
    private String effective = "1";


}
