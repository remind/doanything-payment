package cn.doanything.commons.payment;

import cn.doanything.commons.enums.CodeEnum;
import lombok.Getter;

/**
 * @author wxj
 * 2024/2/7
 */
@Getter
public enum SettleTimeType implements CodeEnum {

    DELAY("D", "延迟结算"),
    REAL("R", "实时结算")
    ;

    private final String code;

    private final String displayName;

    SettleTimeType(String code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }
}
