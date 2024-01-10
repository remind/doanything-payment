package cn.doanything.framework.scheduler.distribute;


import cn.doanything.framework.scheduler.handler.HandlerContainer;
import cn.doanything.framework.scheduler.model.ExecuteResult;
import cn.doanything.framework.scheduler.model.HandlerInfo;
import cn.doanything.framework.scheduler.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 分发器适配器
 */
@Component
public class DistributorAdapter {

    @Autowired
    private Map<String, TaskDistributor> taskDistributorMap;

    @Autowired
    private HandlerContainer handlerContainer;

    /**
     * 调用对应的分发器进行任务分发
     *
     * @param task
     * @return
     */
    public ExecuteResult distribute(Task task) {
        HandlerInfo handlerInfo = handlerContainer.getHandler(task.getType());
        return taskDistributorMap.get(TaskDistributor.TASK_DISTRIBUTE_PREFIX + handlerInfo.getHandlerType().name()).process(task
                , handlerInfo);
    }

}
