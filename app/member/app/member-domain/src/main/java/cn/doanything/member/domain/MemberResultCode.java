package cn.doanything.member.domain;

import cn.doanything.commons.response.ResultCode;

/**
 * @author wxj
 * 2024/1/2
 */
public enum MemberResultCode implements ResultCode {

    MEMBER_NOT_EXISTS("MEMBER_NOT_EXISTS", "用户不存在"),
    PASSWORD_ERROR("PASSWORD_ERROR", "密码错误"),
    PASSWORD_LOCKED("PASSWORD_LOCKED", "密码被锁定"),
    PASSWORD_NOT_EXISTS("PASSWORD_NOT_EXISTS", "密码不存在"),
    ;

    private final String code;

    private final String message;

    MemberResultCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
