package cn.doanything.trade;

import cn.doanything.commons.enums.CodeEnum;
import lombok.Getter;

/**
 * @author wxj
 * 2024/1/31
 */
@Getter
public enum TradeType implements CodeEnum {

    TRANSFER("01", "普通转账交易");

    private String code;

    private String displayName;

    TradeType(String code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }
}
