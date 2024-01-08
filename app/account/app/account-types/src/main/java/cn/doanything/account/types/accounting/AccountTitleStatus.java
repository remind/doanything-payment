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
    public String getCode() {
        return code;
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }

}
