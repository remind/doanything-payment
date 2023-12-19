package cn.doanything.account.types.accounting;

import cn.doanything.commons.enums.CodeEnum;


/**
 *
 */
public enum AccountTitleType implements CodeEnum {
    Assets("1","资产类"),
    Liabilities("2","负债类"),
    OwnersEquity("3","所有者权益"),
    Public("4","公共类"),
    IncreaseAndDecrease("5","损益类");

    private final String code;
    private final String displayName;


    AccountTitleType(String code, String displayName) {
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
    public static AccountTitleType getByCode(String code) {
        for (AccountTitleType enumObject : values()) {
            if (enumObject.code.equals(code)) {
                return enumObject;
            }
        }

        return null;
    }
}
