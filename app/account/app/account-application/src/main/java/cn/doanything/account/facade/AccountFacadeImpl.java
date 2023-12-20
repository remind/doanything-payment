package cn.doanything.account.facade;

import cn.doanything.account.application.convertor.AccountingRequestConvertor;
import cn.doanything.account.domain.detail.AccountDetail;
import cn.doanything.account.facade.dto.AccountingRequest;
import cn.doanything.account.facade.dto.AccountingRequestDetail;

import java.util.List;

/**
 * @author wxj
 * 2023/12/20
 */
public class AccountFacadeImpl implements AccountFacade {

    private AccountingRequestConvertor convertor;

    @Override
    public void apply(AccountingRequest accountingRequest) {
        List<AccountingRequestDetail> accountingRequestDetails = accountingRequest.getAccountingRequestDetails();
        List<AccountDetail> accountDetails = convertor.toAccountDetail(accountingRequest.getAccountingRequestDetails());

    }
}
