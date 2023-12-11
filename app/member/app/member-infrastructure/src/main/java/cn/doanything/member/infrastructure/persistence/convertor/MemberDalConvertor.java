package cn.doanything.member.infrastructure.persistence.convertor;

import cn.doanything.member.infrastructure.persistence.dataobject.MemberDO;
import cn.doanything.member.infrastructure.persistence.dataobject.PersonalMemberDO;
import cn.doanything.member.domain.Member;
import cn.doanything.member.domain.personal.PersonalMember;
import cn.doanything.member.types.Gender;
import cn.doanything.member.types.MemberType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * @author wxj
 * 2023/12/10
 */
@Mapper(componentModel = "spring")
public interface MemberDalConvertor {

    MemberDalConvertor INSTANCE = Mappers.getMapper(MemberDalConvertor.class);

    MemberDO toMemberDo(Member member);

    PersonalMemberDO toPersonalMemberDo(PersonalMember personalMember);

    @Mappings({
        @Mapping(source = "memberDO.memberId",target = "memberId"),
        @Mapping(source = "memberDO.gmtCreate",target = "gmtCreate"),
        @Mapping(source = "memberDO.gmtModified",target = "gmtModified")
    })
    PersonalMember toPersonalMember(MemberDO memberDO, PersonalMemberDO personalMemberDO);

    default Byte toMemberType(MemberType memberType) {
        if (memberType != null) {
            return (byte) memberType.getCode();
        }
        return 0;
    }
    default MemberType toMemberType(Byte memberType) {
        return MemberType.getByCode(Integer.valueOf(memberType));
    }

    default Byte toGender(Gender gender) {
        if (gender != null) {
            return (byte) gender.getCode();
        }
        return 0;
    }
    default Gender toGender(Byte gender) {
        return Gender.getByCode(Integer.valueOf(gender));
    }
}
