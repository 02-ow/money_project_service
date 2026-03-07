package com.wuzl.service;

import com.wuzl.domain.VO.MoneyInfoVO;

import java.util.List;
import java.util.Map;

public interface MoneyManageService {
    /**
     * 查询资产
     * @return
     */
    Map<String, List<MoneyInfoVO>> queryMoney();
}
