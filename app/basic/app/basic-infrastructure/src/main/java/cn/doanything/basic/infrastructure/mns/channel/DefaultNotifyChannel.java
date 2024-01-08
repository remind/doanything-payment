package cn.doanything.basic.infrastructure.mns.channel;

import cn.doanything.basic.domain.mns.MessageDetail;
import cn.doanything.basic.domain.mns.channel.NotifyChannelAdapter;
import cn.doanything.basic.domain.mns.channel.NotifyChannelProcessor;
import cn.doanything.basic.domain.mns.channel.NotifyResult;
import cn.doanything.commons.enums.ResultStatusEnum;
import org.springframework.stereotype.Component;

/**
 * 默认渠道
 * @author wxj
 * 2024/1/7
 */
@Component(NotifyChannelAdapter.PROCESSOR_BEAN_PREFIX + "default")
public class DefaultNotifyChannel implements NotifyChannelProcessor {

    @Override
    public NotifyResult process(MessageDetail messageDetail) {
        NotifyResult result = new NotifyResult();
        result.setResponseId("123");
        result.setResponseText("456");
        result.setStatus(ResultStatusEnum.SUCCESS);
        return result;
    }
}
