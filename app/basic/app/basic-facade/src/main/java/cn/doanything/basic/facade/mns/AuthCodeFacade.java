package cn.doanything.basic.facade.mns;

import cn.doanything.basic.facade.mns.dto.AuthCodeRequest;
import cn.doanything.commons.response.ResponseResult;

/**
 * 短信验证码服务
 *
 * @author wxj
 * 2024/1/7
 */
public interface AuthCodeFacade {

    /**
     * 发送验证码
     *
     * @param request
     * @return
     */
    ResponseResult<String> sendAuthCode(AuthCodeRequest request);

    /**
     * 验证验证码
     * @param request
     * @return
     */
    ResponseResult<String> validate(AuthCodeRequest request);

}
