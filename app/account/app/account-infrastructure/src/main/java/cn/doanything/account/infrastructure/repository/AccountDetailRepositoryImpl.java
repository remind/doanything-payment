package cn.doanything.account.infrastructure.repository;

import cn.doanything.account.domain.detail.AccountDetail;
import cn.doanything.account.domain.detail.OuterAccountDetail;
import cn.doanything.account.domain.repository.AccountDetailRepository;
import cn.doanything.account.infrastructure.persistence.convertor.OuterAccountDetailDalConvertor;
import cn.doanything.account.infrastructure.persistence.convertor.OuterSubAccountDetailDalConvertor;
import cn.doanything.account.infrastructure.persistence.mapper.OuterAccountDetailMapper;
import cn.doanything.account.infrastructure.persistence.mapper.OuterSubAccountDetailMapper;
import cn.doanything.commons.lang.utils.AssertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wxj
 * 2023/12/21
 */
@Repository
public class AccountDetailRepositoryImpl implements AccountDetailRepository {

    @Autowired
    private OuterAccountDetailDalConvertor dalConvertor;

    @Autowired
    private OuterAccountDetailMapper detailMapper;

    @Autowired
    private OuterSubAccountDetailDalConvertor subDalConvertor;

    @Autowired
    private OuterSubAccountDetailMapper subDetailMapper;

    @Override
    public void store(List<AccountDetail> accountDetails) {
        accountDetails.forEach(accountDetail -> {
            AssertUtil.isTrue(accountDetail instanceof OuterAccountDetail, "账务明细有非外部户的");
            singleStore((OuterAccountDetail) accountDetail);
        });
    }

    private void singleStore(OuterAccountDetail accountDetail) {
        detailMapper.insert(dalConvertor.toDo(accountDetail));
        accountDetail.getOuterSubAccountDetails().forEach(outerSubAccountDetail -> {
            subDetailMapper.insert(subDalConvertor.toDo(outerSubAccountDetail));
        });
    }
}
