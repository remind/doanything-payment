package cn.doanything.account.application.convertor;

import cn.doanything.account.domain.AccountTransaction;
import cn.doanything.account.domain.detail.AccountDetail;
import cn.doanything.account.domain.detail.BufferedDetail;
import cn.doanything.account.domain.detail.InnerAccountDetail;
import cn.doanything.account.domain.detail.OuterAccountDetail;
import cn.doanything.account.domain.utils.AccountUtil;
import cn.doanything.account.facade.dto.AccountingRequest;
import cn.doanything.account.facade.dto.EntryDetail;
import cn.doanything.account.types.enums.AccountFamily;
import cn.doanything.commons.exceptions.BaseException;
import cn.doanything.commons.lang.SystemResultCode;
import cn.doanything.commons.lang.utils.AssertUtil;
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

    OuterAccountDetail toOuterAccountDetail(AccountingRequest request, EntryDetail detail);

    InnerAccountDetail toInnerAccountDetail(AccountingRequest request, EntryDetail detail);

    BufferedDetail toBufferedDetail(AccountingRequest request, EntryDetail detail);

    default AccountDetail toAccountDetail(AccountingRequest request, EntryDetail entryDetail) {
        AccountFamily accountFamily = AccountUtil.getAccountFamily(entryDetail.getAccountNo());
        AssertUtil.isNotNull(accountFamily, SystemResultCode.ILLEGAL_PARAM, "账户分类不存在");
        return switch (accountFamily) {
            case INNER -> toInnerAccountDetail(request, entryDetail);
            case OUTER -> toOuterAccountDetail(request, entryDetail);
            default -> throw new BaseException(SystemResultCode.ILLEGAL_PARAM);
        };
    }

    default List<AccountDetail> toAccountDetail(AccountingRequest request, List<EntryDetail> entryDetails) {
        List<AccountDetail> accountDetails = new ArrayList<>();
        if (entryDetails != null) {
            entryDetails.forEach(accountingRequestDetail -> accountDetails.add(toAccountDetail(request, accountingRequestDetail)));
        }
        return accountDetails;
    }

}
