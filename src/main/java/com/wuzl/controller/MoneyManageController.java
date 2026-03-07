package com.wuzl.controller;


import com.wuzl.common.ResultBean;
import com.wuzl.constant.UserContext;
import com.wuzl.domain.VO.MoneyInfoVO;
import com.wuzl.service.MoneyManageService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 用户资产接口
 */
@RestController
@RequestMapping("money")
public class MoneyManageController {

    @Resource
    private MoneyManageService moneyManageService;
    /**
     * 查询资产
     * @return
     */
    @PostMapping("queryMoney")
    public ResultBean<Map<String, List<MoneyInfoVO>>> queryMoney() {
        Map<String, List<MoneyInfoVO>> list = moneyManageService.queryMoney();
        return ResultBean.success(list);
    }
}
