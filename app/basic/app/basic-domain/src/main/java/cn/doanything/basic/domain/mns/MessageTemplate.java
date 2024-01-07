package cn.doanything.basic.domain.mns;

import cn.doanything.commons.enums.EnableEnum;
import cn.doanything.commons.lang.Entity;
import lombok.Data;

/**
 * 消息模板
 * @author wxj
 * 2024/1/6
 */
@Data
public class MessageTemplate extends Entity {

    /**
     * 场景编码
     */
    private String sceneCode;

    /**
     * 协议，如短信、邮件、push
     */
    private String protocol;

    /**
     * 状态，启用、禁用
     */
    private EnableEnum status;

    /**
     * 主题，邮箱时必须有
     */
    private String subject;

    /**
     * 模板内容
     */
    private String content;

    /**
     * 备注
     */
    private String memo;
}
