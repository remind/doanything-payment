package cn.doanything.basic.facade.mns;

import cn.doanything.basic.facade.mns.dto.AuthCodeSendRequest;
import cn.doanything.basic.facade.mns.dto.AuthCodeValidateRequest;
import cn.doanything.commons.response.ResponseResult;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

/**
 * @author wxj
 * 2024/1/8
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class AuthCodeFacadeTest {

    @Autowired
    private AuthCodeFacade authCodeFacade;

    @Test
    public void testSend() {
        AuthCodeSendRequest request = new AuthCodeSendRequest();
        request.setRequestId(getUUID());
        request.setMemberId("100000000201");
        request.setBatchId("123");
        request.setRecipient("135123");
        request.setSceneCode("findPassword");
        authCodeFacade.sendAuthCode(request);
    }

    @Test
    public void testValidate() {
        AuthCodeValidateRequest request = new AuthCodeValidateRequest();
        request.setSendRequestId("97c492117141437fbcfe6457a0bfc4d3");
        request.setAuthCode("513160");
        request.setSceneCode("findPassword");
        request.setMemberId("100000000201");
        request.setRecipient("135123");
        ResponseResult responseResult = authCodeFacade.validate(request);
        System.out.println(responseResult.getMessage());
        Assert.assertTrue(responseResult.isSuccess());
    }

    private String getUUID() {
        String uuid = UUID.randomUUID().toString();
        return uuid.replace("-","").substring(0,32);
    }
}
