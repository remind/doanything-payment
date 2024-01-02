package cn.doanything.member.infrastructure.persistence.convertor;

import cn.doanything.commons.convertor.ReadWriteConvertor;
import cn.doanything.member.domain.personal.PersonalPassword;
import cn.doanything.member.infrastructure.convertor.EnumsConvertor;
import cn.doanything.member.infrastructure.persistence.dataobject.PersonalPasswordDO;
import org.mapstruct.Mapper;

/**
 * @author wxj
 * 2024/1/2
 */
@Mapper(componentModel = "spring", uses = {EnumsConvertor.class})
public interface PersonalPasswordDalConvertor extends ReadWriteConvertor<PersonalPassword, PersonalPasswordDO> {
}
