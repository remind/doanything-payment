package cn.doanything.account.domain.service;

import cn.doanything.account.domain.detail.AccountDetail;
import cn.doanything.account.domain.detail.InnerAccountDetail;

import java.util.List;

/**
 * @author wxj
 * 2023/12/25
 */
public interface InnerAccountDomainService {

    void changeBalance(String accountNo, List<AccountDetail> details);
}
