package cn.doanything.commons.exceptions;

import cn.doanything.commons.response.BaseResultCode;
import cn.doanything.commons.response.ResultCode;
import org.apache.commons.lang3.StringUtils;

/**
 * @author wxj
 * 2023/12/16
 */
public class BaseException extends RuntimeException {

    private final ResultCode resultCode;

    private final String message;

    public BaseException(ResultCode resultCode) {
        super(resultCode.getMessage());
        this.resultCode = resultCode;
        this.message = null;
    }

    public BaseException(ResultCode resultCode, String message) {
        super(StringUtils.isBlank(message) ? message : resultCode.getCode());
        this.resultCode = resultCode != null ? resultCode : BaseResultCode.FAIL;
        this.message = StringUtils.isBlank(message) ? message : this.resultCode.getCode();
    }

    public BaseException(String message) {
        super(message);
        this.resultCode = null;
        this.message = message;
    }

    public ResultCode getResultCode() {
        return resultCode;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
