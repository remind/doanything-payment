package cn.doanything.basic.infrastructure.persistence.mns.repository;

import cn.doanything.basic.domain.mns.MessageDetail;
import cn.doanything.basic.domain.mns.repository.MessageDetailRepository;
import cn.doanything.basic.infrastructure.persistence.mns.convertor.MessageDetailDalConvertor;
import cn.doanything.basic.infrastructure.persistence.mns.dataobject.MessageDetailDO;
import cn.doanything.basic.infrastructure.persistence.mns.mapper.MessageContentMapper;
import cn.doanything.basic.infrastructure.persistence.mns.mapper.MessageDetailMapper;
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

    @Override
    public MessageDetail load(String id) {
        MessageDetail messageDetail = dalConvertor.toEntity(mapper.selectById(id));
        if (messageDetail != null) {
            messageDetail.setContent(dalConvertor.toContent(messageDetail, contentMapper.selectById(id).getContent()));
        }
        return messageDetail;
    }

    @Override
    public void store(MessageDetail messageDetail) {
        mapper.insert(dalConvertor.toDo(messageDetail));
    }

    @Override
    public void reStore(MessageDetail messageDetail) {
        mapper.updateById(dalConvertor.toDo(messageDetail));
    }

    @Override
    public List<MessageDetail> findBySceneCodeAndBizId(String sceneCode, String batchId, String recipient) {
        LambdaQueryWrapper<MessageDetailDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(MessageDetailDO::getSceneCode, sceneCode)
                .eq(MessageDetailDO::getBatchId, batchId)
                .eq(MessageDetailDO::getRecipient, recipient)
        ;
        return dalConvertor.toEntity(mapper.selectList(queryWrapper));
    }
}
