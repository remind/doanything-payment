package cn.doanything.types.status;

import cn.doanything.commons.enums.CodeEnum;
import lombok.Getter;

/**
 * @author wxj
 * 2024/2/3
 */
@Getter
public enum FundOrderStatus implements CodeEnum {

    INIT("I", "初始化"),
    PROCESS("P", "处理中"),
    CANCEL("C", "撤销"),
    SUCCESS("S", "成功"),
    FAIL("F", "失败"),
    ;

    private String code;

    private String displayName;

    FundOrderStatus(String code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }
}