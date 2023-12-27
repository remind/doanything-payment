package cn.doanything.account.types.enums;

import cn.doanything.commons.enums.CodeEnum;

/**
 * 借贷方向
 * @author wxj
 * 2023/12/16
 */
public enum CrDr implements CodeEnum {

    DEBIT("1", "借方"),

    CREDIT("2", "贷方");
    ;

    private final String code;

    private final String displayName;

    CrDr(String code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }

    /**
     * 根据代码获取枚举
     * @param code
     * @return
     */
    public static CrDr getByCode(String code) {
        for (CrDr enumObject : CrDr.values()) {
            if (enumObject.getCode().equals(code)) {
                return enumObject;
            }
        }

        return null;
    }

    /**
     * 获取反向借贷
     * @return
     */
    public static CrDr reverse(CrDr crDr) {
        if (crDr.getCode().equals(DEBIT.getCode())) {
            return CREDIT;
        } else if (crDr.getCode().equals(CREDIT.getCode())) {
            return DEBIT;
        }
        return null;
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
