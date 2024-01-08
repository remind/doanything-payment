package cn.doanything.basic.domain.mns.channel;

import cn.doanything.basic.domain.mns.ChannelRecord;
import cn.doanything.basic.domain.mns.MessageDetail;
import cn.doanything.basic.domain.mns.NotifyChannel;
import cn.doanything.basic.domain.mns.repository.ChannelRecordRepository;
import cn.doanything.basic.domain.mns.service.NotifyChannelDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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
    private ChannelRecordRepository channelRecordRepository;

    @Autowired
    private Map<String, NotifyChannelProcessor> processorMap;

    public void process(MessageDetail messageDetail) {
        NotifyChannel notifyChannel = notifyChannelDomainService.getDefault(messageDetail.getProtocol());
        NotifyResult notifyResult = processorMap.get(PROCESSOR_BEAN_PREFIX + notifyChannel.getCode()).process(messageDetail);
        ChannelRecord channelRecord = new ChannelRecord();
        channelRecord.setChannelCode(notifyChannel.getCode());
        channelRecord.setMessageId(messageDetail.getId());
        channelRecord.setStatus(notifyResult.getStatus());
        channelRecord.setResponseId(notifyResult.getResponseId());
        channelRecord.setResponseText(notifyResult.getResponseText());
        channelRecordRepository.store(channelRecord);
    }

}
