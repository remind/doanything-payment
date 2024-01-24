package cn.doanything.payment.domain.flux;

import cn.doanything.commons.enums.CodeEnum;
import lombok.Getter;

/**
 * @author wxj
 * 2024/1/23
 */
@Getter
public enum InstructType implements CodeEnum {

    PAY("PAY", "支付"),

    REFUND("REFUND", "退款"),

    ;

    private final String code;

    private final String displayName;

    InstructType(String code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }
}
