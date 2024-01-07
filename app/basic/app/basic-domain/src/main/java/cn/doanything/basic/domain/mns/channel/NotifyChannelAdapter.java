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

    @Autowired
    private NotifyChannelDomainService notifyChannelDomainService;

    @Autowired
    private ChannelRecordRepository channelRecordRepository;

    private final Map<String, NotifyChannelProcessor> processorMap = new HashMap<>();

    public void process(MessageDetail messageDetail) {
        NotifyChannel notifyChannel = notifyChannelDomainService.getDefault(messageDetail.getProtocol());
        NotifyResult notifyResult = processorMap.get(notifyChannel.getCode()).process(messageDetail);
        ChannelRecord channelRecord = new ChannelRecord();
        channelRecord.setChannelCode(notifyChannel.getCode());
        channelRecord.setMessageId(messageDetail.getId());
        channelRecord.setStatus(notifyResult.getStatus());
        channelRecord.setResponseId(notifyResult.getResponseId());
        channelRecord.setResponseText(notifyResult.getResponseText());
        channelRecordRepository.store(channelRecord);
    }

    /**
     * 注册处理器
     *
     * @param notifyChannelCode
     * @param processor
     */
    public void register(String notifyChannelCode, NotifyChannelProcessor processor) {
        processorMap.put(notifyChannelCode, processor);
    }
}
