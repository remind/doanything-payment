package cn.doanything.account.application.entry;

import cn.doanything.account.application.entry.preprocess.AccountEntryPreprocessor;
import cn.doanything.account.application.entry.processor.AccountEntryProcessor;
import cn.doanything.account.domain.repository.AccountTransactionRepository;
import cn.doanything.account.facade.dto.AccountingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

/**
 * 入账
 * @author wxj
 * 2023/12/20
 */
@Component
public class AccountEntryServiceImpl implements AccountEntryService {

    @Autowired
    private List<AccountEntryPreprocessor> accountEntryPreprocessors;

    @Autowired
    private List<AccountEntryProcessor> accountEntryProcessors;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Autowired
    private AccountTransactionRepository accountTransactionRepository;


    @Override
    public void process(AccountingRequest request) {
        EntryContext entryContext = new EntryContext();
        accountEntryPreprocessors.forEach(accountEntryPreprocessor -> accountEntryPreprocessor.process(request, entryContext));
        transactionTemplate.executeWithoutResult(status -> {
            accountTransactionRepository.store(entryContext.getAccountTransaction());
            accountEntryProcessors.forEach(accountEntryProcessor -> accountEntryProcessor.process(entryContext));
        });
    }
}
