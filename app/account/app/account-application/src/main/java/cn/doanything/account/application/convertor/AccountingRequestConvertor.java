package cn.doanything.account.application.convertor;

import cn.doanything.account.domain.AccountTransaction;
import cn.doanything.account.domain.detail.InnerAccountDetail;
import cn.doanything.account.domain.detail.OuterAccountDetail;
import cn.doanything.account.facade.dto.AccountingDetail;
import cn.doanything.account.facade.dto.AccountingRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author wxj
 * 2023/12/20
 */
@Mapper(componentModel = "spring")
public interface AccountingRequestConvertor {
    AccountingRequestConvertor INSTANCE = Mappers.getMapper(AccountingRequestConvertor.class);

    AccountTransaction toAccountTransaction(AccountingRequest request);

    OuterAccountDetail toOuterAccountDetail(AccountingDetail detail);

    InnerAccountDetail toInnerAccountDetail(AccountingDetail detail);

}
