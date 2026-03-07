package com.wuzl.controller;


import com.wuzl.common.ResultBean;
import com.wuzl.constant.UserContext;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("money")
public class MoneyManageController {

    @PostMapping("getMoney")
    public ResultBean<String> getMoney() {
        String userId = UserContext.getUserId();
        return ResultBean.success(userId);
    }
}
