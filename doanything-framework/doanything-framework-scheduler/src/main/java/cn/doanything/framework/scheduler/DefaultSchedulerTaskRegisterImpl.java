package cn.doanything.framework.scheduler;

import cn.doanything.framework.scheduler.model.Task;
import cn.doanything.framework.scheduler.repository.TaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author wxj
 * 2023/12/29
 */
@Slf4j
@Component
public class DefaultSchedulerTaskRegisterImpl implements SchedulerTaskRegister {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public String registryTask(String taskType, String bizId) {
        return registryTask(taskType, bizId, null, null);
    }

    @Override
    public String registryTask(String taskType, String bizId, LocalDateTime nextExecuteTime) {
        return registryTask(taskType, bizId, nextExecuteTime, null);
    }

    @Override
    public String registryTask(String taskType, String bizId, String param) {
        return registryTask(taskType, bizId, null, param);
    }

    @Override
    public String registryTask(String taskType, String bizId, LocalDateTime nextExecuteTime, String param) {
        Task task = new Task(taskType, bizId, nextExecuteTime, param);
        String taskId = taskRepository.store(task);
        log.info("注册任务成功,taskId={}", taskId);
        return taskId;
    }
}
