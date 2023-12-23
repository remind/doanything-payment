package cn.doanything.account.facade.dto;

import cn.doanything.commons.lang.types.Money;
import lombok.Data;

/**
 * 资金分摊规则
 * @author wxj
 * 2023/12/23
 */
@Data
public class FundSpiltRule {
    /**
     * 资金类型
     */
    private String fundType;

    /**
     * 金额
     */
    private Money amount;
}
