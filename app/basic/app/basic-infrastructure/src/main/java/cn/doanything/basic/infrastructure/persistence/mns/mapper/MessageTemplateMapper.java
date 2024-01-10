package cn.doanything.basic.infrastructure.persistence.mns.mapper;

import cn.doanything.basic.infrastructure.persistence.mns.dataobject.MessageTemplateDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 消息模板 Mapper 接口
 * </p>
 *
 * @author wxj
 * @since 2024-01-06
 */
@Mapper
public interface MessageTemplateMapper extends BaseMapper<MessageTemplateDO> {

}
