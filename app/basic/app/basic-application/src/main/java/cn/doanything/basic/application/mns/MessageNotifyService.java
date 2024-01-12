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
import cn.doanything.basic.types.mns.MessageStatus;
import cn.doanything.basic.types.mns.NotifyType;
import cn.doanything.commons.enums.ResultStatusEnum;
import cn.doanything.commons.lang.utils.AssertUtil;
import cn.doanything.framework.scheduler.SchedulerTaskRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Map;
import java.util.concurrent.ExecutorService;

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

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Autowired
    private ExecutorService executorService;

    public void process(MessageDetail messageDetail, Map<String, Object> param) {
        AssertUtil.isNull(messageDetailRepository.loadByRequestId(messageDetail.getRequestId()), "重复请求");
        String processKey = MessageContentProcessor.PROCESSOR_BEAN_PREFIX + messageDetail.getMessageType().getCode();
        messageContentProcessorMap.get(processKey).process(messageDetail, param);
        transactionTemplate.executeWithoutResult(status -> {
            messageDetail.setStatus(MessageStatus.WAIT);
            messageDetailRepository.store(messageDetail);
            if (messageDetail.getNotifyType() == NotifyType.REAL) {
                send(messageDetail);
            } else {
                schedulerTaskRegister.registryTask(BasicConstants.DEFAULT_TASK_TYPE, messageDetail.getMessageId(), messageDetail.getNotifyTime());
            }
        });
    }

    public void send(MessageDetail messageDetail) {
        NotifyChannel notifyChannel = notifyChannelDomainService.getDefault(messageDetail.getProtocol());
        ChannelRequest channelRequest = new ChannelRequest(notifyChannel.getCode(), messageDetail.getMessageId());
        channelRequestRepository.store(channelRequest);
        executorService.submit(() -> {
            NotifyResult notifyResult = notifyChannelProcessorMap.get(NotifyChannelProcessor.PROCESSOR_BEAN_PREFIX + notifyChannel.getCode()).process(messageDetail);
            updateSendResult(messageDetail, channelRequest, notifyResult);
        });
    }

    private void updateSendResult(MessageDetail messageDetail, ChannelRequest channelRequest, NotifyResult notifyResult) {
        channelRequest.setStatus(notifyResult.getStatus());
        channelRequest.setResponseId(notifyResult.getResponseId());
        channelRequest.setResponseText(notifyResult.getResponseText());
        channelRequestRepository.reStore(channelRequest);
        if (notifyResult.getStatus() == ResultStatusEnum.SUCCESS) {
            messageDetail.setStatus(MessageStatus.SUCCESS);
            messageDetailRepository.reStore(messageDetail);
        } else if (notifyResult.getStatus() == ResultStatusEnum.FAIL) {
            messageDetail.setStatus(MessageStatus.FAIL);
            messageDetailRepository.reStore(messageDetail);
        }
    }
}
