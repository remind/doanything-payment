package cn.doanything.framework.scheduler.repository.convertor;

import cn.doanything.commons.convertor.ReadWriteConvertor;
import cn.doanything.framework.scheduler.model.Task;
import cn.doanything.framework.scheduler.repository.mybatis.dataobject.SchedulerTaskDO;
import org.mapstruct.factory.Mappers;

/**
 * @author wxj
 * 2023/12/27
 */
public interface TaskDalConvertor extends ReadWriteConvertor<Task, SchedulerTaskDO> {
    TaskDalConvertor INSTANCE = Mappers.getMapper(TaskDalConvertor.class);
}
