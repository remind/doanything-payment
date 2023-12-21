package cn.doanything.account.application.entry.processor.impl;

import cn.doanything.account.application.entry.AccountEntryGroup;
import cn.doanything.account.application.entry.EntryContext;
import cn.doanything.account.application.entry.processor.AccountEntryProcessor;
import cn.doanything.account.domain.service.OuterAccountDomainService;
import cn.doanything.account.domain.utils.AccountUtil;
import cn.doanything.account.types.enums.AccountFamily;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author wxj
 * 2023/12/21
 */
@Component
@Order(1)
public class ChangeBalanceProcessor implements AccountEntryProcessor {

    @Autowired
    private OuterAccountDomainService outerAccountDomainService;

    @Override
    public void process(EntryContext entryContext) {
        List<AccountEntryGroup> accountEntryGroups = entryContext.getAccountEntryGroups();
        accountEntryGroups.forEach(accountEntryGroup -> {
            if (AccountFamily.OUTER == AccountUtil.getAccountFamily(accountEntryGroup.getAccountNo())) {
                outerAccountDomainService.changeBalance(accountEntryGroup.getAccountNo(), accountEntryGroup.getDetails());
            }
        });
    }
}
