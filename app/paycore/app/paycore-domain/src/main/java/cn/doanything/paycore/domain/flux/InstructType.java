package cn.doanything.paycore.domain.flux;

import cn.doanything.commons.enums.CodeEnum;
import lombok.Getter;

/**
 * @author wxj
 * 2024/1/23
 */
@Getter
public enum InstructType implements CodeEnum {

    FORWARD("FORWARD", "正向"),

    REVERSE("REVERSE", "逆向"),

    ;

    private final String code;

    private final String displayName;

    InstructType(String code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }
}
