package cn.doanything.framework.scheduler.local;

import cn.doanything.framework.scheduler.handler.AbstractHandlerRegister;
import cn.doanything.framework.scheduler.handler.SchedulerTaskHandler;
import cn.doanything.framework.scheduler.handler.HandlerContainer;
import cn.doanything.framework.scheduler.handler.annotation.TaskHandler;

/**
 * 本地处理器注册
 */
public class LocalHandlerRegister extends AbstractHandlerRegister {

    @SuppressWarnings("unchecked")
    @Override
    public void registry(HandlerContainer handlerContainer, SchedulerTaskHandler<?> commonSchedulerTaskHandler, TaskHandler taskHandler) {
        LocalHandlerInfo handlerInfo = new LocalHandlerInfo();
        handlerInfo.setTaskType(taskHandler.taskType());
        handlerInfo.setHandlerType(taskHandler.handlerType());
        handlerInfo.setSchedulerTaskHandler((SchedulerTaskHandler<Object>) commonSchedulerTaskHandler);
        handlerInfo.setParamClazz(getParamClass(commonSchedulerTaskHandler));
        handlerInfo.setLockModel(taskHandler.lockModel());
        handlerContainer.addHandler(taskHandler.taskType(), handlerInfo);
    }
}
