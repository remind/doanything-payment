package cn.doanything.basic.application.mns.builder;

import cn.doanything.basic.domain.BasicConstants;
import cn.doanything.basic.domain.mns.MessageAuthCode;
import cn.doanything.basic.domain.mns.service.MessageTemplateDomainService;
import cn.doanything.basic.facade.mns.dto.AuthCodeRequest;
import cn.doanything.basic.mns.MessageStatus;
import cn.doanything.basic.mns.MessageType;
import cn.doanything.basic.mns.NotifyType;
import cn.doanything.basic.mns.Protocol;
import cn.doanything.commons.enums.EnableEnum;
import cn.hutool.core.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author wxj
 * 2024/1/7
 */
@Component
public class MessageDetailBuilder {

    @Autowired
    private MessageTemplateDomainService messageTemplateDomainService;

    public MessageAuthCode build(AuthCodeRequest request) {
        MessageAuthCode messageAuthCode = new MessageAuthCode();
        messageAuthCode.setRequestId(request.getRequestId());
        messageAuthCode.setSceneCode(request.getSceneCode());
        messageAuthCode.setBizId(request.getBizId());
        messageAuthCode.setRecipient(request.getRecipient());
        messageAuthCode.setMemberId(request.getMemberId());
        messageAuthCode.setAuthCode(RandomUtil.randomNumbers(BasicConstants.MNS_AUTH_CODE_LENGTH));
        messageAuthCode.setAuthStatus(EnableEnum.ENABLE);
        messageAuthCode.setValidMinute(BasicConstants.MNS_AUTH_CODE_VALID_MINUTE);
        messageAuthCode.setVerifiableCount(BasicConstants.MNS_AUTH_CODE_VERIFIABLE_COUNT);
        messageAuthCode.setMessageType(MessageType.AUTH_CODE);
        messageAuthCode.setNotifyTime(new Date());
        messageAuthCode.setNotifyType(NotifyType.REAL);
        messageAuthCode.setProtocol(Protocol.SNS);
        messageAuthCode.setStatus(MessageStatus.WAIT);
        messageTemplateDomainService.render(messageAuthCode);
        return messageAuthCode;
    }
}
