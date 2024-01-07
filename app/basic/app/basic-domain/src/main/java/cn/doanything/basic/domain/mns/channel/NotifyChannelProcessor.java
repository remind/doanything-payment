package cn.doanything.basic.domain.mns.channel;

import cn.doanything.basic.domain.mns.MessageDetail;

/**
 * 通知渠道处理器
 * @author wxj
 * 2024/1/7
 */
public interface NotifyChannelProcessor {

    NotifyResult process(MessageDetail messageDetail);
}
