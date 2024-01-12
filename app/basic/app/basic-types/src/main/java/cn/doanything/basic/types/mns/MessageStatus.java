package cn.doanything.basic.types.mns;

import cn.doanything.commons.enums.CodeEnum;

/**
 * 消息状态
 * @author wxj
 * 2024/1/6
 */
public enum MessageStatus implements CodeEnum {
    WAIT("W", "待发送"),

    FAIL("F", "失败"),

    SUCCESS("S", "成功");

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
    MessageStatus(String code, String displayName) {
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