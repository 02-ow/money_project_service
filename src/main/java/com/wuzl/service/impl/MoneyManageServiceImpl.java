package com.wuzl.service.impl;

import com.wuzl.constant.UserContext;
import com.wuzl.domain.VO.MoneyInfoVO;
import com.wuzl.mapper.MoneyManageMapper;
import com.wuzl.service.MoneyManageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 用户资产服务实现
 */
@Service
public class MoneyManageServiceImpl implements MoneyManageService {

    @Resource
    private MoneyManageMapper moneyManageMapper;

    @Override
    public Map<String, List<MoneyInfoVO>> queryMoney() {
        List<MoneyInfoVO> list = moneyManageMapper.queryMoney(UserContext.getUserId());
        return list.stream().collect(Collectors.groupingBy(MoneyInfoVO::getMoneyType));
    }
}
