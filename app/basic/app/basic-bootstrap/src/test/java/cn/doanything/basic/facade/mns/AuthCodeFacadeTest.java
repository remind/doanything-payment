package cn.doanything.basic.facade.mns;

import cn.doanything.basic.facade.mns.dto.AuthCodeRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
        AuthCodeRequest request = new AuthCodeRequest();
        request.setRequestId("123");
        request.setMemberId("123");
        request.setBizId("123");
        request.setRecipient("135123");
        request.setSceneCode("findPassword");
        authCodeFacade.sendAuthCode(request);
    }
}
