package cn.doanything.trade.domain;

import cn.doanything.commons.lang.types.Money;
import cn.doanything.types.TradeType;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author wxj
 * 2024/1/31
 */
@Data
public class TradeOrder {

    /**
     * 交易单号
     */
    private String tradeId;

    /**
     * 交易类型
     */
    private TradeType tradeType;

    /**
     * 交易金额
     */
    private Money amount;

    /**
     * 产品码
     */
    private String productCode;

    /**
     * 提交时间
     */
    private LocalDateTime gmtSubmit;

    /**
     * 备注
     */
    private String memo;

}
