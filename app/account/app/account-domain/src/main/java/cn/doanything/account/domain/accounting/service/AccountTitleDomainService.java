package cn.doanything.account.domain.accounting.service;

import cn.doanything.account.domain.accounting.AccountTitle;

/**
 * @author wxj
 * 2023/12/16
 */
public interface AccountTitleDomainService {

    /**
     * 设置科目层级
     * @param accountTitle
     * @param parentAccountTitle
     */
    void setAccountTitleLevel(AccountTitle accountTitle, AccountTitle parentAccountTitle);
}
