package cn.doanything.basic.infrastructure.persistence.mns.repository;

import cn.doanything.basic.domain.mns.ChannelRequest;
import cn.doanything.basic.domain.mns.repository.ChannelRequestRepository;
import cn.doanything.basic.infrastructure.persistence.mns.convertor.ChannelRequestDalConvertor;
import cn.doanything.basic.infrastructure.persistence.mns.mapper.ChannelRequestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author wxj
 * 2024/1/8
 */
@Repository
public class ChannelRequestRepositoryImpl implements ChannelRequestRepository {

    @Autowired
    private ChannelRequestDalConvertor dalConvertor;

    @Autowired
    private ChannelRequestMapper mapper;
    @Override
    public void store(ChannelRequest channelRequest) {
        mapper.insert(dalConvertor.toDo(channelRequest));
    }
}
