package cn.doanything.account.domain.service.impl;

import cn.doanything.account.domain.OuterAccount;
import cn.doanything.account.domain.OuterSubAccount;
import cn.doanything.account.domain.detail.AccountDetail;
import cn.doanything.account.domain.detail.OuterAccountDetail;
import cn.doanything.account.domain.repository.AccountDetailRepository;
import cn.doanything.account.domain.repository.OuterAccountRepository;
import cn.doanything.account.domain.service.OuterAccountDomainService;
import cn.doanything.account.domain.utils.AccountUtil;
import cn.doanything.account.types.AccountResultCode;
import cn.doanything.account.types.enums.IODirection;
import cn.doanything.commons.exceptions.BizException;
import cn.doanything.commons.lang.utils.AssertUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author wxj
 * 2023/12/20
 */
@Component
@Slf4j
public class OuterAccountDomainServiceImpl implements OuterAccountDomainService {

    @Autowired
    private OuterAccountRepository outerAccountRepository;

    @Autowired
    private AccountDetailRepository accountDetailRepository;

    @Override
    public void changeBalance(String accountNo, List<AccountDetail> accountDetails) {
        OuterAccount account;
        try {
            account = outerAccountRepository.lock(accountNo);
        } catch (Exception e) {
            log.error("账户锁定异常,accountNo=" + accountNo, e);
            throw new BizException(AccountResultCode.ACCOUNT_LOCK_TIME_OUT);
        }
        AssertUtil.isNotNull(account, AccountResultCode.ACCOUNT_ID_NOT_EXISTS);

        accountDetails.forEach(accountDetail -> {
            OuterAccountDetail outerAccountDetail = (OuterAccountDetail) accountDetail;
            outerAccountDetail.setIoDirection(AccountUtil.convert(account.getCurrentBalanceDirection()
                    , outerAccountDetail.getCrDr()));
            checkBalance(account, outerAccountDetail);
            updateBalance(account, outerAccountDetail);
        });

        accountDetailRepository.store(accountDetails);
        outerAccountRepository.reStore(account);
    }

    private void checkBalance(OuterAccount outerAccount, OuterAccountDetail outerAccountDetail) {
        if (outerAccountDetail.getIoDirection() == IODirection.OUT) {
            AssertUtil.isFalse(outerAccountDetail.getAmount().greaterThan(outerAccount.getAvailableBalance())
                    , AccountResultCode.ACCOUNT_BALANCE_NOT_ENOUGH);
        }
    }

    /**
     * 修改子户余额
     *
     * @param outerAccount
     * @param outerAccountDetail
     */
    private void updateBalance(OuterAccount outerAccount, OuterAccountDetail outerAccountDetail) {
        outerAccountDetail.setBeforeBalance(outerAccount.getBalance());
        outerAccountDetail.getOuterSubAccountDetails().forEach(outerSubAccountDetail -> {
            OuterSubAccount outerSubAccount = outerAccount.getSubAccountByFundType(outerSubAccountDetail.getFundType());
            AssertUtil.isNotNull(outerSubAccount, AccountResultCode.SUB_ACCOUNT_NOT_EXISTS);

            outerSubAccountDetail.setBeforeBalance(outerSubAccount.getBalance());
            outerSubAccountDetail.setMemo(outerAccountDetail.getMemo());

            outerSubAccount.updateAvailableBalance(outerAccountDetail.getIoDirection(), outerSubAccountDetail.getAmount());
            outerSubAccountDetail.setAfterBalance(outerSubAccount.getBalance());
        });
        outerAccountDetail.setAfterBalance(outerAccount.getBalance());
    }


}
