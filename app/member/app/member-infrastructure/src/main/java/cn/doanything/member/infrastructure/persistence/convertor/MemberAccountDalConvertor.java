package cn.doanything.member.infrastructure.persistence.convertor;

import cn.doanything.commons.convertor.ReadWriteConvertor;
import cn.doanything.member.infrastructure.convertor.EnumsConvertor;
import cn.doanything.member.infrastructure.persistence.dataobject.MemberAccountDO;
import cn.doanything.member.types.MemberAccount;
import org.mapstruct.Mapper;

/**
 * @author wxj
 * 2024/1/4
 */
@Mapper(componentModel = "spring", uses = {EnumsConvertor.class})
public interface MemberAccountDalConvertor extends ReadWriteConvertor<MemberAccount, MemberAccountDO> {
}
