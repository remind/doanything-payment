package cn.doanything.basic.domain.mns.repository;

import cn.doanything.basic.domain.mns.NotifyChannel;

import java.util.List;

/**
 * @author wxj
 * 2024/1/7
 */
public interface NotifyChannelRepository {

    List<NotifyChannel> findAll();
}
