package cn.doanything.paycore.infrastructure.persistence.repository.inner;

import cn.doanything.paycore.infrastructure.persistence.convertor.FundDetailDalConvertor;
import cn.doanything.paycore.infrastructure.persistence.dataobject.FundDetailDO;
import cn.doanything.paycore.infrastructure.persistence.mapper.FundDetailMapper;
import cn.doanything.paycore.types.funds.FundDetail;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wxj
 * 2024/1/18
 */
@Repository
public class FundDetailInnerRepository  {

    @Autowired
    private FundDetailMapper dalMapper;

    @Autowired
    private FundDetailDalConvertor dalConvertor;

    public List<FundDetail> loadByPaymentId(String paymentId) {
        return dalConvertor.toEntity(dalMapper.selectList(buildPaymentIdQueryWrapper(paymentId)));
    }

    public void store(List<FundDetail> fundDetails) {
        fundDetails.forEach(fundDetail -> {
            dalMapper.insert(dalConvertor.toDo(fundDetail));
        });
    }

    public void reStore(List<FundDetail> fundDetails) {
        fundDetails.forEach(fundDetail -> {
            dalMapper.updateById(dalConvertor.toDo(fundDetail));
        });
    }

    private Wrapper<FundDetailDO> buildPaymentIdQueryWrapper(String paymentId) {
        return new LambdaQueryWrapper<FundDetailDO>().eq(FundDetailDO::getPaymentId, paymentId);
    }
}
