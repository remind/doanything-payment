package cn.doanything.basic.application.mns.processor;

import cn.doanything.basic.domain.mns.MessageDetail;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author wxj
 * 2024/1/9
 */
@Component(MessageContentProcessor.PROCESSOR_BEAN_PREFIX + "1")
public class NormalTextMessageContentProcessor extends AbstractMessageContentProcessor implements MessageContentProcessor {
    @Override
    public void process(MessageDetail messageDetail, Map<String, Object> param) {
        String content = renderByTemplate(messageDetail.getSceneCode(), messageDetail.getProtocol(), param);
        messageDetail.setContent(content);
    }
}
