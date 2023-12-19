package cn.doanything.account.types.enums;

import cn.doanything.commons.enums.CodeEnum;

/**
 * 账户分类
 * @author wxj
 * 2023/12/16
 */
public enum AccountFamily  implements CodeEnum {
    INNER("1", "内部账户"),

    OUTER("2", "客户账户");

    /** 代码 */
    private final String    code;
    /** 名称  */
    private final String displayName;

    /**
     * 构造
     * @param code
     * @param displayName
     */
    AccountFamily(String code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }

    /**
     * 根据代码获取枚举
     * @param code
     * @return AccountFamily
     */
    public static AccountFamily getByCode(String code) {
        for (AccountFamily enumObject : AccountFamily.values()) {
            if (enumObject.code().equals(code)) {
                return enumObject;
            }
        }

        return null;
    }
    @Override
    public String code() {
        return code;
    }

    @Override
    public String displayName() {
        return displayName;
    }
}
