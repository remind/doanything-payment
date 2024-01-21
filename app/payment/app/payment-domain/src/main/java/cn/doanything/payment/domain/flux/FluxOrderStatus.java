package cn.doanything.payment.domain.flux;

import cn.doanything.commons.enums.CodeEnum;
import lombok.Getter;

/**
 * @author wxj
 * 2024/1/20
 */
@Getter
public enum FluxOrderStatus implements CodeEnum {

    ACCEPT("ACCEPT", "已受理"),

    PROCESSING("PROCESSING", "处理中"),

    FAIL("FAIL", "失败"),

    SUCCESS("SUCCESS", "成功"),
    ;

    private final String code;

    private final String displayName;

    FluxOrderStatus(String code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }
}
