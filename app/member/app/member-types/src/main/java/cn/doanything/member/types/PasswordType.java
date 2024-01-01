package cn.doanything.member.types;

import cn.doanything.commons.enums.CodeEnum;

/**
 * 密码类型
 * @author wxj
 * 2024/1/1
 */
public enum PasswordType implements CodeEnum {

    TEXT("1", "文本密码"),
    FINGER("2", "指纹密码"),
    GESTURE("3", "手势密码"),
    ;

    private final String code;

    private final String displayName;

    PasswordType(String code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getDisplayName() {
        return this.displayName;
    }
}
