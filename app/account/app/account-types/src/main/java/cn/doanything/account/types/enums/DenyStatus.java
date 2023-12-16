package cn.doanything.account.types.enums;

import cn.doanything.commons.enums.CodeEnum;

/**
 * @author wxj
 * 2023/12/16
 */
public enum DenyStatus implements CodeEnum {
    INIT("0", "未冻结"),

    DENY_OUT("1", "止出"),

    DENY_IN("2", "止入"),

    DENY_IN_OUT("3", "止入止出");

    private final String code;
    private final String displayName;

    DenyStatus(String code, String displayName) {
        this.code = code;
        this.displayName = displayName;

    }

    /**
     * 根据代码获取枚举
     *
     * @param code
     * @return
     */
    public static DenyStatus getByCode(String code) {
        for (DenyStatus c : DenyStatus.values()) {
            if (c.code().equals(code)) {
                return c;
            }
        }

        return null;
    }

    public String code() {
        return code;
    }

    public String displayName() {
        return displayName;
    }
}
