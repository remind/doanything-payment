package cn.doanything.account.domain.service;

import cn.doanything.account.domain.detail.AccountDetail;

import java.util.List;

/**
 * @author wxj
 * 2023/12/20
 */
public interface OuterAccountDomainService {

    /**
     * 改变余额
     * @param accountNo
     * @param accountDetails
     */
    void changeBalance(String accountNo, List<AccountDetail> accountDetails);
}
