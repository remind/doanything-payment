package cn.doanything.account.application.entry.preprocess.impl;

import cn.doanything.account.application.convertor.AccountingRequestConvertor;
import cn.doanything.account.application.entry.AccountEntryGroup;
import cn.doanything.account.application.entry.EntryContext;
import cn.doanything.account.application.entry.preprocess.AccountEntryPreprocessor;
import cn.doanything.account.domain.detail.AccountDetail;
import cn.doanything.account.domain.service.AccountDetailService;
import cn.doanything.account.facade.dto.AccountingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 分组
 *
 * @author wxj
 * 2023/12/20
 */
@Component
@Order(3)
public class EntryGroupProcessor implements AccountEntryPreprocessor {

    @Autowired
    private AccountingRequestConvertor convertor;

    @Autowired
    private AccountDetailService accountDetailService;

    @Override
    public void process(AccountingRequest request, EntryContext entryContext) {
        Map<String, AccountEntryGroup> groupMap = new HashMap<>();
        request.getEntryDetails().forEach(accountingRequestDetail -> {
            AccountDetail accountDetail = convertor.toAccountDetail(request, accountingRequestDetail);
            if (accountDetailService.isBuffer(accountDetail)) {
                entryContext.getBufferedDetails().add(convertor.toBufferedDetail(request, accountingRequestDetail));
            } else {
                if (!groupMap.containsKey(accountingRequestDetail.getAccountNo())) {
                    AccountEntryGroup accountEntryGroup = new AccountEntryGroup();
                    accountEntryGroup.setAccountNo(accountingRequestDetail.getAccountNo());
                    accountEntryGroup.setDetails(new ArrayList<>());
                    groupMap.put(accountingRequestDetail.getAccountNo(), accountEntryGroup);
                }
                groupMap.get(accountingRequestDetail.getAccountNo()).getDetails().add(accountDetail);
            }
        });
        groupMap.values().forEach(accountEntryGroup -> entryContext.getAccountEntryGroups().add(accountEntryGroup));
    }
}
