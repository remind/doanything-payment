package cn.doanything.framework.sequence;

import cn.doanything.commons.enums.BizIdType;
import cn.doanything.commons.enums.SystemCodeEnums;
import cn.doanything.commons.lang.utils.IdGeneratorUtil;
import cn.doanything.framework.api.sequence.SequenceService;

/**
 * 序列实现
 *
 * @author wxj
 * 2023/12/10
 */
public class SequenceServiceImpl implements SequenceService {

    private final SequenceInnerService sequenceInnerService;

    @Override
    public Long getNext(String sequenceName) {
        return sequenceInnerService.next(sequenceName);
    }

    @Override
    public String getId(String memberId, SystemCodeEnums systemCodeEnums, BizIdType idType) {
        return IdGeneratorUtil.getId(systemCodeEnums.getCode(), idType.getBizTypeCode()
                , IdGeneratorUtil.getDbRouteIdByMemberId(memberId), getNext(idType.getSeqName()));
    }

    @Override
    public String getIdByRouteId(String routeId, SystemCodeEnums systemCodeEnums, BizIdType idType) {
        return IdGeneratorUtil.getId(systemCodeEnums.getCode(), idType.getBizTypeCode()
                , routeId, getNext(idType.getSeqName()));
    }

    public SequenceServiceImpl(SequenceInnerService sequenceInnerService) {
        this.sequenceInnerService = sequenceInnerService;
    }
}
