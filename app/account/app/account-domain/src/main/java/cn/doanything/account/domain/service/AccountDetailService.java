package cn.doanything.account.domain.service;

import cn.doanything.account.domain.detail.AccountDetail;

/**
 * @author wxj
 * 2023/12/21
 */
public interface AccountDetailService {

    /**
     * 是否缓冲
     * @param accountDetail
     * @return
     */
    boolean isBuffer(AccountDetail accountDetail);
}
