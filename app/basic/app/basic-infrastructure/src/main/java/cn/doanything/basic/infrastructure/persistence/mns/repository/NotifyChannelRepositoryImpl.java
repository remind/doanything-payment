package cn.doanything.basic.infrastructure.persistence.mns.repository;

import cn.doanything.basic.domain.mns.NotifyChannel;
import cn.doanything.basic.domain.mns.repository.NotifyChannelRepository;
import cn.doanything.basic.infrastructure.persistence.mns.convertor.NotifyChannelDalConvertor;
import cn.doanything.basic.infrastructure.persistence.mns.mapper.NotifyChannelMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wxj
 * 2024/1/8
 */
@Repository
public class NotifyChannelRepositoryImpl implements NotifyChannelRepository {

    @Autowired
    private NotifyChannelDalConvertor dalConvertor;

    @Autowired
    private NotifyChannelMapper mapper;

    @Override
    public List<NotifyChannel> findAll() {
        return dalConvertor.toEntity(mapper.selectList(new LambdaQueryWrapper<>()));
    }
}
