package cn.doanything.basic.application.mns.processor;

import cn.doanything.basic.domain.mns.MessageDetail;

import java.util.Map;

/**
 * @author wxj
 * 2024/1/9
 */
public interface MessageContentProcessor {

    String PROCESSOR_BEAN_PREFIX = "MessageContentProcessor_prefix_";

    void process(MessageDetail messageDetail, Map<String, Object> param);
}
