package cn.doanything.basic.infrastructure.persistence.mns.convertor;

import cn.doanything.basic.domain.mns.NotifyChannel;
import cn.doanything.basic.infrastructure.persistence.mns.dataobject.NotifyChannelDO;
import cn.doanything.basic.mns.Protocol;
import cn.doanything.commons.convertor.ReadWriteConvertor;
import cn.doanything.commons.lang.utils.EnumUtil;
import org.mapstruct.Mapper;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wxj
 * 2024/1/8
 */
@Mapper(componentModel = "spring", uses = {EnumsConvertor.class})
public interface NotifyChannelDalConvertor extends ReadWriteConvertor<NotifyChannel, NotifyChannelDO> {

    default List<Protocol> toProtocols(String protocol) {
        return Arrays.stream(protocol.split(",")).map(p -> EnumUtil.getByCode(Protocol.class, p)).toList();
    }

    default String toProtocols(List<Protocol> protocols) {
        return protocols.stream().map(Protocol::getCode).collect(Collectors.joining());
    }
}
