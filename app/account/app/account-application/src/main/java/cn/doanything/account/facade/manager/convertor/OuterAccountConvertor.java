package cn.doanything.account.facade.manager.convertor;

import cn.doanything.account.domain.OuterAccount;
import cn.doanything.account.domain.OuterAccountType;
import cn.doanything.account.facade.manager.dto.OuterAccountAddRequest;
import org.mapstruct.Mapper;

/**
 * @author wxj
 * 2023/12/22
 */
@Mapper(componentModel = "spring")
public interface OuterAccountConvertor {
    OuterAccount toOuterAccount(OuterAccountAddRequest request, OuterAccountType outerAccountType);
}
