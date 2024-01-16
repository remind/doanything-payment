package cn.doanything.payment.domain.service;

import cn.doanything.commons.enums.SystemCodeEnums;
import cn.doanything.commons.lang.utils.IdGeneratorUtil;
import cn.doanything.framework.api.sequence.SequenceService;
import cn.doanything.payment.types.IdType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author wxj
 * 2024/1/15
 */
@Component
public class IdGeneratorDomainService {

    @Autowired
    private SequenceService sequenceService;

    public String genPaymentId(String memberId, IdType idType) {
        return sequenceService.getId(memberId, SystemCodeEnums.PAYMENT, idType);
    }

    public String genSubPayOrder(String paymentId, IdType idType) {
        return sequenceService.getIdByRouteId(IdGeneratorUtil.reverseIdGetDbRouteId(paymentId), SystemCodeEnums.PAYMENT, idType);
    }
}
