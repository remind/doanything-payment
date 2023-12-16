package cn.doanything.account.types.enums;

import cn.doanything.commons.enums.CodeEnum;

/**
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
        for (IODirection ioDirection : IODirection.values()) {
            if (ioDirection.code().equals(code)) {
                return ioDirection;
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
