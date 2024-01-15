package cn.doanything.payment.domain.instant;

import cn.doanything.payment.domain.OrderStatus;
import lombok.Getter;

/**
 * 退款状态
 * @author remind
 * 2023年05月06日 21:37
 */
@Getter
public enum RefundOrderStatus implements OrderStatus {

    SUCCESS("SUCCESS", "成功"),
    ;

    private String code;

    private String name;

    RefundOrderStatus(String code, String name) {
        this.code = code;
        this.name = name;
    }
}