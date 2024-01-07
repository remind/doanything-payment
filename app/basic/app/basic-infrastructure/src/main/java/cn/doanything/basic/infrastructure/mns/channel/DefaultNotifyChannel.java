package cn.doanything.basic.infrastructure.mns.channel;

import cn.doanything.basic.domain.mns.MessageDetail;
import cn.doanything.basic.domain.mns.channel.NotifyChannelProcessor;
import cn.doanything.basic.domain.mns.channel.NotifyResult;
import cn.doanything.basic.mns.ChannelSendStatus;

/**
 * 默认渠道
 * @author wxj
 * 2024/1/7
 */
public class DefaultNotifyChannel implements NotifyChannelProcessor {

    @Override
    public NotifyResult process(MessageDetail messageDetail) {
        NotifyResult result = new NotifyResult();
        result.setResponseId("123");
        result.setResponseText("456");
        result.setStatus(ChannelSendStatus.SUCCESS);
        return result;
    }
}
