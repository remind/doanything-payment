package cn.doanything.basic.application.mns.processor;

import cn.doanything.basic.application.mns.processor.AbstractMessageContentProcessor;
import cn.doanything.basic.application.mns.processor.MessageContentProcessor;
import cn.doanything.basic.domain.BasicConstants;
import cn.doanything.basic.domain.mns.MessageDetail;
import cn.doanything.basic.domain.mns.repository.MessageDetailRepository;
import cn.doanything.basic.mns.MessageStatus;
import cn.doanything.basic.mns.content.AuthCode;
import cn.doanything.commons.enums.EnableEnum;
import cn.hutool.core.util.RandomUtil;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wxj
 * 2024/1/9
 */
@Component(MessageContentProcessor.PROCESSOR_BEAN_PREFIX + "2")
public class AuthCodeMessageContentProcessor extends AbstractMessageContentProcessor implements MessageContentProcessor {

    @Autowired
    private MessageDetailRepository messageDetailRepository;

    @Override
    public void process(MessageDetail messageDetail, Map<String, Object> param) {
        AuthCode authCode = new AuthCode();
        authCode.setAuthCode(RandomUtil.randomNumbers(BasicConstants.MNS_AUTH_CODE_LENGTH));
        authCode.setAuthStatus(EnableEnum.ENABLE);
        authCode.setValidMinute(BasicConstants.MNS_AUTH_CODE_VALID_MINUTE);
        authCode.setVerifiableCount(BasicConstants.MNS_AUTH_CODE_VERIFIABLE_COUNT);
        authCode.setVerifiedCount(0);
        authCode.setExpireTime(LocalDateTime.now().plusMinutes(BasicConstants.MNS_AUTH_CODE_VALID_MINUTE));

        param = new HashMap<>();
        param.put("authCode", authCode.getAuthCode());
        param.put("validMinute", authCode.getValidMinute());
        authCode.setMessageText(renderByTemplate(messageDetail.getSceneCode(), messageDetail.getProtocol(), param));

        messageDetail.setContent(authCode);

        invalid(messageDetail.getSceneCode(), messageDetail.getBatchId(), messageDetail.getRecipient());
    }

    private void invalid(String sceneCode, String batchId, String mobile) {
        List<MessageDetail> messageDetails = messageDetailRepository.findBySceneCodeAndBizId(sceneCode, batchId, mobile);
        for (MessageDetail messageDetail : messageDetails) {
            AuthCode authCode = (AuthCode) messageDetail.getContent();
            if (messageDetail.getStatus().equals(MessageStatus.SUCCESS) && authCode.getAuthStatus().equals(EnableEnum.ENABLE)) {
                authCode.setAuthStatus(EnableEnum.DISABLE);
                if (authCode.getExpireTime().isBefore(LocalDateTime.now())) {
                    authCode.setInvalidReason("重新发送");
                } else {
                    authCode.setInvalidReason("超时");
                }
                messageDetailRepository.reStore(messageDetail);
            }
        }
    }
}
