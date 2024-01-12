package cn.doanything.basic.infrastructure.persistence.mns.convertor;

import cn.doanything.basic.types.mns.MessageStatus;
import cn.doanything.basic.types.mns.MessageType;
import cn.doanything.basic.types.mns.NotifyType;
import cn.doanything.basic.types.mns.Protocol;
import cn.doanything.commons.convertor.GlobalTypeConvertor;
import cn.doanything.commons.lang.utils.EnumUtil;
import org.mapstruct.Mapper;

/**
 * @author wxj
 * 2024/1/8
 */
@Mapper(componentModel = "spring")
public interface EnumsConvertor extends GlobalTypeConvertor {

    default Protocol toProtocol(String code) {
        return EnumUtil.getByCode(Protocol.class, code);
    }

    default MessageType getMessageType(String code) {
        return EnumUtil.getByCode(MessageType.class, code);
    }

    default NotifyType getNotifyType(String code) {
        return EnumUtil.getByCode(NotifyType.class, code);
    }

    default MessageStatus toMessageStatus(String code) {
        return EnumUtil.getByCode(MessageStatus.class, code);
    }
}
