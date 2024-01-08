package cn.doanything.basic.domain.mns.service;

import cn.doanything.basic.mns.message.AuthCode;
import cn.doanything.basic.domain.mns.MessageDetail;
import cn.doanything.basic.domain.mns.repository.MessageDetailRepository;
import cn.doanything.basic.mns.MessageStatus;
import cn.doanything.commons.enums.EnableEnum;
import cn.hutool.core.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author wxj
 * 2024/1/7
 */
@Service
public class AuthCodeDomainService {

    @Autowired
    private MessageDetailRepository messageDetailRepository;

    public void invalid(String sceneCode, String bizId, String mobile) {
        List<MessageDetail<AuthCode>> messageDetails = messageDetailRepository.findBySceneCodeAndBizId(sceneCode, bizId, mobile);
        for (MessageDetail<AuthCode> messageDetail : messageDetails) {
            AuthCode authCode = messageDetail.getContent();
            if (messageDetail.getStatus().equals(MessageStatus.SUCCESS) && authCode.getAuthStatus().equals(EnableEnum.ENABLE)) {
                authCode.setAuthStatus(EnableEnum.DISABLE);
                if (authCode.getExpireTime().compareTo(new Date()) > 0) {
                    authCode.setInvalidReason("重新发生");
                } else {
                    authCode.setInvalidReason("超时");
                }
                messageDetailRepository.reStore(messageDetail);
            }
        }
    }

    public void send(AuthCode authCode) {
        authCode.setAuthCode(RandomUtil.randomNumbers(4));

    }

}
