package cn.doanything.member.facade.personal;

import cn.doanything.commons.response.ResponseResult;
import cn.doanything.member.facade.personal.dto.LoginRequest;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @author wxj
 * 2024/1/1
 */
@DubboService
public class PasswordFacadeImpl implements PasswordFacade {
    @Override
    public ResponseResult<String> loginValidate(LoginRequest request) {
        return null;
    }
}
