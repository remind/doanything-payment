package cn.doanything.basic.infrastructure.persistence.mns.repository;

import cn.doanything.basic.domain.mns.ChannelRequest;
import cn.doanything.basic.domain.mns.repository.ChannelRequestRepository;
import cn.doanything.basic.infrastructure.persistence.mns.dataobject.ChannelRequestDO;
import cn.doanything.framework.dal.repository.AbstractBaseRepository;
import org.springframework.stereotype.Repository;

/**
 * @author wxj
 * 2024/1/8
 */
@Repository
public class ChannelRequestRepositoryImpl extends AbstractBaseRepository<ChannelRequest, ChannelRequestDO> implements ChannelRequestRepository {



}
