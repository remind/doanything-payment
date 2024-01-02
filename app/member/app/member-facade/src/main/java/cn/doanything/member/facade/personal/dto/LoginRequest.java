package cn.doanything.member.facade.personal.dto;

import cn.doanything.member.types.PasswordType;
import lombok.Data;

/**
 * 登录请求
 * @author wxj
 * 2024/1/1
 */
@Data
public class LoginRequest {

    /**
     * 登录名
     */
    private String loginName;

    /**
     * 密码类型
     */
    private PasswordType type;

    /**
     * 密码
     */
    private String password;

}
