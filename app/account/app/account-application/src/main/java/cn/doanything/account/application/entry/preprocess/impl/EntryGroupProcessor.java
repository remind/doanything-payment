package cn.doanything.account.application.entry.preprocess.impl;

import cn.doanything.account.application.convertor.AccountingRequestConvertor;
import cn.doanything.account.application.entry.AccountEntryGroup;
import cn.doanything.account.application.entry.EntryContext;
import cn.doanything.account.application.entry.preprocess.AccountEntryPreprocessor;
import cn.doanything.account.domain.detail.AccountDetail;
import cn.doanything.account.facade.dto.AccountingRequest;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 分组
 * @author wxj
 * 2023/12/20
 */
@Component
@Order(3)
public class EntryGroupProcessor implements AccountEntryPreprocessor {

    @Autowired
    private AccountingRequestConvertor convertor;

    @Override
    public void process(AccountingRequest accountingRequest, EntryContext entryContext) {
        Map<String, AccountEntryGroup> groupMap = new HashMap<>();
        accountingRequest.getAccountingRequestDetails().forEach(accountingRequestDetail -> {
            AccountDetail accountDetail = convertor.toAccountDetail(accountingRequestDetail);;
            if (groupMap.containsKey(accountingRequestDetail.getAccountNo())) {
                groupMap.get(accountingRequestDetail.getAccountNo()).getDetails().add(accountDetail);
            } else {
                AccountEntryGroup accountEntryGroup = new AccountEntryGroup();
                accountEntryGroup.setAccountNo(accountingRequestDetail.getAccountNo());
                accountEntryGroup.setDetails(Lists.newArrayList(accountDetail));
                groupMap.put(accountingRequestDetail.getAccountNo(),accountEntryGroup);
            }
        });
        groupMap.values().forEach(accountEntryGroup -> entryContext.getAccountEntryGroups().add(accountEntryGroup));
    }
}
