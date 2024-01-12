package cn.doanything.basic.infrastructure.persistence.mns.repository;

import cn.doanything.basic.domain.mns.MessageTemplate;
import cn.doanything.basic.domain.mns.repository.MessageTemplateRepository;
import cn.doanything.basic.infrastructure.persistence.mns.convertor.MessageTemplateDalConvertor;
import cn.doanything.basic.infrastructure.persistence.mns.dataobject.MessageTemplateDO;
import cn.doanything.basic.infrastructure.persistence.mns.mapper.MessageTemplateMapper;
import cn.doanything.basic.types.mns.Protocol;
import cn.doanything.commons.enums.EnableEnum;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author wxj
 * 2024/1/8
 */
@Repository
public class MessageTemplateRepositoryImpl implements MessageTemplateRepository {

    @Autowired
    private MessageTemplateDalConvertor dalConvertor;

    @Autowired
    private MessageTemplateMapper mapper;

    @Override
    public MessageTemplate findBySceneCodeAndProtocol(String sceneCode, Protocol protocol) {
        LambdaQueryWrapper<MessageTemplateDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(MessageTemplateDO::getSceneCode, sceneCode)
                .eq(MessageTemplateDO::getProtocol, protocol.getCode())
                .eq(MessageTemplateDO::getStatus, EnableEnum.ENABLE.getCode())
        ;
        return dalConvertor.toEntity(mapper.selectOne(wrapper));
    }
}
