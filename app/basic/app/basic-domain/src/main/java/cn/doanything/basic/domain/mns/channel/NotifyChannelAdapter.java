package cn.doanything.basic.domain.mns.channel;

import cn.doanything.basic.domain.mns.ChannelRequest;
import cn.doanything.basic.domain.mns.MessageDetail;
import cn.doanything.basic.domain.mns.NotifyChannel;
import cn.doanything.basic.domain.mns.repository.ChannelRequestRepository;
import cn.doanything.basic.domain.mns.service.NotifyChannelDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 通知渠道适配器
 * @author wxj
 * 2024/1/7
 */
@Service
public class NotifyChannelAdapter {

    public static final String PROCESSOR_BEAN_PREFIX = "MessageSendProcessor_prefix_";

    @Autowired
    private NotifyChannelDomainService notifyChannelDomainService;

    @Autowired
    private ChannelRequestRepository channelRequestRepository;

    @Autowired
    private Map<String, NotifyChannelProcessor> processorMap;

    public void process(MessageDetail messageDetail) {
        NotifyChannel notifyChannel = notifyChannelDomainService.getDefault(messageDetail.getProtocol());
        NotifyResult notifyResult = processorMap.get(PROCESSOR_BEAN_PREFIX + notifyChannel.getCode()).process(messageDetail);
        ChannelRequest channelRequest = new ChannelRequest();
        channelRequest.setChannelCode(notifyChannel.getCode());
        channelRequest.setMessageId(messageDetail.getMessageId());
        channelRequest.setStatus(notifyResult.getStatus());
        channelRequest.setResponseId(notifyResult.getResponseId());
        channelRequest.setResponseText(notifyResult.getResponseText());
        channelRequestRepository.store(channelRequest);
    }

}
