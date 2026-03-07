package com.wuzl.mapper;

import com.wuzl.domain.VO.MoneyInfoVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 用户资产持久层
 */
@Mapper
public interface MoneyManageMapper {
    /**
     * 获取资产
     * @param userId
     * @return
     */
    List<MoneyInfoVO> queryMoney(String userId);
}
