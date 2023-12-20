package cn.doanything.account.application.entry.preprocess.impl;

import cn.doanything.account.application.entry.EntryContext;
import cn.doanything.account.application.entry.preprocess.AccountEntryPreprocessor;
import cn.doanything.account.facade.dto.AccountingRequest;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 操作分组
 * @author wxj
 * 2023/12/20
 */
@Component
@Order(3)
public class OperationGroupProcessor implements AccountEntryPreprocessor {
    @Override
    public void process(AccountingRequest accountingRequest, EntryContext entryContext) {

    }
}
