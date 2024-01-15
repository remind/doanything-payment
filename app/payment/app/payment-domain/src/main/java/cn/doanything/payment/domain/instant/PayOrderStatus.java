package cn.doanything.payment.domain.instant;

import cn.doanything.payment.domain.OrderStatus;
import lombok.Getter;

/**
 * @author wxj
 * 2024/1/15
 */
public enum PayOrderStatus implements OrderStatus {

    INIT("INIT", "初始化"),

    PAYING("PAYING", "支付中"),

    FAIL("FAIL", "失败"),

    SUCCESS("SUCCESS", "成功"),
    ;

    @Getter
    private String code;

    @Getter
    private String name;

    PayOrderStatus(String code, String name) {
        this.code = code;
        this.name = name;
    }
}
