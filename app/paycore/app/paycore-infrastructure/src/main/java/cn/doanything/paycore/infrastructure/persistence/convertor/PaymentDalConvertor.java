package cn.doanything.paycore.infrastructure.persistence.convertor;

import cn.doanything.commons.convertor.WriteConvertor;
import cn.doanything.paycore.domain.BasePayment;
import cn.doanything.paycore.domain.instant.InstantPayment;
import cn.doanything.paycore.infrastructure.convertor.EnumsConvertor;
import cn.doanything.paycore.infrastructure.persistence.dataobject.PaymentDO;
import cn.doanything.paycore.types.PaymentType;
import org.mapstruct.Mapper;

/**
 * @author wxj
 * 2024/1/18
 */
@Mapper(componentModel = "spring", uses = {EnumsConvertor.class})
public interface PaymentDalConvertor extends WriteConvertor<BasePayment, PaymentDO> {

    default BasePayment toPayment(PaymentDO paymentDO) {
        BasePayment payment = null;
        if (paymentDO != null) {
            if (PaymentType.INSTANT.getCode().equals(paymentDO.getPaymentType())) {
                payment = toInstantPayment(paymentDO);
            }
        }
        return payment;
    }

    InstantPayment toInstantPayment(PaymentDO paymentDO);
}
