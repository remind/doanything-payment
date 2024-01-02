package cn.doanything.member.infrastructure.convertor;

import cn.doanything.commons.lang.utils.EnumUtil;
import cn.doanything.member.types.MemberStatus;
import cn.doanything.member.types.PasswordStatus;
import cn.doanything.member.types.PasswordType;
import cn.doanything.member.types.PasswordUseType;
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

    default PasswordUseType toPasswordUseType(String code) {
        return EnumUtil.getByCode(PasswordUseType.class, code);
    }

    default String toPasswordUseType(PasswordUseType enumObject) {
        return enumObject == null ? null : enumObject.getCode();
    }

    default PasswordType toPasswordType(String code) {
        return EnumUtil.getByCode(PasswordType.class, code);
    }

    default String toPasswordType(PasswordType enumObject) {
        return enumObject == null ? null : enumObject.getCode();
    }

    default PasswordStatus toPasswordStatus(String code) {
        return EnumUtil.getByCode(PasswordStatus.class, code);
    }

    default String toPasswordStatus(PasswordStatus enumObject) {
        return enumObject == null ? null : enumObject.getCode();
    }
}
