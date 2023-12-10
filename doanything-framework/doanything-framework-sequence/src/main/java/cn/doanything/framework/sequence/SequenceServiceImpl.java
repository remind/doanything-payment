package cn.doanything.framework.sequence;

import cn.doanything.framework.api.sequence.SequenceService;

/**
 * 序列实现
 * @author wxj
 * 2023/12/10
 */
public class SequenceServiceImpl implements SequenceService {

    private final SequenceInnerService sequenceInnerService;

    @Override
    public Long getNext(String sequenceName) {
        return sequenceInnerService.next(sequenceName);
    }

    public SequenceServiceImpl(SequenceInnerService sequenceInnerService) {
        this.sequenceInnerService = sequenceInnerService;
    }
}
