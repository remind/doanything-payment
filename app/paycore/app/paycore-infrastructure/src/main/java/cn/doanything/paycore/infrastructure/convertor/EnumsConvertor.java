package cn.doanything.paycore.infrastructure.convertor;

import cn.doanything.commons.lang.utils.EnumUtil;
import cn.doanything.paycore.domain.instant.PayOrderStatus;
import cn.doanything.paycore.domain.instant.RefundOrderStatus;
import cn.doanything.paycore.types.PaymentType;
import cn.doanything.paycore.types.asset.BelongTo;
import cn.doanything.paycore.types.funds.FundActionType;
import org.mapstruct.Mapper;

/**
 * @author wxj
 * 2024/1/18
 */
@Mapper(componentModel = "spring")
public interface EnumsConvertor  {

    default PaymentType toPaymentType(String code) {
        return EnumUtil.getByCode(PaymentType.class, code);
    }

    default PayOrderStatus toPayOrderStatus(String code) {
        return EnumUtil.getByCode(PayOrderStatus.class, code);
    }

    default RefundOrderStatus toRefundOrderStatus(String code) {
        return EnumUtil.getByCode(RefundOrderStatus.class, code);
    }

    default FundActionType toFundActionType(String code) {
        return EnumUtil.getByCode(FundActionType.class, code);
    }

    default BelongTo toBelongTo(String code) {
        return EnumUtil.getByCode(BelongTo.class, code);
    }

}