package cn.doanything.commons.exceptions;

import cn.doanything.commons.response.GlobalResultCode;
import cn.doanything.commons.response.ResultCode;
import org.apache.commons.lang3.StringUtils;

/**
 * 业务异常
 * @author wxj
 * 2023/12/16
 */
public class BizException extends RuntimeException {

    private final ResultCode resultCode;

    public BizException(ResultCode resultCode) {
        this(resultCode, null);
    }
    public BizException(String message) {
        this(null, message);
    }

    public BizException(ResultCode resultCode, String message) {
        super(StringUtils.isNotBlank(message) ? message : resultCode.getMessage());
        this.resultCode = resultCode != null ? resultCode : GlobalResultCode.FAIL;
    }

    public ResultCode getResultCode() {
        return resultCode;
    }

}
