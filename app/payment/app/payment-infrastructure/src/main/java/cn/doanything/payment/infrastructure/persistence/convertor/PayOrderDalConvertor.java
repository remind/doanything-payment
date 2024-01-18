package cn.doanything.payment.infrastructure.persistence.convertor;

import cn.doanything.commons.convertor.GlobalTypeConvertor;
import cn.doanything.payment.domain.BasePayOrder;
import cn.doanything.payment.domain.instant.PayOrder;
import cn.doanything.payment.domain.instant.RefundOrder;
import cn.doanything.payment.infrastructure.convertor.EnumsConvertor;
import cn.doanything.payment.infrastructure.persistence.dataobject.PayOrderDO;
import cn.doanything.payment.types.PayOrderType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wxj
 * 2024/1/17
 */
@Mapper(componentModel = "spring", uses = {EnumsConvertor.class})
public interface PayOrderDalConvertor extends GlobalTypeConvertor {

    default BasePayOrder toBasePayOrder(PayOrderDO payOrderDO) {
        BasePayOrder basePayOrder = null;
        if (payOrderDO != null) {
            if (PayOrderType.PAY.getCode().equals(payOrderDO.getOrderType())) {
                return toPayOrder(payOrderDO);
            } else if (PayOrderType.REFUND.getCode().equals(payOrderDO.getOrderType())) {
                return toRefundOrder(payOrderDO);
            }
        }
        return basePayOrder;
    }

    @Mapping(target = "orderAmount", expression = "java(toMoney(payOrderDO.getOrderAmount(), payOrderDO.getCurrencyCode()))")
    PayOrder toPayOrder(PayOrderDO payOrderDO);

    @Mapping(target = "orderAmount", expression = "java(toMoney(payOrderDO.getOrderAmount(), payOrderDO.getCurrencyCode()))")
    RefundOrder toRefundOrder(PayOrderDO payOrderDO);

    default List<BasePayOrder> toBasePayOrder(List<PayOrderDO> doTypes) {
        List<BasePayOrder> entityTypes = new ArrayList<>();
        if (doTypes != null) {
            doTypes.forEach(doType -> entityTypes.add(toBasePayOrder(doType)));
        }
        return entityTypes;
    }

    default PayOrderDO toDo(BasePayOrder basePayOrder) {
        if (basePayOrder instanceof PayOrder) {
            return toDo((PayOrder) basePayOrder);
        } else if (basePayOrder instanceof RefundOrder) {
            return toDo((RefundOrder) basePayOrder);
        } else {
            return null;
        }
    }

    @Mapping(target = "currencyCode", expression = "java(toCurrencyCode(payOrder.getOrderAmount()))")
    @Mapping(target = "orderAmount", expression = "java(toAmountValue(payOrder.getOrderAmount()))")
    PayOrderDO toDo(PayOrder payOrder);

    @Mapping(target = "currencyCode", expression = "java(toCurrencyCode(refundOrder.getOrderAmount()))")
    @Mapping(target = "orderAmount", expression = "java(toAmountValue(refundOrder.getOrderAmount()))")
    PayOrderDO toDo(RefundOrder refundOrder);

    default List<PayOrderDO> toDo(List<BasePayOrder> doTypes) {
        List<PayOrderDO> entityTypes = new ArrayList<>();
        if (doTypes != null) {
            doTypes.forEach(doType -> entityTypes.add(toDo(doType)));
        }
        return entityTypes;
    }

}
