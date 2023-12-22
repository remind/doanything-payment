package cn.doanything.account.infrastructure.persistence.convertor;

import cn.doanything.account.domain.OuterAccount;
import cn.doanything.account.infrastructure.convertor.EnumsConvertor;
import cn.doanything.account.infrastructure.persistence.dataobject.OuterAccountDO;
import cn.doanything.account.infrastructure.persistence.dataobject.OuterSubAccountDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author wxj
 * 2023/12/18
 */
@Mapper(componentModel = "spring", uses = {OuterSubAccountDalConvertor.class, EnumsConvertor.class})
public interface OuterAccountDalConvertor {

    OuterAccountDalConvertor INSTANCE = Mappers.getMapper(OuterAccountDalConvertor.class);

    @Mapping(target = "outerSubAccounts", source = "outerSubAccountDOs")
    OuterAccount toEntity(OuterAccountDO outerAccountDO, List<OuterSubAccountDO> outerSubAccountDOs);

    OuterAccountDO toOuterAccountDo(OuterAccount outerAccount);

}
