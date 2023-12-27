package cn.doanything.account.types.enums;

import cn.doanything.commons.enums.CodeEnum;

/**
 * 外部账户类型
 * @author wxj
 * 2023/12/16
 */
public enum AccountAttribute implements CodeEnum {

    PERSONAL("1", "对私"),
    COMPANY("2", "对公");

    private final String code;
    private final String displayName;

    AccountAttribute(String code, String displayName) {
        this.code = code;
        this.displayName = displayName;

    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }
    /**
     * 通过枚举code获得枚举
     *
     * @param code
     * @return AccountAttribute
     */
    public static AccountAttribute getByCode(String code) {
        for (AccountAttribute enumObject : values()) {
            if (enumObject.getCode().equals(code)) {
                return enumObject;
            }
        }
        return null;
    }
}
