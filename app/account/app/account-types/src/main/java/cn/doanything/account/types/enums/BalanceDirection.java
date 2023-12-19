package cn.doanything.account.types.enums;

import cn.doanything.commons.enums.CodeEnum;

/**
 * 余额方向
 * @author wxj
 * 2023/12/16
 */
public enum BalanceDirection implements CodeEnum {

    TWO_WAY("0","双向"),

    DEBIT("1", "借方"),

    CREDIT("2", "贷方");

    private final String code;
    private final String displayName;

    private BalanceDirection(String code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }

    /**
     * 根据code获取枚举
     *
     * @param code
     * @return BalanceDirection
     */
    public static BalanceDirection getByCode(String code) {
        for (BalanceDirection enumObject : BalanceDirection.values()) {
            if (enumObject.code().equals(code)) {
                return enumObject;
            }
        }
        return null;
    }


    @Override
    public String code() {
        return this.code;
    }

    @Override
    public String displayName() {
        return this.displayName;
    }
}
