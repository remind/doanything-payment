package cn.doanything.member.types;

import cn.doanything.commons.enums.CodeEnum;

/**
 * 密码使用分类
 * @author wxj
 * 2024/1/1
 */
public enum PasswordUseType implements CodeEnum {

    LOGIN_PASSWORD("1", "登录密码"),

    PAY_PASSWORD("2", "支付密码"),

    ;

    private final String code;

    private final String displayName;

    PasswordUseType(String code, String displayName) {
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
