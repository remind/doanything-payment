package cn.doanything.basic.domain.mns.service;

import cn.doanything.basic.domain.mns.MessageAuthCode;
import cn.doanything.basic.domain.mns.MessageDetail;
import cn.doanything.basic.domain.mns.repository.MessageDetailRepository;
import cn.doanything.basic.mns.MessageStatus;
import cn.doanything.commons.enums.EnableEnum;
import cn.doanything.commons.lang.utils.AssertUtil;
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

    public void invalid(String sceneCode, String bizId) {
        List<MessageDetail> messageDetails = messageDetailRepository.findBySceneCodeAndBizId(sceneCode, bizId);
        for (MessageDetail messageDetail : messageDetails) {
            MessageAuthCode authCode = (MessageAuthCode) messageDetail;
            if (authCode.getStatus().equals(MessageStatus.SUCCESS) && authCode.getAuthStatus().equals(EnableEnum.ENABLE)) {
                authCode.setAuthStatus(EnableEnum.DISABLE);
                if (authCode.getExpireTime().compareTo(new Date()) > 0) {
                    authCode.setInvalidReason("重新发生");
                } else {
                    authCode.setInvalidReason("超时");
                }
                messageDetailRepository.reStore(authCode);
            }
        }
    }

    public void send(MessageAuthCode messageAuthCode) {
        messageAuthCode.setAuthCode(RandomUtil.randomNumbers(4));

    }

}
