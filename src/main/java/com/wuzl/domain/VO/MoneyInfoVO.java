package com.wuzl.domain.VO;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class MoneyInfoVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String userId;
    private String moneyType;
    private String unitPrice;
    private Date payTime;
}
