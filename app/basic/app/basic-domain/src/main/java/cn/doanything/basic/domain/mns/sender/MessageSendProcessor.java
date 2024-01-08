package cn.doanything.basic.domain.mns.sender;

import cn.doanything.basic.domain.mns.MessageDetail;

/**
 * @author wxj
 * 2024/1/8
 */
public interface MessageSendProcessor {

    void send(MessageDetail messageDetail);
}
