package cn.doanything.payment.domain.flux;

import cn.doanything.commons.enums.CodeEnum;
import lombok.Getter;

/**
 * @author wxj
 * 2024/1/20
 */
@Getter
public enum FluxStatus implements CodeEnum {

    ACCEPT("ACCEPT", "已受理"),

    PROCESSING("PROCESSING", "处理中"),

    FAIL("FAIL", "失败"),

    SUCCESS("SUCCESS", "成功"),
    ;

    @Getter
    private String code;

    @Getter
    private String displayName;

    FluxStatus(String code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }
}
