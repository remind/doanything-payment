package cn.doanything.basic.domain.mns.sender;

import cn.doanything.basic.domain.mns.MessageDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author wxj
 * 2024/1/8
 */
@Component
public class MessageSendAdapter {

    public static final String PROCESSOR_BEAN_PREFIX = "MessageSendProcessor_prefix_";

    @Autowired
    private Map<String, MessageSendProcessor> processorMap;

    public void process(MessageDetail messageDetail) {
        processorMap.get(MessageSendAdapter.PROCESSOR_BEAN_PREFIX + messageDetail.getMessageType().getCode()).send(messageDetail);
    }

}
