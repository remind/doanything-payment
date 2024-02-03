package cn.doanything.trade;

import cn.doanything.commons.enums.CodeEnum;
import lombok.Getter;

/**
 * @author wxj
 * 2024/1/31
 */
@Getter
public enum TradeType implements CodeEnum {

    DEPOSIT("01", "充值交易"),
    TRANSFER("02", "转账交易"),
    WITHDRAWAL("03", "提现交易"),

    ;

    private String code;

    private String displayName;

    TradeType(String code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }
}
