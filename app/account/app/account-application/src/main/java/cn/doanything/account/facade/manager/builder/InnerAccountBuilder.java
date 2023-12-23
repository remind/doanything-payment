package cn.doanything.account.facade.manager.builder;

import cn.doanything.account.domain.InnerAccount;
import cn.doanything.account.domain.accounting.AccountTitle;
import cn.doanything.account.domain.repository.AccountTitleRepository;
import cn.doanything.account.domain.utils.AccountUtil;
import cn.doanything.account.facade.manager.convertor.InnerAccountConvertor;
import cn.doanything.account.facade.manager.dto.InnerAccountAddRequest;
import cn.doanything.account.types.accounting.AccountTitleStatus;
import cn.doanything.commons.lang.types.Money;
import cn.doanything.commons.lang.utils.AssertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Currency;

/**
 * @author wxj
 * 2023/12/23
 */
@Component
public class InnerAccountBuilder {

    @Autowired
    private InnerAccountConvertor convertor;

    @Autowired
    private AccountTitleRepository accountTitleRepository;

    public InnerAccount build(InnerAccountAddRequest request) {
        InnerAccount account = convertor.toOuterAccount(request);
        account.setBalance(new Money(0, Currency.getInstance(request.getCurrencyCode())));

        AccountTitle accountTitle = accountTitleRepository.load(request.getTitleCode());
        AssertUtil.isNotNull(accountTitle, "科目不存在");
        AssertUtil.isTrue(accountTitle.getStatus() == AccountTitleStatus.VALID, "科目状态不是有效");

        account.setBalanceDirection(accountTitle.getBalanceDirection());
        account.setCurrentBalanceDirection(AccountUtil.getBalanceCrdr(accountTitle.getBalanceDirection()));

        return account;
    }
}
