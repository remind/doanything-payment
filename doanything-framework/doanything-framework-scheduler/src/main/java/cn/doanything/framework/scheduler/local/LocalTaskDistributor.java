package cn.doanything.framework.scheduler.local;


import cn.doanything.framework.scheduler.distribute.TaskDistributor;
import cn.doanything.framework.scheduler.model.ExecuteResult;
import cn.doanything.framework.scheduler.model.HandlerInfo;
import cn.doanything.framework.scheduler.model.LockModel;
import cn.doanything.framework.scheduler.model.Task;
import cn.doanything.framework.scheduler.properties.CommonTaskProperties;
import cn.doanything.framework.scheduler.repository.TaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.Assert;

import java.util.Date;

@Slf4j
public class LocalTaskDistributor implements TaskDistributor {

    private final TaskRepository commonTaskRepository;

    private final TransactionTemplate transactionTemplate;

    private final CommonTaskProperties properties;

    @Override
    public ExecuteResult process(Task commonTask, HandlerInfo handlerInfo) {
        LocalHandlerInfo localHandlerInfo = (LocalHandlerInfo) handlerInfo;
        try {
            if (LockModel.STRICT.equals(localHandlerInfo.getLockModel())) {
                return transactionTemplate.execute(status -> {
                    CommonTask lockCommonTask = commonTaskRepository.lock(commonTask.getTaskId());
                    return handle(lockCommonTask, localHandlerInfo);
                });
            } else {
                validate(commonTask);
                if (!commonTaskRepository.lockTask(commonTask)) {
                    return ExecuteResult.fail(commonTask.getTaskId(), "任务锁定失败");
                }
                return handle(commonTask, localHandlerInfo);
            }
        } catch (IllegalArgumentException e) {
            return ExecuteResult.fail(commonTask.getTaskId(), e.getMessage());
        } catch (Exception e) {
            log.error("任务处理异常", e);
            failHandle(commonTask, localHandlerInfo);
            return ExecuteResult.fail(commonTask.getTaskId(), "任务处理异常");
        }
    }

    /**
     * 校验能否处理
     *
     * @param commonTask
     */
    private void validate(CommonTask commonTask) {
        Assert.isTrue(TaskStatus.WAIT.equals(commonTask.getStatus())
                        || (TaskStatus.PROCESS.equals(commonTask.getStatus())
                        && commonTask.getStartExecuteTime() != null
                        && DateUtils.addMinutes(commonTask.getStartExecuteTime(), properties.getMaxExecuteMinutes()).compareTo(new Date()) < 0
                )
                , "该任务在处理中或者已经失败");
    }

    private ExecuteResult handle(CommonTask commonTask, LocalHandlerInfo handlerInfo) {
        boolean ret = handlerInfo.getTaskHandler().handle(commonTask.getTaskId(), commonTask.getTaskType()
                , commonTask.getBizId(), convertParam(commonTask.getTaskParam(), handlerInfo.getParamClazz()));
        if (ret) {
            commonTaskRepository.delete(commonTask.getTaskId());
            return ExecuteResult.success(commonTask.getTaskId());
        } else {
            failHandle(commonTask, handlerInfo);
            return ExecuteResult.fail(commonTask.getTaskId(), "任务执行失败");
        }
    }

    /**
     * 失败处理
     *
     * @param commonTask
     */
    private void failHandle(CommonTask commonTask, LocalHandlerInfo handlerInfo) {
        commonTask.setExecuteCount(commonTask.getExecuteCount() + 1);
        if (commonTask.getExecuteCount() >= properties.getMaxExecuteCount()) {
            log.error("任务执行次数达到最大执行次数，置为失败:{}", commonTask.getTaskId());

        }
    }
}
