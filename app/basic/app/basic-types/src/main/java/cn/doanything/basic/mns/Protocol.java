package cn.doanything.basic.mns;

import cn.doanything.commons.enums.CodeEnum;

/**
 * 消息协议
 *
 * @author wxj
 * 2024/1/6
 */
public enum Protocol implements CodeEnum {
    MAIL("M", "邮件"),
    SNS("S", "手机短信"),
    APP("A", "应用推送"),

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
    Protocol(String code, String displayName) {
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
