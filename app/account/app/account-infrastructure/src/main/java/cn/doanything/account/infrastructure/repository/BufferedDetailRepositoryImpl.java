package cn.doanything.account.infrastructure.repository;

import cn.doanything.account.domain.detail.BufferedDetail;
import cn.doanything.account.domain.repository.BufferedDetailRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wxj
 * 2023/12/21
 */
@Repository
public class BufferedDetailRepositoryImpl implements BufferedDetailRepository {
    @Override
    public void store(List<BufferedDetail> bufferedDetails) {

    }
}
