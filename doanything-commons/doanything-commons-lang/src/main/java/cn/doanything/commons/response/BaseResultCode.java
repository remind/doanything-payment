package cn.doanything.commons.response;


/**
 * 结果码
 */
public enum BaseResultCode implements ResultCode {
    SUCCESS("S001", "成功")
    ;

    private final String code;

    private final String message;

    BaseResultCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
