package cn.doanything.basic.domain.mns.repository;

import cn.doanything.basic.domain.mns.ChannelRecord;

/**
 * @author wxj
 * 2024/1/7
 */
public interface ChannelRecordRepository {

    void store(ChannelRecord channelRecord);
}
