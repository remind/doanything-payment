package cn.doanything.account.domain.buffer.service;

import cn.doanything.account.domain.detail.AccountDetail;
import cn.doanything.account.domain.repository.BufferedRuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wxj
 * 2023/12/25
 */
@Service
public class BufferedService {

    @Autowired
    private BufferedRuleRepository bufferedRuleRepository;

    /**
     * 是否缓冲
     * @param accountDetail
     * @return
     */
    public boolean isBuffer(AccountDetail accountDetail) {
        return bufferedRuleRepository.isExists(accountDetail.getAccountNo(), accountDetail.getCrDr());
    }
}
