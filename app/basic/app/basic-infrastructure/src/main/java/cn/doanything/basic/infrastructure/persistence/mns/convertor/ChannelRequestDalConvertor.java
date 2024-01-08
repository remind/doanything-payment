package cn.doanything.basic.infrastructure.persistence.mns.convertor;

import cn.doanything.basic.domain.mns.ChannelRequest;
import cn.doanything.basic.infrastructure.persistence.mns.dataobject.ChannelRequestDO;
import cn.doanything.commons.convertor.ReadWriteConvertor;
import org.mapstruct.Mapper;

/**
 * @author wxj
 * 2024/1/8
 */
@Mapper(componentModel = "spring", uses = {EnumsConvertor.class})
public interface ChannelRequestDalConvertor extends ReadWriteConvertor<ChannelRequest, ChannelRequestDO> {
}
