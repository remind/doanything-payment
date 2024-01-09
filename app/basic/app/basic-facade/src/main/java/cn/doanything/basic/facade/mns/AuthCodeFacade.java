package cn.doanything.basic.facade.mns;

import cn.doanything.basic.facade.mns.dto.AuthCodeMessageRequest;
import cn.doanything.basic.facade.mns.dto.AuthCodeValidateRequest;
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
    ResponseResult<String> sendAuthCode(AuthCodeMessageRequest request);

    /**
     * 验证验证码
     * @param request
     * @return
     */
    ResponseResult<String> validate(AuthCodeValidateRequest request);

}
