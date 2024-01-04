package cn.doanything.member.facade.personal;

import cn.doanything.commons.response.ResponseResult;
import cn.doanything.member.types.PasswordType;
import cn.doanything.member.types.PasswordUseType;

/**
 * 个人用户密码相关操作
 * @author wxj
 * 2024/1/1
 */
public interface PasswordFacade {

    /**
     * 创建密码
     * @param memberId
     * @param useType
     * @param type
     * @param password
     */
    ResponseResult<String> create(String memberId, PasswordUseType useType, PasswordType type, String password);


    /**
     * 修改密码
     * @param memberId 会员ID
     * @param useType   密码使用类型
     * @param type  密码类型
     * @param oldPassword   老密码
     * @param newPassword   新密码
     * @return
     */
    ResponseResult<String> change(String memberId, PasswordUseType useType, PasswordType type, String oldPassword, String newPassword);

    /**
     * 登录验证
     * @param loginName
     * @param type
     * @param password
     * @return
     */
    ResponseResult<String> loginValidate(String loginName, PasswordType type, String password);
}
