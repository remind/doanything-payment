package cn.doanything.basic.facade.mns;

import cn.doanything.basic.facade.mns.dto.NotifyMessageRequest;
import cn.doanything.basic.mns.Protocol;
import cn.doanything.framework.BaseTestBootStarter;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * @author wxj
 * 2024/1/9
 */
public class NotifyMessageFacadeTest extends BaseTestBootStarter {

    @Autowired
    private NotifyMessageFacade notifyMessageFacade;

    @Test
    public void test(){
        NotifyMessageRequest request = new NotifyMessageRequest();
        request.setMemberId(randomPersonalMemberId());
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


}
