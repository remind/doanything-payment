package cn.doanything.account.application.entry.preprocess.impl;

import cn.doanything.account.application.entry.EntryContext;
import cn.doanything.account.application.entry.preprocess.AccountEntryPreprocessor;
import cn.doanything.account.facade.dto.AccountingRequest;
import cn.doanything.account.facade.dto.EntryDetail;
import cn.doanything.account.types.AccountResultCode;
import cn.doanything.commons.exceptions.BaseException;
import cn.doanything.commons.lang.types.Money;
import cn.doanything.commons.lang.utils.AssertUtil;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 平衡校验
 * @author wxj
 * 2023/12/20
 */
@Component
@Order(2)
public class BalanceValidationProcessor implements AccountEntryPreprocessor {
    @Override
    public void process(AccountingRequest request, EntryContext entryContext) {
        Map<String, List<EntryDetail>> entryMap = new HashMap<>();
        AssertUtil.isNotNull(request.getEntryDetails(), AccountResultCode.ILLEGAL_PARAM, "入账明细不能为空");
        request.getEntryDetails().forEach(entryDetail -> {
            if (!entryMap.containsKey(entryDetail.getSuiteNo())) {
                entryMap.put(entryDetail.getSuiteNo(), new ArrayList<>());
            }
            entryMap.get(entryDetail.getSuiteNo()).add(entryDetail);
        });

        entryMap.keySet().forEach(suiteNo -> {
            List<EntryDetail> entryDetails = entryMap.get(suiteNo);
            Money drAmount = new Money();
            Money crAmount = new Money();
            entryDetails.forEach(entryDetail -> {
                switch (entryDetail.getCrDr()) {
                    case DEBIT -> drAmount.addTo(entryDetail.getAmount());
                    case CREDIT -> crAmount.addTo(entryDetail.getAmount());
                    default -> throw new BaseException(AccountResultCode.CR_DR_NOT_EXISTS);
                }
            });
            AssertUtil.isTrue(drAmount.equals(crAmount), AccountResultCode.CR_DR_NOT_EXISTS, "套号内借贷不平衡,套号:" + suiteNo);
        });

    }
}
