package cn.doanything.trade.domain;

import cn.doanything.commons.enums.CodeEnum;
import lombok.Getter;

/**
 * @author wxj
 * 2024/2/3
 */
@Getter
public enum IdType implements CodeEnum {

    TRADE_ORDER_ID("1", "seq_trade_order_id", "交易单"),
    PAYMENT_ORDER_ID("2", "seq_payment_order_id", "交易单"),
    ;

    /**
     * 1位数字
     */
    private final String code;

    private final String seqName;

    private final String displayName;

    IdType(String code, String seqName, String displayName) {
        this.code = code;
        this.seqName = seqName;
        this.displayName = displayName;
    }
}
