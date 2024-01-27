package cn.doanything.paycore.domain.service;

import cn.doanything.commons.enums.SystemCodeEnums;
import cn.doanything.commons.lang.utils.IdGeneratorUtil;
import cn.doanything.framework.api.sequence.SequenceService;
import cn.doanything.paycore.types.IdType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author wxj
 * 2024/1/15
 */
@Component
public class IdGeneratorService {

    @Autowired
    private SequenceService sequenceService;

    public String genPaymentId(String memberId, IdType idType) {
        return sequenceService.getId(memberId, SystemCodeEnums.PAYMENT, idType);
    }

    public String genIdByRelateId(String relateId, IdType idType) {
        return sequenceService.getIdByRouteId(IdGeneratorUtil.reverseIdGetDbRouteId(relateId), SystemCodeEnums.PAYMENT, idType);
    }

}
