package cn.doanything.basic.facade.mns;

import cn.doanything.basic.application.mns.builder.MessageDetailBuilder;
import cn.doanything.basic.domain.mns.MessageAuthCode;
import cn.doanything.basic.domain.mns.MessageDetail;
import cn.doanything.basic.domain.mns.channel.NotifyChannelAdapter;
import cn.doanything.basic.domain.mns.repository.MessageDetailRepository;
import cn.doanything.basic.domain.mns.service.AuthCodeDomainService;
import cn.doanything.basic.facade.mns.dto.AuthCodeRequest;
import cn.doanything.commons.lang.utils.AssertUtil;
import cn.doanything.commons.response.ResponseResult;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @author wxj
 * 2024/1/7
 */
@DubboService
public class AuthCodeFacadeImpl implements AuthCodeFacade {

    @Autowired
    private MessageDetailBuilder messageDetailBuilder;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Autowired
    private MessageDetailRepository messageDetailRepository;

    @Autowired
    private AuthCodeDomainService authCodeDomainService;

    @Autowired
    private NotifyChannelAdapter notifyChannelAdapter;

    @Override
    public ResponseResult<String> sendAuthCode(AuthCodeRequest request) {
        transactionTemplate.executeWithoutResult(status -> {
            MessageDetail messageDetail = messageDetailRepository.load(request.getRequestId());
            AssertUtil.isNull(messageDetail, "重复请求");
            MessageAuthCode messageAuthCode = messageDetailBuilder.build(request);
            authCodeDomainService.invalid(messageAuthCode.getSceneCode(), messageAuthCode.getBizId());
            messageDetailRepository.reStore(messageAuthCode);
            notifyChannelAdapter.process(messageAuthCode);
        });
        return ResponseResult.success();
    }

    @Override
    public ResponseResult<String> validate(AuthCodeRequest request) {
        return null;
    }
}
