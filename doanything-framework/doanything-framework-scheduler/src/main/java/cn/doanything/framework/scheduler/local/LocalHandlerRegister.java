package cn.doanything.framework.scheduler.local;

import cn.doanything.framework.scheduler.handler.AbstractHandlerRegister;
import cn.doanything.framework.scheduler.handler.SchedulerTaskHandler;
import cn.doanything.framework.scheduler.handler.HandlerContainer;

/**
 * 本地处理器注册
 */
public class LocalHandlerRegister extends AbstractHandlerRegister {

    @Override
    public void registry(HandlerContainer handlerContainer, SchedulerTaskHandler<?> commonSchedulerTaskHandler, cn.doanything.framework.scheduler.handler.annotation.TaskHandler taskHandler) {

    }
}
