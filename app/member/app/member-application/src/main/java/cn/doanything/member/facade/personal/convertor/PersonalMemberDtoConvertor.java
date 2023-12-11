package cn.doanything.member.facade.personal.convertor;

import cn.doanything.member.domain.personal.PersonalMember;
import cn.doanything.member.facade.personal.dto.PersonalAddRequest;
import cn.doanything.member.facade.personal.dto.PersonalDetailInfo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author wxj
 * 2023/12/11
 */
@Mapper(componentModel = "spring")
public interface PersonalMemberDtoConvertor {
    PersonalMemberDtoConvertor INSTANCE = Mappers.getMapper(PersonalMemberDtoConvertor.class);

    PersonalMember toPersonalMember(PersonalAddRequest request);

    PersonalDetailInfo toPersonalDetailInfo(PersonalMember personalMember);
}
