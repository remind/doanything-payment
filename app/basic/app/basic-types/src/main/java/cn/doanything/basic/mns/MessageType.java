package cn.doanything.basic.mns;

import cn.doanything.commons.enums.CodeEnum;

/**
 * 消息类型
 * @author wxj
 * 2024/1/6
 */
public enum MessageType implements CodeEnum {
    NORMAL_TEXT("1", "普通文本"),
    AUTH_CODE("2", "验证码"),

    ;

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
    MessageType(String code, String displayName) {
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
