package cn.doanything.basic.mns;

import cn.doanything.commons.enums.CodeEnum;

/**
 * 通知类型
 *
 * @author wxj
 * 2024/1/6
 */
public enum NotifyType implements CodeEnum {
    REAL("R", "实时发送"),

    DELAY("D", "延时发送");

    /**
     * 代码
     */
    private final String code;
    /**
     * 名称
     */
    private final String displayName;

    /**
     * 构造
     *
     * @param code
     * @param displayName
     */
    NotifyType(String code, String displayName) {
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