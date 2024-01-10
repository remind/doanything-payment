package cn.doanything.basic.infrastructure.persistence.mns.mapper;

import cn.doanything.basic.infrastructure.persistence.mns.dataobject.MessageContentDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 消息内容 Mapper 接口
 * </p>
 *
 * @author wxj
 * @since 2024-01-09
 */
@Mapper
public interface MessageContentMapper extends BaseMapper<MessageContentDO> {

}
