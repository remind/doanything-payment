package cn.doanything.account.infrastructure.persistence.convertor;

import cn.doanything.account.domain.OuterAccount;
import cn.doanything.account.infrastructure.convertor.EnumsConvertor;
import cn.doanything.account.infrastructure.persistence.dataobject.OuterAccountDO;
import cn.doanything.commons.convertor.ReadWriteConvertor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author wxj
 * 2023/12/18
 */
@Mapper(componentModel = "spring", uses = {EnumsConvertor.class})
public interface OuterAccountDalConvertor extends ReadWriteConvertor<OuterAccount, OuterAccountDO> {

    OuterAccountDalConvertor INSTANCE = Mappers.getMapper(OuterAccountDalConvertor.class);

}
