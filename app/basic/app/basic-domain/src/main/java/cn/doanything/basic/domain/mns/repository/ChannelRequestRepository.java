package cn.doanything.basic.domain.mns.repository;

import cn.doanything.basic.domain.mns.ChannelRequest;

import java.io.Serializable;

/**
 * @author wxj
 * 2024/1/7
 */
public interface ChannelRequestRepository {

    ChannelRequest load(Serializable id);

    ChannelRequest lock(Serializable id);

    ChannelRequest store(ChannelRequest channelRequest);

    ChannelRequest reStore(ChannelRequest channelRequest);
}
