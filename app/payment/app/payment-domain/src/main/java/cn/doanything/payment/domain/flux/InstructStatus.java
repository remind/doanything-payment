package cn.doanything.payment.domain.flux;

import cn.doanything.commons.enums.CodeEnum;
import lombok.Getter;

/**
 * @author wxj
 * 2024/1/20
 */
@Getter
public enum InstructStatus implements CodeEnum {

    INIT("INIT", "初始化"),

    PROCESSING("PROCESSING", "处理中"),

    FAIL("FAIL", "失败"),

    SUCCESS("SUCCESS", "成功"),
    ;

    private final String code;

    private final String displayName;

    InstructStatus(String code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }
}
