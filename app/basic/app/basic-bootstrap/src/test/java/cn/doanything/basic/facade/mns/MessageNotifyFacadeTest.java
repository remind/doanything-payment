package cn.doanything.basic.facade.mns;

import cn.doanything.basic.facade.mns.dto.NotifyMessageRequest;
import cn.doanything.basic.mns.Protocol;
import cn.doanything.framework.BaseTestBootStarter;
import cn.doanything.framework.scheduler.load.SchedulerTaskLoader;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * @author wxj
 * 2024/1/9
 */
public class MessageNotifyFacadeTest extends BaseTestBootStarter {

    @Autowired
    private MessageNotifyFacade messageNotifyFacade;

    @Autowired
    private SchedulerTaskLoader schedulerTaskLoader;
    @Test
    public void test() {
        NotifyMessageRequest request = new NotifyMessageRequest();
        request.setMemberId(randomPersonalMemberId());
        request.setProtocol(Protocol.MAIL);
        request.setRecipient("123");
        request.setReal(true);
        request.setNotifyTime(LocalDateTime.now());
        request.setSubject("测试");
        request.setSceneCode("testMail");
        request.setParam(Map.of("a", "vv"));
        request.setRequestId(getUUID());
        request.setSubject("测试");
        messageNotifyFacade.send(request);
    }

    @Test
    public void testDelayMessage() {
        NotifyMessageRequest request = new NotifyMessageRequest();
        request.setMemberId(randomPersonalMemberId());
        request.setProtocol(Protocol.MAIL);
        request.setRecipient("123");
        request.setReal(false);
        request.setNotifyTime(LocalDateTime.now().plusDays(1));
        request.setSubject("测试");
        request.setSceneCode("testMail");
        request.setParam(Map.of("a", "vv"));
        request.setRequestId(getUUID());
        request.setSubject("测试");
        messageNotifyFacade.send(request);
    }

    @Test
    public void testSendDelayMessage() {
        schedulerTaskLoader.loadAndDistribute();
    }


}
