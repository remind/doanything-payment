package cn.doanything.account.infrastructure.repository;

import cn.doanything.account.domain.detail.AccountDetail;
import cn.doanything.account.domain.detail.InnerAccountDetail;
import cn.doanything.account.domain.detail.OuterAccountDetail;
import cn.doanything.account.domain.repository.AccountDetailRepository;
import cn.doanything.account.infrastructure.persistence.convertor.InnerAccountDetailDalConvertor;
import cn.doanything.account.infrastructure.persistence.convertor.OuterAccountDetailDalConvertor;
import cn.doanything.account.infrastructure.persistence.convertor.OuterSubAccountDetailDalConvertor;
import cn.doanything.account.infrastructure.persistence.mapper.InnerAccountDetailMapper;
import cn.doanything.account.infrastructure.persistence.mapper.OuterAccountDetailMapper;
import cn.doanything.account.infrastructure.persistence.mapper.OuterSubAccountDetailMapper;
import cn.doanything.commons.exceptions.BizException;
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
    private OuterAccountDetailDalConvertor outDalConvertor;

    @Autowired
    private OuterAccountDetailMapper outDetailMapper;

    @Autowired
    private OuterSubAccountDetailDalConvertor subDalConvertor;

    @Autowired
    private OuterSubAccountDetailMapper subDetailMapper;

    @Autowired
    private InnerAccountDetailDalConvertor innerDalConvertor;

    @Autowired
    private InnerAccountDetailMapper innerDetailMapper;

    @Override
    public void store(List<AccountDetail> accountDetails) {
        accountDetails.forEach(accountDetail -> {
            if (accountDetail instanceof OuterAccountDetail) {
                outerDetailStore((OuterAccountDetail) accountDetail);
            } else if (accountDetail instanceof InnerAccountDetail) {
                innerDetailStore((InnerAccountDetail) accountDetail);
            } else {
                throw new BizException("异常明细");
            }
        });
    }

    private void outerDetailStore(OuterAccountDetail detail) {
        outDetailMapper.insert(outDalConvertor.toDo(detail));
        detail.getOuterSubAccountDetails().forEach(outerSubAccountDetail -> {
            subDetailMapper.insert(subDalConvertor.toDo(outerSubAccountDetail));
        });
    }

    private void innerDetailStore(InnerAccountDetail detail) {
        innerDetailMapper.insert(innerDalConvertor.toDo(detail));
    }

}
