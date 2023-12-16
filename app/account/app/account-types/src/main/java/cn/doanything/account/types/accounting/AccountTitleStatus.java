package cn.doanything.account.types.accounting;

import cn.doanything.commons.enums.CodeEnum;

public enum AccountTitleStatus implements CodeEnum {
    VALID("1","有效"),
    INVALID("0","无效");


    private final String code;
    private final String displayName;


    AccountTitleStatus(String code, String displayName) {
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
     * @return AccountType
     */
    public static AccountTitleStatus getByCode(String code) {
        for (AccountTitleStatus accountTitleStatus : values()) {
            if (accountTitleStatus.code.equals(code)) {
                return accountTitleStatus;
            }
        }
        return null;
    }
}
