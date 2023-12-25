package cn.doanything.account.infrastructure.repository;

import cn.doanything.account.domain.AccountDomainConstants;
import cn.doanything.account.domain.InnerAccount;
import cn.doanything.account.domain.repository.InnerAccountRepository;
import cn.doanything.account.infrastructure.persistence.convertor.InnerAccountDalConvertor;
import cn.doanything.account.infrastructure.persistence.dataobject.InnerAccountDO;
import cn.doanything.account.infrastructure.persistence.mapper.InnerAccountMapper;
import cn.doanything.commons.lang.utils.AssertUtil;
import cn.doanything.commons.response.GlobalResultCode;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.Currency;
import java.util.List;

/**
 * @author wxj
 * 2023/12/23
 */
@Repository
public class InnerAccountRepositoryImpl implements InnerAccountRepository {

    @Autowired
    private InnerAccountMapper mapper;

    @Autowired
    private InnerAccountDalConvertor dalConvertor;

    @Override
    public String store(InnerAccount account) {
        AssertUtil.isNotNull(account, "账户不存在");
        account.setAccountNo(genAccountNo(account.getTitleCode(), account.getCurrencyCode()));
        mapper.insert(dalConvertor.toDo(account));
        return account.getAccountNo();
    }

    @Override
    public void reStore(InnerAccount account) {
        AssertUtil.isNotNull(account, GlobalResultCode.ILLEGAL_PARAM);
        mapper.update(dalConvertor.toDo(account), getIdWrapper(account.getAccountNo()));
    }

    @Override
    public InnerAccount load(String accountNo) {
        AssertUtil.isNotNull(accountNo, GlobalResultCode.ILLEGAL_PARAM);
        return dalConvertor.toEntity(mapper.selectOne(getIdWrapper(accountNo)));
    }

    @Override
    public InnerAccount lock(String accountNo) {
        AssertUtil.isNotNull(accountNo, GlobalResultCode.ILLEGAL_PARAM);
        return dalConvertor.toEntity(mapper.lockOne(getIdWrapper(accountNo)));
    }

    private String genAccountNo(String titleCode, String currencyCode) {
        String incId = getIncId(titleCode, currencyCode);
        return titleCode + Currency.getInstance(currencyCode).getNumericCode() + incId;
    }
    private String getIncId(String titleCode, String currencyCode) {
        String accountNoPrefix = titleCode + Currency.getInstance(currencyCode).getNumericCode();
        int maxLength = String.valueOf(AccountDomainConstants.INNER_ACCOUNT_NO_MAX_INC).length();
        List<InnerAccountDO> accountDOS = mapper.selectList(new LambdaQueryWrapper<InnerAccountDO>()
                .like(InnerAccountDO::getAccountNo, accountNoPrefix)
                .orderByDesc(InnerAccountDO::getAccountNo));
        int intMaxNo = 0;
        if (!CollectionUtils.isEmpty(accountDOS)) {
            String maxNo = accountDOS.get(0).getAccountNo().substring(accountDOS.get(0).getAccountNo().length() - maxLength);
            intMaxNo = Integer.parseInt(maxNo);
            AssertUtil.isTrue(intMaxNo > 0 && intMaxNo < AccountDomainConstants.OUTER_ACCOUNT_NO_MAX_INC, "用户账户已经超过最大个数");
        }
        return String.format("%0" + maxLength + "d", intMaxNo + 1);
    }

    private Wrapper<InnerAccountDO> getIdWrapper(String accountNo) {
        return new LambdaQueryWrapper<InnerAccountDO>().eq(InnerAccountDO::getAccountNo, accountNo);
    }
}
