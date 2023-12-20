package cn.doanything.account.application.convertor;

import cn.doanything.account.domain.AccountTransaction;
import cn.doanything.account.domain.detail.AccountDetail;
import cn.doanything.account.domain.detail.InnerAccountDetail;
import cn.doanything.account.domain.detail.OuterAccountDetail;
import cn.doanything.account.domain.utils.AccountUtil;
import cn.doanything.account.facade.dto.AccountingRequestDetail;
import cn.doanything.account.facade.dto.AccountingRequest;
import cn.doanything.account.types.enums.AccountFamily;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wxj
 * 2023/12/20
 */
@Mapper(componentModel = "spring")
public interface AccountingRequestConvertor {
    AccountingRequestConvertor INSTANCE = Mappers.getMapper(AccountingRequestConvertor.class);

    AccountTransaction toAccountTransaction(AccountingRequest request);

    OuterAccountDetail toOuterAccountDetail(AccountingRequestDetail detail);

    InnerAccountDetail toInnerAccountDetail(AccountingRequestDetail detail);

    default AccountDetail toAccountDetail(AccountingRequestDetail accountingRequestDetail) {
        AccountFamily accountFamily = AccountUtil.getAccountFamily(accountingRequestDetail.getAccountNo());
        if (AccountFamily.INNER == accountFamily) {
            return toInnerAccountDetail(accountingRequestDetail);
        } else if (AccountFamily.OUTER == accountFamily) {
            return toOuterAccountDetail(accountingRequestDetail);
        }
        return null;
    }

    default List<AccountDetail> toAccountDetail(List<AccountingRequestDetail> accountingRequestDetails) {
        List<AccountDetail> accountDetails = new ArrayList<>();
        if (accountingRequestDetails != null) {
            accountingRequestDetails.forEach(accountingRequestDetail -> accountDetails.add(toAccountDetail(accountingRequestDetail)));
        }
        return accountDetails;
    }

}
