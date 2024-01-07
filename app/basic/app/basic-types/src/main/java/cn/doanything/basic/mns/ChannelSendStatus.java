package cn.doanything.basic.mns;

import cn.doanything.commons.enums.CodeEnum;

/**
 * 渠道发送状态
 * @author wxj
 * 2024/1/7
 */
public enum ChannelSendStatus implements CodeEnum {
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
    ChannelSendStatus(String code, String displayName) {
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
