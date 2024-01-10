package cn.doanything.framework.scheduler.repository.convertor;

import cn.doanything.commons.convertor.GlobalTypeConvertor;
import cn.doanything.commons.convertor.ReadWriteConvertor;
import cn.doanything.commons.lang.utils.EnumUtil;
import cn.doanything.framework.scheduler.model.Task;
import cn.doanything.framework.scheduler.model.TaskStatus;
import cn.doanything.framework.scheduler.repository.mybatis.dataobject.SchedulerTaskDO;
import org.mapstruct.Mapper;

/**
 * @author wxj
 * 2023/12/27
 */
@Mapper(componentModel = "spring", uses = GlobalTypeConvertor.class)
public interface TaskDalConvertor extends ReadWriteConvertor<Task, SchedulerTaskDO> {

    default TaskStatus getByCode(String code) {
        return EnumUtil.getByCode(TaskStatus.class, code);
    }
}
