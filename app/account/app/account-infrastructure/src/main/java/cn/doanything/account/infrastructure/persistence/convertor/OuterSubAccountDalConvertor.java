package cn.doanything.account.infrastructure.persistence.convertor;

import cn.doanything.account.domain.OuterSubAccount;
import cn.doanything.account.infrastructure.persistence.dataobject.OuterSubAccountDO;
import cn.doanything.commons.convertor.ReadWriteConvertor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author wxj
 * 2023/12/18
 */
@Mapper(componentModel = "spring")
public interface OuterSubAccountDalConvertor  {

    OuterSubAccountDalConvertor INSTANCE = Mappers.getMapper(OuterSubAccountDalConvertor.class);

    @Mapping(source = "balance", target = "balance")
    OuterSubAccount toEntity(OuterSubAccountDO outerSubAccountDO);
}

