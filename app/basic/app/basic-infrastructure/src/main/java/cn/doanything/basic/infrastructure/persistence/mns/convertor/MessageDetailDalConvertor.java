package cn.doanything.basic.infrastructure.persistence.mns.convertor;

import cn.doanything.basic.domain.mns.MessageDetail;
import cn.doanything.basic.infrastructure.persistence.mns.dataobject.MessageDetailDO;
import cn.doanything.commons.convertor.ReadWriteConvertor;
import org.mapstruct.Mapper;

/**
 * @author wxj
 * 2024/1/8
 */
@Mapper(componentModel = "spring",uses = {EnumsConvertor.class})
public interface MessageDetailDalConvertor extends ReadWriteConvertor<MessageDetail, MessageDetailDO> {
}
