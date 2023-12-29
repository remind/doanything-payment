package cn.doanything.framework.scheduler;

import cn.doanything.framework.scheduler.model.Task;
import cn.doanything.framework.scheduler.repository.TaskRepository;
import lombok.extern.slf4j.Slf4j;

/**
 * @author wxj
 * 2023/12/29
 */
@Slf4j
public class DefaultCommTaskRegisterImpl implements CommTaskRegister {

    private TaskRepository taskRepository;

    @Override
    public String registryTask(String taskType, String bizId) {
        Task task = new Task(taskType, bizId);
        String taskId = taskRepository.store(task);
        log.info("注册任务成功,taskId={}", taskId);
        return taskId;
    }
}
