package cn.doanything.basic.infrastructure.persistence.mns.repository;

import cn.doanything.basic.domain.IdType;
import cn.doanything.basic.domain.mns.MessageDetail;
import cn.doanything.basic.domain.mns.repository.MessageDetailRepository;
import cn.doanything.basic.infrastructure.persistence.mns.convertor.MessageDetailDalConvertor;
import cn.doanything.basic.infrastructure.persistence.mns.dataobject.MessageContentDO;
import cn.doanything.basic.infrastructure.persistence.mns.dataobject.MessageDetailDO;
import cn.doanything.basic.infrastructure.persistence.mns.mapper.MessageContentMapper;
import cn.doanything.basic.infrastructure.persistence.mns.mapper.MessageDetailMapper;
import cn.doanything.commons.enums.SystemCodeEnums;
import cn.doanything.framework.api.sequence.SequenceService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wxj
 * 2024/1/8
 */
@Repository
public class MessageDetailRepositoryImpl implements MessageDetailRepository {

    @Autowired
    private MessageDetailMapper mapper;

    @Autowired
    private MessageContentMapper contentMapper;

    @Autowired
    private MessageDetailDalConvertor dalConvertor;

    @Autowired
    private SequenceService sequenceService;

    @Override
    public MessageDetail load(String id) {
        MessageDetail messageDetail = dalConvertor.toEntity(mapper.selectOne(getIdWrapper(id)));
        fillContent(messageDetail);
        return messageDetail;
    }

    @Override
    public MessageDetail loadByRequestId(String requestId) {
        Wrapper<MessageDetailDO> wrapper = new LambdaQueryWrapper<MessageDetailDO>().eq(MessageDetailDO::getRequestId, requestId);
        MessageDetail messageDetail = dalConvertor.toEntity(mapper.selectOne(wrapper));
        fillContent(messageDetail);
        return messageDetail;
    }

    @Override
    public void store(MessageDetail messageDetail) {
        messageDetail.setMessageId(getId(messageDetail.getMemberId()));
        mapper.insert(dalConvertor.toDo(messageDetail));
        contentMapper.insert(dalConvertor.toContent(messageDetail));
    }

    @Override
    public void reStore(MessageDetail messageDetail) {
        mapper.update(dalConvertor.toDo(messageDetail), getIdWrapper(messageDetail.getMessageId()));
        contentMapper.update(dalConvertor.toContent(messageDetail), getContentIdWrapper(messageDetail.getMessageId()));
    }

    @Override
    public List<MessageDetail> findBySceneCodeAndBizId(String sceneCode, String batchId, String recipient) {
        LambdaQueryWrapper<MessageDetailDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(MessageDetailDO::getSceneCode, sceneCode)
                .eq(MessageDetailDO::getBatchId, batchId)
                .eq(MessageDetailDO::getRecipient, recipient)
        ;
        List<MessageDetail> messageDetails = dalConvertor.toEntity(mapper.selectList(queryWrapper));
        if (messageDetails != null) {
            for (MessageDetail messageDetail : messageDetails) {
                fillContent(messageDetail);
            }
        }
        return messageDetails;
    }

    private void fillContent(MessageDetail messageDetail) {
        if (messageDetail != null) {
            messageDetail.setContent(dalConvertor.toContent(messageDetail, contentMapper.selectOne(getContentIdWrapper(messageDetail.getMessageId())).getContent()));
        }
    }

    private Wrapper<MessageDetailDO> getIdWrapper(String id) {
        return new LambdaQueryWrapper<MessageDetailDO>().eq(MessageDetailDO::getMessageId, id);
    }

    private Wrapper<MessageContentDO> getContentIdWrapper(String id) {
        return new LambdaQueryWrapper<MessageContentDO>().eq(MessageContentDO::getMessageId, id);
    }

    private String getId(String memberId) {
        return sequenceService.getId(memberId, SystemCodeEnums.BASIC, IdType.MNS_MESSAGE_DETAIL);
    }
}
