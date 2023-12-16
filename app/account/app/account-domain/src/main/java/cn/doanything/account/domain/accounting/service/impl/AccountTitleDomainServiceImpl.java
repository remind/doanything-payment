package cn.doanything.account.domain.accounting.service.impl;

import cn.doanything.account.domain.AccountDomainConstants;
import cn.doanything.account.domain.accounting.AccountTitle;
import cn.doanything.account.domain.accounting.service.AccountTitleDomainService;
import cn.doanything.commons.lang.utils.AssertUtil;
import org.springframework.stereotype.Component;

/**
 * @author wxj
 * 2023/12/16
 */
@Component
public class AccountTitleDomainServiceImpl implements AccountTitleDomainService {

    @Override
    public void setAccountTitleLevel(AccountTitle accountTitle, AccountTitle parentAccountTitle) {
        if (parentAccountTitle == null) {
            accountTitle.setTitleLevel(AccountDomainConstants.ACCOUNT_TITLE_LEVEL_MIN);
            accountTitle.setLeaf(false);
        } else {
            AssertUtil.isTrue(parentAccountTitle.getTitleLevel() < AccountDomainConstants.ACCOUNT_TITLE_LEVEL_MAX
                    , "科目层级已经超过最大值");
            accountTitle.setTitleLevel(parentAccountTitle.getTitleLevel() + 1);
            accountTitle.setLeaf(accountTitle.getTitleLevel() == AccountDomainConstants.ACCOUNT_TITLE_LEVEL_MAX);
        }
    }
}
