package cn.doanything.basic.infrastructure.persistence.mns.repository;

import cn.doanything.basic.domain.mns.ChannelRequest;
import cn.doanything.basic.domain.mns.repository.ChannelRequestRepository;
import cn.doanything.basic.infrastructure.persistence.mns.convertor.ChannelRequestDalConvertor;
import cn.doanything.basic.infrastructure.persistence.mns.dataobject.ChannelRequestDO;
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
    public ChannelRequest load(Long id) {
        return dalConvertor.toEntity(mapper.selectById(id));
    }

    @Override
    public ChannelRequest lock(Long id) {
        return dalConvertor.toEntity(mapper.lockById(id));
    }

    @Override
    public void store(ChannelRequest channelRequest) {
        ChannelRequestDO channelRequestDO = dalConvertor.toDo(channelRequest);
        mapper.insert(channelRequestDO);
        channelRequest.setId(channelRequestDO.getId());
    }

    @Override
    public void reStore(ChannelRequest channelRequest) {
        mapper.updateById(dalConvertor.toDo(channelRequest));
    }

}
