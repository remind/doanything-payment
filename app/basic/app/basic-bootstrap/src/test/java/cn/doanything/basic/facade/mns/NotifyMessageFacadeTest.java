package cn.doanything.basic.facade.mns;

import cn.doanything.basic.facade.mns.dto.NotifyMessageRequest;
import cn.doanything.basic.mns.Protocol;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;
import java.util.UUID;

/**
 * @author wxj
 * 2024/1/9
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class NotifyMessageFacadeTest {

    @Autowired
    private NotifyMessageFacade notifyMessageFacade;

    @Test
    public void test(){
        NotifyMessageRequest request = new NotifyMessageRequest();
        request.setMemberId("123");
        request.setProtocol(Protocol.MAIL);
        request.setRecipient("123");
        request.setReal(true);
        request.setNotifyTime(new java.util.Date());
        request.setSubject("测试");
        request.setSceneCode("testMail");
        request.setParam(Map.of("a", "vv"));
        request.setRequestId(getUUID());
        request.setSubject("测试");
        notifyMessageFacade.send(request);
    }

    private String getUUID() {
        String uuid = UUID.randomUUID().toString();
        return uuid.replace("-","").substring(0,32);
    }
}
