package cn.doanything.account.types;

import cn.doanything.commons.response.ResultCode;

/**
 * @author wxj
 * 2023/12/16
 */
public enum AccountResultCode implements ResultCode {

    ACCOUNT_ID_NOT_EXISTS("ACCOUNT_ID_NOT_EXISTS","账户不存在"),
    ACCOUNT_LOCK_TIME_OUT("ACCOUNT_LOCK_TIME_OUT", "账户锁定超时"),
    SUB_ACCOUNT_NOT_EXISTS("SUB_ACCOUNT_NOT_EXISTS", "不存在指定的子账户"),

    ;

    private final String code;

    private final String message;

    AccountResultCode(String code, String message) {
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
