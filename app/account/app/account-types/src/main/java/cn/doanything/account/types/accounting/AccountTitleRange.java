package cn.doanything.account.types.accounting;

import cn.doanything.commons.enums.CodeEnum;

/**
 * 科目适用范围
 *
 * @author wxj
 * 2023/12/16
 */
public enum AccountTitleRange implements CodeEnum {
    INNER_ENTRIES("1", "内部科目"),
    OUTER_ENTRIES("2", "外部科目");

    private final String code;
    private final String displayName;


    AccountTitleRange(String code, String displayName) {
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
     * @return AccountTitleRange
     */
    public static AccountTitleRange getByCode(String code) {
        for (AccountTitleRange enumObject : values()) {
            if (enumObject.code.equals(code)) {
                return enumObject;
            }
        }
        return null;
    }
}
