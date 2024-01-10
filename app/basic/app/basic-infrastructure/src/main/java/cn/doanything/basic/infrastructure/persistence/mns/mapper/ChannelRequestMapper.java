package cn.doanything.basic.infrastructure.persistence.mns.mapper;

import cn.doanything.basic.infrastructure.persistence.mns.dataobject.ChannelRequestDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 消息渠道发送记录 Mapper 接口
 * </p>
 *
 * @author wxj
 * @since 2024-01-08
 */
@Mapper
public interface ChannelRequestMapper extends BaseMapper<ChannelRequestDO> {

}
