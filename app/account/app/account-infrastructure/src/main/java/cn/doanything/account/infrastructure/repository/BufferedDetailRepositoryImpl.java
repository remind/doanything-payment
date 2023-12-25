package cn.doanything.account.infrastructure.repository;

import cn.doanything.account.domain.detail.BufferedDetail;
import cn.doanything.account.domain.repository.BufferedDetailRepository;
import cn.doanything.account.infrastructure.persistence.convertor.BufferedDetailDalConvertor;
import cn.doanything.account.infrastructure.persistence.mapper.BufferedDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wxj
 * 2023/12/21
 */
@Repository
public class BufferedDetailRepositoryImpl implements BufferedDetailRepository {

    @Autowired
    private BufferedDetailDalConvertor bufferedDalConvertor;

    @Autowired
    private BufferedDetailMapper bufferedDetailMapper;

    @Override
    public void store(List<BufferedDetail> bufferedDetails) {
        bufferedDetails.forEach(detail -> {
            bufferedDetailMapper.insert(bufferedDalConvertor.toDo(detail));
        });
    }
}
