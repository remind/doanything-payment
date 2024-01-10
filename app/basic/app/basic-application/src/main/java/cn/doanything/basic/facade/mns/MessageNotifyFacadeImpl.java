package cn.doanything.basic.facade.mns;

import cn.doanything.basic.application.mns.MessageNotifyService;
import cn.doanything.basic.application.mns.builder.MessageDetailBuilder;
import cn.doanything.basic.domain.mns.MessageDetail;
import cn.doanything.basic.facade.mns.dto.NotifyMessageRequest;
import cn.doanything.commons.response.ResponseResult;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author wxj
 * 2024/1/9
 */
@DubboService
public class MessageNotifyFacadeImpl implements MessageNotifyFacade {

    @Autowired
    private MessageNotifyService messageNotifyService;

    @Autowired
    private MessageDetailBuilder messageDetailBuilder;
    @Override
    public ResponseResult<String> send(NotifyMessageRequest request) {
        MessageDetail messageDetail = messageDetailBuilder.build(request);
        messageNotifyService.process(messageDetail, request.getParam());
        return ResponseResult.success();
    }
}
