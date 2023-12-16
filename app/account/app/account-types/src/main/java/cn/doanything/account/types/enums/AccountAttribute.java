package cn.doanything.account.types.enums;

import cn.doanything.commons.enums.CodeEnum;

/**
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
    public String code() {
        return code;
    }

    @Override
    public String displayName() {
        return displayName;
    }
    /**
     * 通过枚举code获得枚举
     *
     * @param code
     * @return AccountAttribute
     */
    public static AccountAttribute getByCode(String code) {
        for (AccountAttribute accountAttribute : values()) {
            if (accountAttribute.code().equals(code)) {
                return accountAttribute;
            }
        }
        return null;
    }
}
