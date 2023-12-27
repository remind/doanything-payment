package cn.doanything.framework.scheduler.handler;


/**
 * 处理器注册器
 * Created by 2023/12/13
 *
 * @author wxj
 * @version V1.0
 * @description:
 */
public interface HandlerRegister {

    void registry(HandlerContainer handlerContainer, SchedulerTaskHandler<?> commonSchedulerTaskHandler, cn.doanything.framework.scheduler.handler.annotation.TaskHandler taskHandler);

}
