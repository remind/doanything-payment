package cn.doanything.basic.infrastructure.persistence.mns.mapper;

import cn.doanything.basic.infrastructure.persistence.mns.dataobject.MessageSceneDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 消息场景 Mapper 接口
 * </p>
 *
 * @author wxj
 * @since 2024-01-06
 */
@Mapper
public interface MessageSceneMapper extends BaseMapper<MessageSceneDO> {

}
