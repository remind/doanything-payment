package cn.doanything.framework.scheduler.local;


import cn.doanything.framework.scheduler.distribute.TaskDistributor;
import cn.doanything.framework.scheduler.handler.HandlerRegister;
import cn.doanything.framework.scheduler.model.*;
import cn.doanything.framework.scheduler.properties.CommonTaskProperties;
import cn.doanything.framework.scheduler.repository.TaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.Date;

@Slf4j
@Component(TaskDistributor.TASK_DISTRIBUTE_PREFIX + "LOCAL")
public class LocalTaskDistributor implements TaskDistributor {

    @Autowired
    private TaskRepository commonTaskRepository;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Autowired
    private CommonTaskProperties properties;

    @Override
    public ExecuteResult process(Task task, HandlerInfo handlerInfo) {
        LocalHandlerInfo localHandlerInfo = (LocalHandlerInfo) handlerInfo;
        try {
            if (LockModel.STRICT.equals(localHandlerInfo.getLockModel())) {
                return transactionTemplate.execute(status -> {
                    Task lockTask = commonTaskRepository.lock(task.getId());
                    return handle(lockTask, localHandlerInfo);
                });
            } else {
                validate(task);
                if (!commonTaskRepository.lockTask(task)) {
                    return ExecuteResult.fail(task.getId(), "任务锁定失败");
                }
                return handle(task, localHandlerInfo);
            }
        } catch (IllegalArgumentException e) {
            return ExecuteResult.fail(task.getId(), e.getMessage());
        } catch (Exception e) {
            log.error("任务处理异常", e);
            failHandle(task, localHandlerInfo);
            return ExecuteResult.fail(task.getId(), "任务处理异常");
        }
    }

    /**
     * 校验能否处理
     *
     * @param task
     */
    private void validate(Task task) {
        Assert.isTrue(TaskStatus.WAIT.equals(task.getStatus())
                        || (TaskStatus.PROCESS.equals(task.getStatus())
                        && task.getStartExecuteTime() != null
                        && task.getStartExecuteTime().plusMinutes(properties.getMaxExecuteMinutes()).isBefore(LocalDateTime.now())
                )
                , "该任务在处理中或者已经失败");
    }

    private ExecuteResult handle(Task task, LocalHandlerInfo handlerInfo) {
        boolean ret = handlerInfo.getSchedulerTaskHandler().handle(task.getId(), task.getType()
                , task.getBizId(), task.getParam());
        if (ret) {
            commonTaskRepository.delete(task.getId());
            return ExecuteResult.success(task.getId());
        } else {
            failHandle(task, handlerInfo);
            return ExecuteResult.fail(task.getId(), "任务执行失败");
        }
    }

    /**
     * 失败处理
     *
     * @param task
     */
    private void failHandle(Task task, LocalHandlerInfo handlerInfo) {
        task.setExecuteCount(task.getExecuteCount() + 1);
        if (task.getExecuteCount() >= properties.getMaxExecuteCount()) {
            log.error("任务执行次数达到最大执行次数，置为失败:{}", task.getId());
            task.setStatus(TaskStatus.FAIL);
            handlerInfo.getSchedulerTaskHandler().failHandle(task.getId(), task.getType()
                    , task.getBizId(), task.getParam());
        } else {
            task.setStatus(TaskStatus.WAIT);
            task.setNextExecuteTime(task.getNextExecuteTime().plusMinutes(properties.getExecuteIntervalSeconds()));
        }
        commonTaskRepository.save(task);
    }

}
