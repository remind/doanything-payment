package cn.doanything.types.status;

import cn.doanything.commons.enums.CodeEnum;
import lombok.Getter;

/**
 * @author wxj
 * 2024/2/7
 */
@Getter
public enum SplitOrderStatus implements CodeEnum, TradeOrderStatus {

    WAIT_SPLIT("10", "待分账"),

    SPLIT_SUCCESS("20", "分账成功"),

    SPLIT_FAIL("90","分账失败");

    private final String code;

    private final String displayName;

    SplitOrderStatus(String code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }
}