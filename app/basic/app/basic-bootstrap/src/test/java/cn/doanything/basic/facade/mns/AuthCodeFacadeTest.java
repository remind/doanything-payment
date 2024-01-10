package cn.doanything.basic.facade.mns;

import cn.doanything.basic.facade.mns.dto.AuthCodeMessageRequest;
import cn.doanything.basic.facade.mns.dto.AuthCodeValidateRequest;
import cn.doanything.commons.response.ResponseResult;
import cn.doanything.framework.BaseTestBootStarter;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author wxj
 * 2024/1/8
 */
public class AuthCodeFacadeTest extends BaseTestBootStarter {

    @Autowired
    private AuthCodeFacade authCodeFacade;

    @Test
    public void testSend() throws InterruptedException {
        AuthCodeMessageRequest request = new AuthCodeMessageRequest();
        request.setRequestId(getUUID());
        request.setMemberId(randomPersonalMemberId());
        request.setBatchId("123");
        request.setRecipient("135123");
        request.setSceneCode("findPassword");
        authCodeFacade.sendAuthCode(request);

//        Thread.sleep(10000);
    }

    @Test
    public void testValidate() {
        AuthCodeValidateRequest request = new AuthCodeValidateRequest();
        request.setSendRequestId("97c492117141437fbcfe6457a0bfc4d3");
        request.setAuthCode("513160");
        request.setSceneCode("findPassword");
        request.setRecipient("135123");
        ResponseResult responseResult = authCodeFacade.validate(request);
        System.out.println(responseResult.getMessage());
        Assert.assertTrue(responseResult.isSuccess());
    }

}
