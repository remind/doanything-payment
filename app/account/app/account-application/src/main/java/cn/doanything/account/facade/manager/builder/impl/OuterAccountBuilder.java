package cn.doanything.account.facade.manager.builder.impl;

import cn.doanything.account.domain.*;
import cn.doanything.account.domain.accounting.AccountTitle;
import cn.doanything.account.domain.repository.AccountTitleRepository;
import cn.doanything.account.domain.repository.AccountTypeRepository;
import cn.doanything.account.domain.utils.AccountUtil;
import cn.doanything.account.facade.manager.builder.AccountBuilder;
import cn.doanything.account.facade.manager.convertor.OuterAccountConvertor;
import cn.doanything.account.facade.manager.dto.OuterAccountAddRequest;
import cn.doanything.account.types.enums.DenyStatus;
import cn.doanything.commons.response.GlobalResultCode;
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
        AssertUtil.isNotNull(outerAccountType, GlobalResultCode.ILLEGAL_PARAM, "账户类型不存在");
        OuterAccount outerAccount = outerAccountConvertor.toOuterAccount(request, outerAccountType);
        outerAccount.setDenyStatus(DenyStatus.INIT);
        fillBalanceDirection(outerAccount, outerAccountType.getTitleCode());
        fillOuterSubAccount(outerAccount, outerAccountType.getFundTypes());
        return outerAccount;
    }

    private void fillBalanceDirection(OuterAccount outerAccount, String titleCode) {
        AccountTitle accountTitle = accountTitleRepository.load(titleCode);
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
