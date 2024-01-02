package cn.doanything.member.types;

import cn.doanything.commons.enums.CodeEnum;

/**
 * 密码状态
 * @author wxj
 * 2024/1/1
 */
public enum PasswordStatus implements CodeEnum {

    NORMAL("1", "正常"),
    LOCK("2", "锁定"),
    ;

    private final String code;

    private final String displayName;

    PasswordStatus(String code, String displayName) {
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