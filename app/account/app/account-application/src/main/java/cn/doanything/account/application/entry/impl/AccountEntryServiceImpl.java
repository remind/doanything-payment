package cn.doanything.account.application.entry.impl;

import cn.doanything.account.application.convertor.AccountingRequestConvertor;
import cn.doanything.account.application.entry.AccountEntryGroup;
import cn.doanything.account.application.entry.AccountEntryService;
import cn.doanything.account.application.entry.EntryContext;
import cn.doanything.account.application.entry.preprocess.AccountEntryPreprocessor;
import cn.doanything.account.domain.detail.BufferedDetail;
import cn.doanything.account.domain.repository.AccountTransactionRepository;
import cn.doanything.account.domain.repository.BufferedDetailRepository;
import cn.doanything.account.domain.service.InnerAccountDomainService;
import cn.doanything.account.domain.service.OuterAccountDomainService;
import cn.doanything.account.domain.utils.AccountUtil;
import cn.doanything.account.facade.dto.AccountingRequest;
import cn.doanything.account.types.enums.AccountFamily;
import cn.doanything.commons.exceptions.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

/**
 * 入账
 *
 * @author wxj
 * 2023/12/20
 */
@Component
public class AccountEntryServiceImpl implements AccountEntryService {

    @Autowired
    private AccountingRequestConvertor requestConvertor;
    @Autowired
    private List<AccountEntryPreprocessor> accountEntryPreprocessors;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Autowired
    private AccountTransactionRepository accountTransactionRepository;

    @Autowired
    private OuterAccountDomainService outerAccountDomainService;

    @Autowired
    private InnerAccountDomainService innerAccountDomainService;

    @Autowired
    private BufferedDetailRepository bufferedDetailRepository;

    @Override
    public void process(AccountingRequest request) {
        EntryContext entryContext = new EntryContext();
        entryContext.setAccountTransaction(requestConvertor.toAccountTransaction(request));
        accountEntryPreprocessors.forEach(accountEntryPreprocessor -> accountEntryPreprocessor.process(request, entryContext));
        transactionTemplate.executeWithoutResult(status -> {
            accountTransactionRepository.store(entryContext.getAccountTransaction());
            processDetail(entryContext.getAccountEntryGroups());
            bufferedDetailRepository.store(entryContext.getBufferedDetails());
        });
    }

    @Override
    public void processBufferedDetail(String voucherNo) {
        transactionTemplate.executeWithoutResult(status -> {
            BufferedDetail bufferedDetail = bufferedDetailRepository.lock(voucherNo);
        });
    }

    private void processDetail(List<AccountEntryGroup> accountEntryGroups) {
        accountEntryGroups.forEach(accountEntryGroup -> {
            if (AccountFamily.OUTER == AccountUtil.getAccountFamily(accountEntryGroup.getAccountNo())) {
                outerAccountDomainService.changeBalance(accountEntryGroup.getAccountNo(), accountEntryGroup.getDetails());
            } else if (AccountFamily.INNER == AccountUtil.getAccountFamily(accountEntryGroup.getAccountNo())) {
                innerAccountDomainService.changeBalance(accountEntryGroup.getAccountNo(), accountEntryGroup.getDetails());
            } else {
                throw new BizException("账户类型不存在");
            }
        });
    }
}
