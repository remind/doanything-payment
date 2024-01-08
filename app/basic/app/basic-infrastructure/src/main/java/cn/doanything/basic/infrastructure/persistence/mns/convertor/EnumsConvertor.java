package cn.doanything.basic.infrastructure.persistence.mns.convertor;

import cn.doanything.basic.mns.Protocol;
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

    default String toProtocol(Protocol enumObject) {
        return enumObject == null ? null : enumObject.getCode();
    }
}
