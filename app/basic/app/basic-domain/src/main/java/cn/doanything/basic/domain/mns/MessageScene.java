package cn.doanything.basic.domain.mns;

import cn.doanything.commons.enums.EnableEnum;
import cn.doanything.commons.lang.Entity;
import lombok.Data;

/**
 * 消息场景
 * @author wxj
 * 2024/1/6
 */
@Data
public class MessageScene extends Entity {

    /**
     * 编码
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    /**
     * 状态，启用、禁用
     */
    private EnableEnum status;

    /**
     * 备注
     */
    private String memo;
}
