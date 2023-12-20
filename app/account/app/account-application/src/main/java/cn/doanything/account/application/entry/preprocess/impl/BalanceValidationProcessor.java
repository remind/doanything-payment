package cn.doanything.account.application.entry.preprocess.impl;

import cn.doanything.account.application.entry.EntryContext;
import cn.doanything.account.application.entry.preprocess.AccountEntryPreprocessor;
import cn.doanything.account.facade.dto.AccountingRequest;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author wxj
 * 2023/12/20
 */
@Component
@Order(2)
public class BalanceValidationProcessor implements AccountEntryPreprocessor {
    @Override
    public void process(AccountingRequest accountingRequest, EntryContext entryContext) {

    }
}
