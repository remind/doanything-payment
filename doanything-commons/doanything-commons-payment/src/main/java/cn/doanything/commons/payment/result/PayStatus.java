package cn.doanything.commons.payment.result;

import cn.doanything.commons.enums.CodeEnum;
import lombok.Getter;

/**
 * @author wxj
 * 2024/1/26
 */
@Getter
public enum PayStatus implements CodeEnum {

    SUCCESS("SUCCESS", "成功"),
    FAIL("FAIL", "失败"),
    PROCESS("PROCESS", "处理中"),
    ;

    private String code;

    private String displayName;

    PayStatus(String code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }
}