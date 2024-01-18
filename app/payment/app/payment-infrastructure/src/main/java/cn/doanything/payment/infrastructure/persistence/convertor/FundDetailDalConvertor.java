package cn.doanything.payment.infrastructure.persistence.convertor;

import cn.doanything.commons.convertor.ReadWriteConvertor;
import cn.doanything.payment.infrastructure.persistence.dataobject.FundDetailDO;
import cn.doanything.payment.types.funds.FundDetail;
import org.mapstruct.Mapper;

/**
 * @author wxj
 * 2024/1/17
 */
//@Mapper(componentModel = "spring")
public interface FundDetailDalConvertor extends ReadWriteConvertor<FundDetail, FundDetailDO> {
}
