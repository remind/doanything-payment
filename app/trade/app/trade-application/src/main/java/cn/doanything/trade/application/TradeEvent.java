package cn.doanything.trade.application;

import cn.doanything.commons.enums.CodeEnum;
import lombok.Getter;

/**
 * @author wxj
 * 2024/2/2
 */
@Getter
public enum TradeEvent implements CodeEnum {

    START_PAY("01", "发起支付"),
    PAY_SUCCESS("02", "支付成功"),
    PAY_FAIL("03", "支付失败"),
    REFUND_SUCCESS("04", "退款成功"),
    REFUND_FAIL("05", "退款失败"),
    ;

    private String code;

    private String displayName;

    TradeEvent(String code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }
}