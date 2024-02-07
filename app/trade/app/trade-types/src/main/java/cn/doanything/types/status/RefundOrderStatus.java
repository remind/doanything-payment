package cn.doanything.types.status;

import cn.doanything.commons.enums.CodeEnum;
import lombok.Getter;

/**
 * @author wxj
 * 2024/2/7
 */
@Getter
public enum RefundOrderStatus implements CodeEnum, TradeOrderStatus {


    WAIT_REFUND("10", "待退款"),

    REFUND_SUCCESS("20", "退款成功"),

    REFUND_FAIL("90","退款失败");

    private final String code;

    private final String displayName;

    RefundOrderStatus(String code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }
}
