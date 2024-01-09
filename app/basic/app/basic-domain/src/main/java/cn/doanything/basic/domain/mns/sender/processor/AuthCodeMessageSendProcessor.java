package cn.doanything.basic.domain.mns.sender.processor;

import cn.doanything.basic.domain.mns.MessageDetail;
import cn.doanything.basic.domain.mns.channel.NotifyChannelAdapter;
import cn.doanything.basic.domain.mns.repository.MessageDetailRepository;
import cn.doanything.basic.domain.mns.sender.MessageSendAdapter;
import cn.doanything.basic.domain.mns.sender.MessageSendProcessor;
import cn.doanything.basic.mns.MessageStatus;
import cn.doanything.basic.mns.content.AuthCode;
import cn.doanything.commons.enums.EnableEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @author wxj
 * 2024/1/8
 */
@Component(MessageSendAdapter.PROCESSOR_BEAN_PREFIX + "2")
public class AuthCodeMessageSendProcessor implements MessageSendProcessor {

    @Autowired
    private MessageDetailRepository messageDetailRepository;

    @Autowired
    private NotifyChannelAdapter notifyChannelAdapter;

    @Override
    public void send(MessageDetail messageDetail) {
        invalid(messageDetail.getSceneCode(), messageDetail.getBatchId(), messageDetail.getRecipient());
        messageDetailRepository.store(messageDetail);
        notifyChannelAdapter.process(messageDetail);
    }

    private void invalid(String sceneCode, String batchId, String mobile) {
        List<MessageDetail> messageDetails = messageDetailRepository.findBySceneCodeAndBizId(sceneCode, batchId, mobile);
        for (MessageDetail messageDetail : messageDetails) {
            AuthCode authCode = (AuthCode) messageDetail.getContent();
            if (messageDetail.getStatus().equals(MessageStatus.SUCCESS) && authCode.getAuthStatus().equals(EnableEnum.ENABLE)) {
                authCode.setAuthStatus(EnableEnum.DISABLE);
                if (authCode.getExpireTime().compareTo(new Date()) > 0) {
                    authCode.setInvalidReason("重新发送");
                } else {
                    authCode.setInvalidReason("超时");
                }
                messageDetailRepository.reStore(messageDetail);
            }
        }
    }
}
