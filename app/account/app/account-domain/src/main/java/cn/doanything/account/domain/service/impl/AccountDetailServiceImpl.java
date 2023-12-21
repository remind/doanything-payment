package cn.doanything.account.domain.service.impl;

import cn.doanything.account.domain.detail.AccountDetail;
import cn.doanything.account.domain.service.AccountDetailService;
import org.springframework.stereotype.Component;

/**
 * @author wxj
 * 2023/12/21
 */
@Component
public class AccountDetailServiceImpl implements AccountDetailService {
    @Override
    public boolean isBuffer(AccountDetail accountDetail) {
        return false;
    }
}
