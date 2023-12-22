package cn.doanything.commons.exceptions;

import cn.doanything.commons.response.ResultCode;

/**
 * 业务类异常
 * @author wxj
 * 2023/12/22
 */
public class BizException extends BaseException {

    public BizException(ResultCode resultCode) {
        super(resultCode);
    }

    public BizException(String message) {
        super(message);
    }

    public BizException(ResultCode resultCode, String message) {
        super(resultCode, message);
    }
}
