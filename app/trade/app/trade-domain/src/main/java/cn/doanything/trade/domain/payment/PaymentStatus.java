package cn.doanything.trade.domain.payment;

import cn.doanything.commons.enums.CodeEnum;
import lombok.Getter;

/**
 * 支付状态
 *
 * @author wxj
 * 2024/2/2
 */
@Getter
public enum PaymentStatus implements CodeEnum {

    INIT("I", "初始化"),
    PROCESS("P", "处理中"),
    CANCEL("C", "撤销"),
    SUCCESS("S", "成功"),
    FAIL("F", "失败"),
    ;

    private String code;

    private String displayName;

    PaymentStatus(String code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }
}