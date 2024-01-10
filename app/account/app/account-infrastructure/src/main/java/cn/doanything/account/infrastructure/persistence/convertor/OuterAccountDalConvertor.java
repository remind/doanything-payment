package cn.doanything.account.infrastructure.persistence.convertor;

import cn.doanything.account.domain.OuterAccount;
import cn.doanything.account.infrastructure.convertor.EnumsConvertor;
import cn.doanything.account.infrastructure.persistence.dataobject.OuterAccountDO;
import cn.doanything.account.infrastructure.persistence.dataobject.OuterSubAccountDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * @author wxj
 * 2023/12/18
 */
@Mapper(componentModel = "spring", uses = {OuterSubAccountDalConvertor.class, EnumsConvertor.class})
public interface OuterAccountDalConvertor {

    @Mapping(target = "outerSubAccounts", source = "outerSubAccountDOs")
    OuterAccount toEntity(OuterAccountDO outerAccountDO, List<OuterSubAccountDO> outerSubAccountDOs);

    OuterAccountDO toOuterAccountDo(OuterAccount outerAccount);

}
