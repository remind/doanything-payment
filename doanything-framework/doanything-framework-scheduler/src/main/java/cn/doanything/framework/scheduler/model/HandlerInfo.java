package cn.doanything.framework.scheduler.model;

import cn.doanything.framework.scheduler.handler.SchedulerTaskHandler;
import lombok.Data;


@Data
public class HandlerInfo {

    /**
     * 任务类型
     */
    private String taskType;

    /**
     * 处理器类型
     */
    private HandlerType handlerType;

    /**
     * 任务处理器
     */
    private SchedulerTaskHandler schedulerTaskHandler;

}
