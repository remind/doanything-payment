package cn.doanything.account.types.accounting;

import cn.doanything.commons.enums.CodeEnum;

/**
 * 入账规则
 * @author wxj
 * 2023/12/19
 */
public enum AccountingRule implements CodeEnum {
    ;
    private final String code;
    private final String displayName;


    AccountingRule(String code, String displayName) {
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
     * @return AccountingRule
     */
    public static AccountingRule getByCode(String code) {
        for (AccountingRule enumObject : values()) {
            if (enumObject.code.equals(code)) {
                return enumObject;
            }
        }
        return null;
    }
}
