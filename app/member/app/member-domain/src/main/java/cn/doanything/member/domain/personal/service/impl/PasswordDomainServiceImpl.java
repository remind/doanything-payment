package cn.doanything.member.domain.personal.service.impl;

import cn.doanything.member.domain.personal.service.PasswordDomainService;
import cn.doanything.member.types.PasswordType;
import org.springframework.stereotype.Service;

/**
 * @author wxj
 * 2024/1/2
 */
@Service
public class PasswordDomainServiceImpl implements PasswordDomainService {
    @Override
    public void loginValidate(String loginName, String password, PasswordType type) {

    }
}
