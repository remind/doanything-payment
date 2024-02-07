package cn.doanything.types.status;

import cn.doanything.commons.enums.CodeEnum;
import lombok.Getter;

/**
 * 收单交易状态
 * @author wxj
 * 2024/2/7
 */
@Getter
public enum AcquiringTradeStatus implements CodeEnum, TradeOrderStatus {

    /**
     * 创建时，等待支付
     */
    WAIT_PAY("10", "待付款"),

    /**
     * 支付成功
     */
    PAY_SUCCESS("20", "付款成功"),

    /**
     * 支付成功，卖家待结算户收到款，但还没有结算，如果是实时结算的会直接跳过该状态
     */
    TRADE_SUCCESS("30", "交易成功"),

    /**
     * 对卖家结算结束
     */
    TRADE_FINISHED("40", "交易结束"),

    /**
     * 超时或者商家主动关闭
     */
    TRADE_CLOSED("90", "交易关闭");

    private final String code;

    private final String displayName;

    AcquiringTradeStatus(String code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }
}
