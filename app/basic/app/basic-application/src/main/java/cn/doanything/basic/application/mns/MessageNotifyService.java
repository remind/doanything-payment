package cn.doanything.basic.application.mns;

import cn.doanything.basic.application.mns.processor.MessageContentProcessor;
import cn.doanything.basic.domain.BasicConstants;
import cn.doanything.basic.domain.mns.ChannelRequest;
import cn.doanything.basic.domain.mns.MessageDetail;
import cn.doanything.basic.domain.mns.NotifyChannel;
import cn.doanything.basic.domain.mns.channel.NotifyChannelProcessor;
import cn.doanything.basic.domain.mns.channel.NotifyResult;
import cn.doanything.basic.domain.mns.repository.ChannelRequestRepository;
import cn.doanything.basic.domain.mns.repository.MessageDetailRepository;
import cn.doanything.basic.domain.mns.service.NotifyChannelDomainService;
import cn.doanything.basic.mns.MessageStatus;
import cn.doanything.basic.mns.NotifyType;
import cn.doanything.commons.lang.utils.AssertUtil;
import cn.doanything.framework.scheduler.SchedulerTaskRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author wxj
 * 2024/1/9
 */
@Component
public class MessageNotifyService {

    @Autowired
    private NotifyChannelDomainService notifyChannelDomainService;

    @Autowired
    private MessageDetailRepository messageDetailRepository;

    @Autowired
    private ChannelRequestRepository channelRequestRepository;

    @Autowired
    private SchedulerTaskRegister schedulerTaskRegister;

    @Autowired
    private Map<String, MessageContentProcessor> messageContentProcessorMap;
    @Autowired
    private Map<String, NotifyChannelProcessor> notifyChannelProcessorMap;

    public void process(MessageDetail messageDetail, Map<String, Object> param) {
        AssertUtil.isNull(messageDetailRepository.loadByRequestId(messageDetail.getRequestId()), "重复请求");
        String processKey = MessageContentProcessor.PROCESSOR_BEAN_PREFIX + messageDetail.getMessageType().getCode();
        messageContentProcessorMap.get(processKey).process(messageDetail, param);
        messageDetail.setStatus(MessageStatus.SUCCESS);
        messageDetailRepository.store(messageDetail);
        if (messageDetail.getNotifyType() == NotifyType.REAL) {
            send(messageDetail);
        } else {
            schedulerTaskRegister.registryTask(BasicConstants.DEFAULT_TASK_TYPE, messageDetail.getMessageId(), messageDetail.getNotifyTime());
        }
    }

    public void send(MessageDetail messageDetail) {
        NotifyChannel notifyChannel = notifyChannelDomainService.getDefault(messageDetail.getProtocol());
        NotifyResult notifyResult = notifyChannelProcessorMap.get(NotifyChannelProcessor.PROCESSOR_BEAN_PREFIX + notifyChannel.getCode()).process(messageDetail);
        ChannelRequest channelRequest = new ChannelRequest();
        channelRequest.setChannelCode(notifyChannel.getCode());
        channelRequest.setMessageId(messageDetail.getMessageId());
        channelRequest.setStatus(notifyResult.getStatus());
        channelRequest.setResponseId(notifyResult.getResponseId());
        channelRequest.setResponseText(notifyResult.getResponseText());
        channelRequestRepository.store(channelRequest);
    }
}
