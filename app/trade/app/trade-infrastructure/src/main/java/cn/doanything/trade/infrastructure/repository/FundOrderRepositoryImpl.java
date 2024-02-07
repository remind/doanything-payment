package cn.doanything.trade.infrastructure.repository;

import cn.doanything.trade.domain.fund.FundOrder;
import cn.doanything.trade.domain.repository.FundOrderRepository;
import org.springframework.stereotype.Repository;

/**
 * @author wxj
 * 2024/2/7
 */
@Repository
public class FundOrderRepositoryImpl implements FundOrderRepository {
    @Override
    public void store(FundOrder fundOrder) {

    }

    @Override
    public void reStore(FundOrder fundOrder) {

    }
}
