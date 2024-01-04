package cn.doanything.member.domain.personal.service;

import cn.doanything.member.types.PasswordType;
import cn.doanything.member.types.PasswordUseType;

/**
 * @author wxj
 * 2024/1/2
 */
public interface PasswordDomainService {

    /**
     * 设置密码
     *
     * @param memberId
     * @param useType
     * @param type
     * @param password
     */
    void create(String memberId, PasswordUseType useType, PasswordType type, String password);

    void change(String memberId, PasswordUseType useType, PasswordType type, String oldPassword, String newPassword);

    /**
     * 登录验证
     *
     * @param loginName
     * @param type
     * @param password
     */
    void loginValidate(String loginName, PasswordType type, String password);
}
