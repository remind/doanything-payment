package cn.doanything.types.status;

import cn.doanything.commons.enums.CodeEnum;
import lombok.Getter;

/**
 * @author wxj
 * 2024/2/2
 */
@Getter
public enum TransferOrderStatus implements CodeEnum, TradeOrderStatus {

    INIT("10", "初始化"),
    PAYING("20", "转账中"),
    SUCCESS("30", "转账成功"),
    FAIL("40", "转账失败"),
    ;

    private final String code;

    private final String displayName;

    TransferOrderStatus(String code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }
}