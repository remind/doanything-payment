package cn.doanything.member.types;

import cn.doanything.commons.enums.CodeEnum;

/**
 * 性别枚举
 *
 * @author wxj
 * 2023/12/10
 */
public enum Gender implements CodeEnum {

    UNKNOWN("0", "保密"),
    MALE("1", "男性"),
    FEMALE("2", "女性");

    /**
     * 代码,只能为1位正整数
     */
    private final String code;
    /**
     * 信息
     */
    private final String displayName;

    Gender(String code, String displayName) {
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
