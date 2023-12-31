package cn.doanything.member.infrastructure.convertor;

import cn.doanything.commons.lang.utils.EnumUtil;
import cn.doanything.member.types.MemberStatus;
import org.mapstruct.Mapper;

/**
 * @author wxj
 * 2023/12/31
 */
@Mapper(componentModel = "spring")
public interface EnumsConvertor {

    default MemberStatus toMemberStatus(String code) {
        return EnumUtil.getByCode(MemberStatus.class, code);
    }

    default String toMemberStatus(MemberStatus enumObject) {
        return enumObject == null ? null : enumObject.getCode();
    }
}
