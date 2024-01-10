package cn.doanything.basic.application.mns.builder;

import cn.doanything.basic.domain.mns.MessageDetail;
import cn.doanything.basic.facade.mns.dto.AuthCodeMessageRequest;
import cn.doanything.basic.facade.mns.dto.NotifyMessageRequest;
import cn.doanything.basic.mns.MessageType;
import cn.doanything.basic.mns.NotifyType;
import cn.doanything.basic.mns.Protocol;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 消息构造器
 * @author wxj
 * 2024/1/7
 */
@Component
public class MessageDetailBuilder {

    public MessageDetail build(AuthCodeMessageRequest request) {
        MessageDetail authCodeMessage = new MessageDetail();
        authCodeMessage.setRequestId(request.getRequestId());
        authCodeMessage.setSceneCode(request.getSceneCode());
        authCodeMessage.setBatchId(request.getBatchId());
        authCodeMessage.setRecipient(request.getRecipient());
        authCodeMessage.setMemberId(request.getMemberId());
        authCodeMessage.setMessageType(MessageType.AUTH_CODE);
        authCodeMessage.setNotifyTime(LocalDateTime.now());
        authCodeMessage.setNotifyType(NotifyType.REAL);
        authCodeMessage.setProtocol(Protocol.SNS);
        return authCodeMessage;
    }

    public MessageDetail build(NotifyMessageRequest request) {
        MessageDetail messageDetail = new MessageDetail();
        messageDetail.setRequestId(request.getRequestId());
        messageDetail.setSceneCode(request.getSceneCode());
        messageDetail.setBatchId(request.getRequestId());
        messageDetail.setRecipient(request.getRecipient());
        messageDetail.setMemberId(request.getMemberId());
        messageDetail.setMessageType(MessageType.NORMAL_TEXT);
        messageDetail.setNotifyTime(request.getNotifyTime());
        messageDetail.setNotifyType(request.isReal() ? NotifyType.REAL : NotifyType.DELAY);
        messageDetail.setProtocol(request.getProtocol());
        return messageDetail;
    }
}
