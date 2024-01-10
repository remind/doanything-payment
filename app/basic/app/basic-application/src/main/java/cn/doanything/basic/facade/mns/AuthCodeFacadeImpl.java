package cn.doanything.basic.facade.mns;

import cn.doanything.basic.application.mns.MessageNotifyService;
import cn.doanything.basic.application.mns.builder.MessageDetailBuilder;
import cn.doanything.basic.domain.mns.MessageDetail;
import cn.doanything.basic.domain.mns.repository.MessageDetailRepository;
import cn.doanything.basic.facade.mns.dto.AuthCodeMessageRequest;
import cn.doanything.basic.facade.mns.dto.AuthCodeValidateRequest;
import cn.doanything.basic.mns.MessageStatus;
import cn.doanything.basic.mns.content.AuthCode;
import cn.doanything.commons.enums.EnableEnum;
import cn.doanything.commons.lang.utils.AssertUtil;
import cn.doanything.commons.response.ResponseResult;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

/**
 * @author wxj
 * 2024/1/7
 */
@DubboService
public class AuthCodeFacadeImpl implements AuthCodeFacade {

    @Autowired
    private MessageDetailBuilder messageDetailBuilder;

    @Autowired
    private MessageDetailRepository messageDetailRepository;

    @Autowired
    private MessageNotifyService messageNotifyService;

    @Override
    public ResponseResult<String> sendAuthCode(AuthCodeMessageRequest request) {
        MessageDetail messageDetail = messageDetailBuilder.build(request);
        messageNotifyService.process(messageDetail, null);
        return ResponseResult.success();
    }

    @Override
    public ResponseResult<String> validate(AuthCodeValidateRequest request) {
        MessageDetail messageDetail = messageDetailRepository.loadByRequestId(request.getSendRequestId());
        AssertUtil.isNotNull(messageDetail, "验证码错误");
        AssertUtil.isTrue(messageDetail.getRecipient().equals(request.getRecipient()), "验证码错误");
        AssertUtil.isTrue(messageDetail.getSceneCode().equals(request.getSceneCode()), "验证码错误");

        AuthCode authCode = (AuthCode) messageDetail.getContent();
        ResponseResult<String> result = ResponseResult.fail("验证码错误");
        if (messageDetail.getStatus() == MessageStatus.SUCCESS
                && authCode.getAuthStatus() == EnableEnum.ENABLE) {
            if (authCode.getVerifiedCount() >= authCode.getVerifiableCount()) {
                authCode.invalid("验证次数超限");
                result = ResponseResult.fail("验证次数超限，请重新获取");

            } else if (authCode.getExpireTime().isBefore(LocalDateTime.now())) {
                authCode.invalid("验证码已过期");
                result = ResponseResult.fail("验证码已过期");
            } else {
                if (!authCode.getAuthCode().equals(request.getAuthCode())) {
                    authCode.incVerifiedCount();
                    result = ResponseResult.fail("验证码错误，剩余可验证" + (authCode.getVerifiableCount() - authCode.getVerifiedCount()) + "次");
                } else {
                    authCode.invalid("验证通过");
                    result = ResponseResult.success();
                }
            }
            messageDetailRepository.reStore(messageDetail);
        }
        return result;
    }
}
