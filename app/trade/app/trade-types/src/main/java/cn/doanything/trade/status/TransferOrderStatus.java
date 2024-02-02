package cn.doanything.trade.status;

import cn.doanything.commons.enums.CodeEnum;
import lombok.Getter;

/**
 * @author wxj
 * 2024/2/2
 */
@Getter
public enum TransferOrderStatus implements CodeEnum, TradeOrderStatus {

    INIT("01", "初始化"),
    PAYING("02", "转账中"),
    SUCCESS("03", "转账成功"),
    FAIL("04", "转账失败"),
    ;

    private String code;

    private String displayName;

    TransferOrderStatus(String code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }
}