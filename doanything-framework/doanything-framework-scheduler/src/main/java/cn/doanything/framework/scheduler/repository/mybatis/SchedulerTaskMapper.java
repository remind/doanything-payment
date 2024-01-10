package cn.doanything.framework.scheduler.repository.mybatis;

import cn.doanything.framework.dal.mybatis.ext.ExtBaseMapper;
import cn.doanything.framework.scheduler.repository.mybatis.dataobject.SchedulerTaskDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 调度任务 Mapper 接口
 * </p>
 *
 * @author wxj
 * @since 2023-12-27
 */
@Mapper
public interface SchedulerTaskMapper extends ExtBaseMapper<SchedulerTaskDO> {

}
