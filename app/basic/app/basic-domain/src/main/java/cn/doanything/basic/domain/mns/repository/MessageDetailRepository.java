package cn.doanything.basic.domain.mns.repository;

import cn.doanything.basic.domain.mns.MessageDetail;

import java.util.List;

/**
 * @author wxj
 * 2024/1/7
 */
public interface MessageDetailRepository {

    MessageDetail load(String id);

    void reStore(MessageDetail messageDetail);

    List<MessageDetail> findBySceneCodeAndBizId(String sceneCode, String bizId);
}
