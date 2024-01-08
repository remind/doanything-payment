package cn.doanything.basic.infrastructure.persistence.mns.convertor;

import cn.doanything.basic.domain.mns.MessageTemplate;
import cn.doanything.basic.infrastructure.persistence.mns.dataobject.MessageTemplateDO;
import cn.doanything.commons.convertor.ReadWriteConvertor;
import org.mapstruct.Mapper;

/**
 * @author wxj
 * 2024/1/8
 */
@Mapper(componentModel = "spring",uses = {EnumsConvertor.class})
public interface MessageTemplateDalConvertor extends ReadWriteConvertor<MessageTemplate, MessageTemplateDO> {
}
