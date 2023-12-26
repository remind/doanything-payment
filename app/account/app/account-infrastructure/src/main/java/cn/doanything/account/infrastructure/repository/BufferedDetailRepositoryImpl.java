package cn.doanything.account.infrastructure.repository;

import cn.doanything.account.domain.detail.BufferedDetail;
import cn.doanything.account.domain.repository.BufferedDetailRepository;
import cn.doanything.account.infrastructure.persistence.convertor.BufferedDetailDalConvertor;
import cn.doanything.account.infrastructure.persistence.dataobject.BufferedDetailDO;
import cn.doanything.account.infrastructure.persistence.mapper.BufferedDetailMapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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
    private BufferedDetailDalConvertor dalConvertor;

    @Autowired
    private BufferedDetailMapper mapper;

    @Override
    public void store(List<BufferedDetail> bufferedDetails) {
        bufferedDetails.forEach(detail -> {
            mapper.insert(dalConvertor.toDo(detail));
        });
    }

    @Override
    public void reStore(BufferedDetail bufferedDetail) {
        mapper.update(dalConvertor.toDo(bufferedDetail), getIdWrapper(bufferedDetail.getVoucherNo()));
    }

    @Override
    public BufferedDetail lock(String voucherNo) {
        return dalConvertor.toEntity(mapper.lockOne(getIdWrapper(voucherNo)));
    }

    private Wrapper<BufferedDetailDO> getIdWrapper(String voucherNo) {
        return new LambdaQueryWrapper<BufferedDetailDO>().eq(BufferedDetailDO::getVoucherNo, voucherNo);
    }
}
