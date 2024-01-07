package cn.doanything.basic.domain.mns;

import cn.doanything.basic.mns.Protocol;
import cn.doanything.commons.lang.Entity;
import lombok.Data;

import java.util.List;

/**
 * 通知渠道
 *
 * @author wxj
 * @since 2024-01-06
 */
@Data
public class NotifyChannel extends Entity {

    /**
     * 编码
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    /**
     * 支持的协议，如短信、邮件、push
     */
    private List<Protocol> protocol;

    /**
     * 是否为默认
     */
    private boolean isDefault;

    /**
     * 备注
     */
    private String memo;

}
