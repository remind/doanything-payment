package cn.doanything.framework.scheduler.load;

import cn.doanything.framework.scheduler.distribute.DistributorAdapter;
import cn.doanything.framework.scheduler.model.ExecuteResult;
import cn.doanything.framework.scheduler.model.Task;
import cn.doanything.framework.scheduler.model.TaskStatus;
import cn.doanything.framework.scheduler.properties.CommonTaskProperties;
import cn.doanything.framework.scheduler.repository.TaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;


/**
 * 默认任务加载器
 */
@Slf4j
@Component
public class DefaultSchedulerTaskLoaderImpl implements SchedulerTaskLoader {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private CommonTaskProperties properties;

    @Autowired
    private DistributorAdapter distributorAdapter;

    @Autowired
    private ExecutorService executorService;

    @Override
    public int loadAndDistribute() {
        List<Task> tasks;
        int total = 0;
        long startTime = System.currentTimeMillis();
        List<Future<ExecuteResult>> executeResults = new ArrayList<>();
        while (true) {
            tasks = taskRepository.pageQueryWaitExecuteTaskIds(TaskStatus.WAIT, properties.getLoadCount());
            if (CollectionUtils.isEmpty(tasks)) {
                break;
            }
            executeResults.addAll(batchDistributeTask(tasks));
            total += tasks.size();
            if (tasks.size() < properties.getLoadCount()) {
                break;
            }
        }
        if (total > 0) {
            printExecuteResults(executeResults);
            log.info("结束任务处理，处理条数={},耗时={}", total, (System.currentTimeMillis() - startTime));
        }
        return total;
    }

    private void printExecuteResults(List<Future<ExecuteResult>> executeResults) {
        executeResults.forEach(executeResultFuture -> {
            try {
                ExecuteResult executeResult = executeResultFuture.get();
                log.info("任务执行结果,taskId={},status={},cost={},message={}"
                        , executeResult.getTaskId(), executeResult.isSuccess()
                        , (executeResult.getEndTime() - executeResult.getStartTime())
                        , executeResult.getMessage());
            } catch (Exception e) {
                log.error("打印执行结果异常", e);
            }
        });
    }

    private List<Future<ExecuteResult>> batchDistributeTask(List<Task> tasks) {
        List<Future<ExecuteResult>> executeResults = new ArrayList<>();
        for (Task task : tasks) {
            try {
                executeResults.add(executorService.submit(() -> {
                    ExecuteResult executeResult;
                    Long startTime = System.currentTimeMillis();
                    try {
                        executeResult = distributorAdapter.distribute(task);
                    } catch (Exception e) {
                        log.error("任务处理异常:" + task.getId(), e);
                        executeResult = ExecuteResult.fail(task.getId(), e.getMessage());
                    }
                    executeResult.setTime(startTime, System.currentTimeMillis());
                    return executeResult;

                }));
            } catch (Exception e) {
                log.error("任务处理异常,taskId=" + task.getId(), e);
            }
        }
        return executeResults;
    }

}
