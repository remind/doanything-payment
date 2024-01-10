package cn.doanything.framework.scheduler.local;

import cn.doanything.framework.scheduler.handler.HandlerRegister;
import cn.doanything.framework.scheduler.handler.SchedulerTaskHandler;
import cn.doanything.framework.scheduler.handler.HandlerContainer;
import cn.doanything.framework.scheduler.handler.annotation.TaskHandler;
import org.springframework.stereotype.Component;

/**
 * 本地处理器注册
 */
@Component(HandlerRegister.HANDLER_REGISTER_PREFIX + "LOCAL")
public class LocalHandlerRegister implements HandlerRegister {

    @Override
    public void registry(HandlerContainer handlerContainer, SchedulerTaskHandler commonSchedulerTaskHandler, TaskHandler taskHandler) {
        LocalHandlerInfo handlerInfo = new LocalHandlerInfo();
        handlerInfo.setTaskType(taskHandler.taskType());
        handlerInfo.setHandlerType(taskHandler.handlerType());
        handlerInfo.setSchedulerTaskHandler(commonSchedulerTaskHandler);
        handlerInfo.setLockModel(taskHandler.lockModel());
        handlerContainer.addHandler(taskHandler.taskType(), handlerInfo);
    }
}
