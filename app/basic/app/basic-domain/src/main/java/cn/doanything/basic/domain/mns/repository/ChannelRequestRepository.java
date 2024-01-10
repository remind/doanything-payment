package cn.doanything.basic.domain.mns.repository;

import cn.doanything.basic.domain.mns.ChannelRequest;

/**
 * @author wxj
 * 2024/1/7
 */
public interface ChannelRequestRepository {

    ChannelRequest load(Long id);

    ChannelRequest lock(Long id);

    void store(ChannelRequest channelRequest);

    void reStore(ChannelRequest channelRequest);
}
