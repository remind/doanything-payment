package cn.doanything.payment.infrastructure.persistence.convertor;

import cn.doanything.commons.convertor.BaseExpressionConvertor;
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
public interface PayOrderDalConvertor extends BaseExpressionConvertor {

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

    @Mapping(target = "amount", expression = "java(toMoney(payOrderDO.getAmount(), payOrderDO.getCurrencyCode()))")
    PayOrder toPayOrder(PayOrderDO payOrderDO);

    @Mapping(target = "amount", expression = "java(toMoney(payOrderDO.getAmount(), payOrderDO.getCurrencyCode()))")
    RefundOrder toRefundOrder(PayOrderDO payOrderDO);

    default List<BasePayOrder> toBasePayOrder(List<PayOrderDO> doTypes) {
        List<BasePayOrder> entityTypes = new ArrayList<>();
        if (doTypes != null) {
            doTypes.forEach(doType -> entityTypes.add(toBasePayOrder(doType)));
        }
        return entityTypes;
    }

    default PayOrderDO toDo(BasePayOrder basePayOrder) {
        PayOrderDO payOrderDO;
        if (basePayOrder instanceof PayOrder) {
            payOrderDO = toDo((PayOrder) basePayOrder);
            payOrderDO.setOrderType(PayOrderType.PAY.getCode());
        } else if (basePayOrder instanceof RefundOrder) {
            payOrderDO = toDo((RefundOrder) basePayOrder);
            payOrderDO.setOrderType(PayOrderType.REFUND.getCode());
        } else {
            payOrderDO = null;
        }
        return payOrderDO;
    }

    @Mapping(target = "currencyCode", expression = "java(toCurrencyCode(payOrder.getAmount()))")
    @Mapping(target = "amount", expression = "java(toAmountValue(payOrder.getAmount()))")
    PayOrderDO toDo(PayOrder payOrder);

    @Mapping(target = "currencyCode", expression = "java(toCurrencyCode(refundOrder.getAmount()))")
    @Mapping(target = "amount", expression = "java(toAmountValue(refundOrder.getAmount()))")
    PayOrderDO toDo(RefundOrder refundOrder);

    default List<PayOrderDO> toDo(List<BasePayOrder> doTypes) {
        List<PayOrderDO> entityTypes = new ArrayList<>();
        if (doTypes != null) {
            doTypes.forEach(doType -> entityTypes.add(toDo(doType)));
        }
        return entityTypes;
    }

}
