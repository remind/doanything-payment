package cn.doanything.paycore.types;

import lombok.Getter;

/**
 * @author wxj
 * 2024/1/26
 */
@Getter
public enum PayStatus implements OrderStatus {

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