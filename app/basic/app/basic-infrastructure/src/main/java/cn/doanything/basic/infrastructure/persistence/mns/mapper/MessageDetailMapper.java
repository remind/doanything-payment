package cn.doanything.basic.infrastructure.persistence.mns.mapper;

import cn.doanything.basic.infrastructure.persistence.mns.dataobject.MessageDetailDO;
import cn.doanything.framework.dal.mybatis.ext.ExtBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 消息明细 Mapper 接口
 * </p>
 *
 * @author wxj
 * @since 2024-01-09
 */
@Mapper
public interface MessageDetailMapper extends ExtBaseMapper<MessageDetailDO> {

}
