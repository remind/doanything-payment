package cn.doanything.framework.scheduler.handler;


import cn.doanything.framework.scheduler.handler.annotation.TaskHandler;

/**
 * 处理器注册器
 * Created by 2023/12/13
 *
 * @author wxj
 * @version V1.0
 * @description:
 */
public interface HandlerRegister {

    String HANDLER_REGISTER_PREFIX = "HandlerRegisterPrefix";

    void registry(HandlerContainer handlerContainer, SchedulerTaskHandler commonSchedulerTaskHandler, TaskHandler taskHandler);

}
