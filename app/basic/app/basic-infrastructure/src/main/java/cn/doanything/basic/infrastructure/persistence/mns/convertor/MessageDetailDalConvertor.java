package cn.doanything.basic.infrastructure.persistence.mns.convertor;

import cn.doanything.basic.domain.mns.MessageDetail;
import cn.doanything.basic.infrastructure.persistence.mns.dataobject.MessageContentDO;
import cn.doanything.basic.infrastructure.persistence.mns.dataobject.MessageDetailDO;
import cn.doanything.basic.mns.content.AuthCode;
import cn.doanything.commons.convertor.ReadWriteConvertor;
import cn.hutool.json.JSONUtil;
import org.mapstruct.Mapper;

/**
 * @author wxj
 * 2024/1/8
 */
@Mapper(componentModel = "spring", uses = {EnumsConvertor.class})
public interface MessageDetailDalConvertor extends ReadWriteConvertor<MessageDetail, MessageDetailDO> {

    default Object toContent(MessageDetail messageDetail, String content) {
        switch (messageDetail.getMessageType()) {
            case AUTH_CODE:
                return JSONUtil.toBean(content, AuthCode.class);
            default:
                return content;
        }
    }

    default MessageContentDO toContent(MessageDetail messageDetail) {
        MessageContentDO messageContentDO = new MessageContentDO();
        messageContentDO.setMessageId(messageDetail.getId());
        switch (messageDetail.getMessageType()) {
            case AUTH_CODE:
                messageContentDO.setContent(JSONUtil.parse(messageDetail.getContent()).toString());
                break;
            default:
                messageContentDO.setContent(String.valueOf(messageDetail.getContent()));
        }
        return messageContentDO;
    }

}
