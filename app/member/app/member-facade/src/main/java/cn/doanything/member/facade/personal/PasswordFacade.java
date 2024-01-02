package cn.doanything.member.facade.personal;

import cn.doanything.commons.response.ResponseResult;
import cn.doanything.member.facade.personal.dto.LoginRequest;

/**
 * 个人用户密码相关操作
 * @author wxj
 * 2024/1/1
 */
public interface PasswordFacade {

    /**
     * 登录验证
     * @param request
     * @return
     */
    ResponseResult<String> loginValidate(LoginRequest request);
}
