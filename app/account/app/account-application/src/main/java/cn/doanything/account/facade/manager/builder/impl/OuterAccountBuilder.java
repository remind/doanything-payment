package cn.doanything.account.facade.manager.builder.impl;

import cn.doanything.account.domain.*;
import cn.doanything.account.domain.accounting.AccountTitle;
import cn.doanything.account.domain.repository.AccountTitleRepository;
import cn.doanything.account.domain.repository.AccountTypeRepository;
import cn.doanything.account.domain.utils.AccountUtil;
import cn.doanything.account.facade.manager.builder.AccountBuilder;
import cn.doanything.account.facade.manager.convertor.OuterAccountConvertor;
import cn.doanything.account.facade.manager.dto.OuterAccountAddRequest;
import cn.doanything.commons.lang.SystemResultCode;
import cn.doanything.commons.lang.utils.AssertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author wxj
 * 2023/12/22
 */
@Component
public class OuterAccountBuilder implements AccountBuilder {

    @Autowired
    private AccountTypeRepository accountTypeRepository;

    @Autowired
    private AccountTitleRepository accountTitleRepository;

    @Autowired
    private OuterAccountConvertor outerAccountConvertor;

    @Override
    public Account build(OuterAccountAddRequest request) {
        OuterAccountType outerAccountType = accountTypeRepository.load(request.getAccountType());
        AssertUtil.isNotNull(outerAccountType, SystemResultCode.ILLEGAL_PARAM, "账户类型不存在");
        OuterAccount outerAccount = outerAccountConvertor.toOuterAccount(request, outerAccountType);
        fillBalanceDirection(outerAccount, outerAccountType.getAccountTitleNo());
        fillOuterSubAccount(outerAccount, outerAccountType.getFundTypes());
        return outerAccount;
    }

    private void fillBalanceDirection(OuterAccount outerAccount, String titleNo) {
        AccountTitle accountTitle = accountTitleRepository.load(titleNo);
        outerAccount.setBalanceDirection(accountTitle.getBalanceDirection());
        outerAccount.setCurrentBalanceDirection(AccountUtil.getBalanceCrdr(accountTitle.getBalanceDirection()));
    }

    private void fillOuterSubAccount(OuterAccount outerAccount, List<String> fundTypes) {
        if (CollectionUtils.isEmpty(fundTypes)) {
            outerAccount.addSubAccount(AccountDomainConstants.DEFAULT_FUND_TYPE);
        } else {
            fundTypes.forEach(outerAccount::addSubAccount);
        }
    }

}
