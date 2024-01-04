package cn.doanything.member.facade.personal;

import cn.doanything.commons.response.GlobalResultCode;
import cn.doanything.commons.response.ResponseResult;
import cn.doanything.member.domain.MemberResultCode;
import cn.doanything.member.types.PasswordType;
import cn.doanything.member.types.PasswordUseType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author wxj
 * 2024/1/2
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class PasswordFacadeTest {

    @Autowired
    private PasswordFacade passwordFacade;

    @Test
    public void testLoginValidate() {
        ResponseResult<String> successResult = passwordFacade.loginValidate("4567@qq.com", PasswordType.TEXT, "123");
        Assert.assertEquals(successResult.getCode(), GlobalResultCode.SUCCESS.getCode());

        ResponseResult<String> fail1Result = passwordFacade.loginValidate("45678@qq.com", PasswordType.TEXT, "123");
        Assert.assertEquals(fail1Result.getCode(), MemberResultCode.MEMBER_NOT_EXISTS.getCode());

        ResponseResult<String> fail2Result = passwordFacade.loginValidate("4567@qq.com", PasswordType.TEXT, "123456");
        Assert.assertEquals(fail2Result.getCode(), MemberResultCode.PASSWORD_ERROR.getCode());
    }

    @Test
    public void testCreateSuccess() {
        ResponseResult<String> result = passwordFacade.create("100000000201", PasswordUseType.LOGIN_PASSWORD, PasswordType.TEXT, "123");
        Assert.assertEquals(result.getCode(), GlobalResultCode.SUCCESS.getCode());
    }

    @Test
    public void testChangeSuccess() {
        ResponseResult<String> result = passwordFacade.change("100000000201", PasswordUseType.LOGIN_PASSWORD, PasswordType.TEXT, "123", "456");
        Assert.assertEquals(result.getCode(), GlobalResultCode.SUCCESS.getCode());
    }
}
