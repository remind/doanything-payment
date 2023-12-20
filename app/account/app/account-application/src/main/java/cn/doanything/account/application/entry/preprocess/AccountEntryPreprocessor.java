package cn.doanything.account.application.entry.preprocess;

import cn.doanything.account.application.entry.EntryContext;
import cn.doanything.account.facade.dto.AccountingRequest;

/**
 * @author wxj
 * 2023/12/20
 */
public interface AccountEntryPreprocessor {

    void process(AccountingRequest accountingRequest, EntryContext entryContext);
}
