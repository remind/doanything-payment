package cn.doanything.commons.exceptions;

import cn.doanything.commons.response.ResultCode;
import cn.doanything.commons.response.GlobalResultCode;
import org.apache.commons.lang3.StringUtils;

/**
 * @author wxj
 * 2023/12/16
 */
public class BaseException extends RuntimeException {

    private final ResultCode resultCode;

    public BaseException(ResultCode resultCode) {
        this(resultCode, null);
    }
    public BaseException(String message) {
        this(null, message);
    }

    public BaseException(ResultCode resultCode, String message) {
        super(StringUtils.isNotBlank(message) ? message : resultCode.getMessage());
        this.resultCode = resultCode != null ? resultCode : GlobalResultCode.FAIL;
    }

    public ResultCode getResultCode() {
        return resultCode;
    }

}
