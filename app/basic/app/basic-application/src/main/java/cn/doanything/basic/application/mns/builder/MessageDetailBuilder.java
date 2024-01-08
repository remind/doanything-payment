package cn.doanything.basic.application.mns.builder;

import cn.doanything.basic.domain.BasicConstants;
import cn.doanything.basic.domain.mns.MessageDetail;
import cn.doanything.basic.mns.content.AuthCode;
import cn.doanything.basic.domain.mns.service.MessageTemplateDomainService;
import cn.doanything.basic.facade.mns.dto.AuthCodeRequest;
import cn.doanything.basic.mns.MessageStatus;
import cn.doanything.basic.mns.MessageType;
import cn.doanything.basic.mns.NotifyType;
import cn.doanything.basic.mns.Protocol;
import cn.doanything.commons.enums.EnableEnum;
import cn.hutool.core.util.RandomUtil;
import org.apache.commons.lang3.time.DateUtils;
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

    public MessageDetail buildAuthCodeMessage(AuthCodeRequest request) {
        MessageDetail authCodeMessage = new MessageDetail();

        authCodeMessage.setRequestId(request.getRequestId());
        authCodeMessage.setSceneCode(request.getSceneCode());
        authCodeMessage.setBatchId(request.getBizId());
        authCodeMessage.setRecipient(request.getRecipient());
        authCodeMessage.setMemberId(request.getMemberId());
        authCodeMessage.setMessageType(MessageType.AUTH_CODE);
        authCodeMessage.setNotifyTime(new Date());
        authCodeMessage.setNotifyType(NotifyType.REAL);
        authCodeMessage.setProtocol(Protocol.SNS);
        authCodeMessage.setStatus(MessageStatus.WAIT);

        AuthCode authCode = new AuthCode();
        authCode.setAuthCode(RandomUtil.randomNumbers(BasicConstants.MNS_AUTH_CODE_LENGTH));
        authCode.setAuthStatus(EnableEnum.ENABLE);
        authCode.setValidMinute(BasicConstants.MNS_AUTH_CODE_VALID_MINUTE);
        authCode.setVerifiableCount(BasicConstants.MNS_AUTH_CODE_VERIFIABLE_COUNT);
        authCode.setExpireTime(DateUtils.addMinutes(new Date(), BasicConstants.MNS_AUTH_CODE_VALID_MINUTE));

        authCodeMessage.setContent(authCode);

        messageTemplateDomainService.render(authCodeMessage);
        return authCodeMessage;
    }
}
