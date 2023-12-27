package cn.doanything.account.types.enums;

import cn.doanything.commons.enums.CodeEnum;

/**
 * 账户实际余额方向
 * @author wxj
 * 2023/12/16
 */
public enum IODirection implements CodeEnum {

    IN("1", "加"),

    OUT("2", "减");
    ;

    private final String code;

    private final String displayName;

    IODirection(String code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }

    /**
     * 根据代码获取枚举
     * @param code
     * @return
     */
    public static IODirection getByCode(String code) {
        for (IODirection enumObject : IODirection.values()) {
            if (enumObject.getCode().equals(code)) {
                return enumObject;
            }
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
