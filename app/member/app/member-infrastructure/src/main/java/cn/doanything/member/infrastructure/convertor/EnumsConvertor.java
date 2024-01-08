package cn.doanything.member.infrastructure.convertor;

import cn.doanything.commons.convertor.GlobalTypeConvertor;
import cn.doanything.commons.lang.utils.EnumUtil;
import cn.doanything.member.types.*;
import org.mapstruct.Mapper;

/**
 * @author wxj
 * 2023/12/31
 */
@Mapper(componentModel = "spring")
public interface EnumsConvertor extends GlobalTypeConvertor {

    default MemberStatus toMemberStatus(String code) {
        return EnumUtil.getByCode(MemberStatus.class, code);
    }

    default PasswordUseType toPasswordUseType(String code) {
        return EnumUtil.getByCode(PasswordUseType.class, code);
    }

    default PasswordType toPasswordType(String code) {
        return EnumUtil.getByCode(PasswordType.class, code);
    }

    default PasswordStatus toPasswordStatus(String code) {
        return EnumUtil.getByCode(PasswordStatus.class, code);
    }
    default Gender toGender(String code) {
        return EnumUtil.getByCode(Gender.class, code);
    }

}
