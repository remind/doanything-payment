package cn.doanything.account.types.enums;

import cn.doanything.commons.enums.CodeEnum;

/**
 * 禁止状态
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
        for (DenyStatus enumObject : DenyStatus.values()) {
            if (enumObject.getCode().equals(code)) {
                return enumObject;
            }
        }

        return null;
    }

    public String getCode() {
        return code;
    }

    public String getDisplayName() {
        return displayName;
    }
}
