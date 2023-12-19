package cn.doanything.account.types.enums;

import cn.doanything.commons.enums.CodeEnum;

/**
 * 激活状态
 * @author wxj
 * 2023/12/16
 */
public enum ActivateStatus implements CodeEnum {
    NOTACTIVATED("0", "未激活"),

    ACTIVATED("1", "已激活");

    private final String code;
    private final String displayName;

    ActivateStatus(String code, String displayName) {
        this.code = code;
        this.displayName = displayName;

    }

    /**
     * 根据代码获取枚举
     *
     * @param code
     * @return ActivateStatus
     */
    public static ActivateStatus getByCode(String code) {
        for (ActivateStatus enumObject : ActivateStatus.values()) {
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
