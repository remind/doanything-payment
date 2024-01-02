package cn.doanything.member.domain.personal.service;

import cn.doanything.member.types.PasswordType;

/**
 * @author wxj
 * 2024/1/2
 */
public interface PasswordDomainService {

    void loginValidate(String loginName, String password, PasswordType type);
}
