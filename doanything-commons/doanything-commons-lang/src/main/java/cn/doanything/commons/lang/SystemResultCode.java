package cn.doanything.commons.lang;

import cn.doanything.commons.response.ResultCode;

/**
 * 系统结果码
 * @author wxj
 * 2023/12/22
 */
public enum SystemResultCode implements ResultCode {

    ILLEGAL_PARAM("ILLEGAL_PARAM", "非法参数"),
    ;

    private final String code;

    private final String message;

    SystemResultCode(String code, String message) {
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
