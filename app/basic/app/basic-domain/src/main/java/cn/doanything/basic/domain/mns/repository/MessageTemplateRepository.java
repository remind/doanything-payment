package cn.doanything.basic.domain.mns.repository;

import cn.doanything.basic.domain.mns.MessageTemplate;
import cn.doanything.basic.types.mns.Protocol;

/**
 * @author wxj
 * 2024/1/7
 */
public interface MessageTemplateRepository {

    /**
     * 根据场景码和协议获取消息模板
     * @param sceneCode
     * @param protocol
     * @return
     */
    MessageTemplate findBySceneCodeAndProtocol(String sceneCode, Protocol protocol);
}
