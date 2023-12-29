package cn.doanything.framework.scheduler.repository;

import cn.doanything.framework.scheduler.model.Task;
import cn.doanything.framework.scheduler.model.TaskStatus;
import cn.doanything.framework.scheduler.repository.convertor.TaskDalConvertor;
import cn.doanything.framework.scheduler.repository.mybatis.SchedulerTaskMapper;
import cn.doanything.framework.scheduler.repository.mybatis.dataobject.SchedulerTaskDO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author wxj
 * 2023/12/27
 */
public class TaskRepository {

    @Autowired
    private SchedulerTaskMapper taskMapper;

    private final TaskDalConvertor dalConvertor = TaskDalConvertor.INSTANCE;

    /**
     * 新增任务
     *
     * @param task
     * @return
     */
    public String store(Task task) {
        taskMapper.insert(dalConvertor.toDo(task));
        return task.getId();
    }

    /**
     * 根据任务ID锁定任务
     *
     * @param taskId
     * @return
     */
    public Task load(String taskId) {
        return dalConvertor.toEntity(taskMapper.selectById(taskId));
    }

    public Task lock(String taskId) {
        return dalConvertor.toEntity(taskMapper.lockById(taskId));
    }

    /**
     * 保存
     *
     * @param task
     * @return
     */
    public boolean save(Task task) {
        return taskMapper.updateById(dalConvertor.toDo(task)) == 1;
    }

    /**
     * 根据任务ID删除
     *
     * @param taskId
     * @return
     */
    public boolean delete(String taskId) {
        return taskMapper.deleteById(taskId) == 1;
    }

    /**
     * 锁定任务
     *
     * @param task
     * @return
     */
    public boolean lockTask(Task task) {
        return false;
    }


    public List<Task> pageQueryWaitExecuteTaskIds(TaskStatus taskStatus, int count) {
        return  dalConvertor.toEntity(taskMapper.selectList(new Page<>(1, count), new LambdaQueryWrapper<SchedulerTaskDO>()
                .eq(SchedulerTaskDO::getStatus, taskStatus.getCode())
                .orderByAsc(SchedulerTaskDO::getNextExecuteTime)
        ));
    }
}
