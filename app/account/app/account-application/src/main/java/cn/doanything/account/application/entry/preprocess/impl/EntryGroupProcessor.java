package cn.doanything.account.application.entry.preprocess.impl;

import cn.doanything.account.application.convertor.AccountingRequestConvertor;
import cn.doanything.account.application.entry.AccountEntryGroup;
import cn.doanything.account.application.entry.EntryContext;
import cn.doanything.account.application.entry.preprocess.AccountEntryPreprocessor;
import cn.doanything.account.domain.AccountDomainConstants;
import cn.doanything.account.domain.detail.AccountDetail;
import cn.doanything.account.domain.detail.OuterAccountDetail;
import cn.doanything.account.domain.service.AccountDetailService;
import cn.doanything.account.facade.dto.AccountingRequest;
import cn.doanything.account.facade.dto.FundSpiltRule;
import cn.doanything.commons.lang.types.Money;
import cn.doanything.commons.lang.utils.AssertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
        request.getEntryDetails().forEach(entryDetail -> {
            AccountDetail accountDetail = convertor.toAccountDetail(request, entryDetail);
            if (accountDetailService.isBuffer(accountDetail)) {
                entryContext.getBufferedDetails().add(convertor.toBufferedDetail(request, entryDetail));
            } else {
                if (!groupMap.containsKey(entryDetail.getAccountNo())) {
                    AccountEntryGroup accountEntryGroup = new AccountEntryGroup();
                    accountEntryGroup.setAccountNo(entryDetail.getAccountNo());
                    accountEntryGroup.setDetails(new ArrayList<>());
                    groupMap.put(entryDetail.getAccountNo(), accountEntryGroup);
                }
                if (accountDetail instanceof OuterAccountDetail) {
                    addOuterSubDetail((OuterAccountDetail) accountDetail, entryDetail.getSpiltRules());
                }
                groupMap.get(entryDetail.getAccountNo()).getDetails().add(accountDetail);
            }
        });
        groupMap.values().forEach(accountEntryGroup -> entryContext.getAccountEntryGroups().add(accountEntryGroup));
    }

    private void addOuterSubDetail(OuterAccountDetail outerAccountDetail, List<FundSpiltRule> fundSpiltRules) {
        if (CollectionUtils.isEmpty(fundSpiltRules)) {
            outerAccountDetail.addSubDetail(AccountDomainConstants.DEFAULT_FUND_TYPE, outerAccountDetail.getAmount());
        } else {
            Money totalAmount = new Money();
            fundSpiltRules.forEach(fundSpiltRule -> {
                totalAmount.addTo(fundSpiltRule.getAmount());
                outerAccountDetail.addSubDetail(fundSpiltRule.getFundType(), fundSpiltRule.getAmount());
            });
            AssertUtil.isTrue(totalAmount.equals(outerAccountDetail.getAmount()), "资金分摊金额不等于总金额");
        }
    }
}
