package cn.doanything.framework.scheduler.distribute;


import cn.doanything.framework.scheduler.handler.HandlerContainer;
import cn.doanything.framework.scheduler.model.ExecuteResult;
import cn.doanything.framework.scheduler.model.HandlerInfo;
import cn.doanything.framework.scheduler.model.HandlerType;
import cn.doanything.framework.scheduler.model.Task;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 分发器适配器
 */
public class DistributorAdapter {

    private final Map<String, TaskDistributor> taskDistributorMap = new ConcurrentHashMap<>();

    private final HandlerContainer handlerContainer;

    /**
     * 调用对应的分发器进行任务分发
     * @param task
     * @return
     */
    public ExecuteResult distribute(Task task) {
        HandlerInfo handlerInfo = handlerContainer.getHandler(task.getType());
        return taskDistributorMap.get(handlerInfo.getHandlerType().name()).process(task
                , handlerInfo);
    }

    /**
     * 注册分发器
     * @param handlerType
     * @param taskDistributor
     */
    public void registry(HandlerType handlerType, TaskDistributor taskDistributor) {
        taskDistributorMap.put(handlerType.name(), taskDistributor);
    }

    public DistributorAdapter(HandlerContainer handlerContainer) {
        this.handlerContainer = handlerContainer;
    }
}
