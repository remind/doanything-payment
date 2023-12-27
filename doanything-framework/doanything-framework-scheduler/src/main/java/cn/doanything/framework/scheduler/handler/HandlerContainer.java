package cn.doanything.framework.scheduler.handler;

import cn.doanything.framework.scheduler.model.HandlerInfo;
import cn.doanything.framework.scheduler.model.HandlerType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.util.Assert;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 处理器容器
 */
@Slf4j
public class HandlerContainer {

    private final Map<String, HandlerInfo> commonTaskExecutorMap = new ConcurrentHashMap<>();
    private final Map<String, HandlerRegister> handlerRegisterMap = new ConcurrentHashMap<>();
    private final  ApplicationContext applicationContext;

    /**
     * 新增处理器
     * @param taskType
     * @param handlerInfo
     */
    public void addHandler(String taskType, HandlerInfo handlerInfo) {
        Assert.notNull(taskType, "任务类型不能为空");
        Assert.notNull(handlerInfo, "执行器信息不能为空");
        Assert.isTrue(!commonTaskExecutorMap.containsKey(taskType), "通用任务处理器注册重复,taskType=" + taskType);
        log.info("注册处理器,taskType={},参数类型={}", taskType, handlerInfo.getParamClazz().getName());
        commonTaskExecutorMap.put(taskType, handlerInfo);
    }

    /**
     * 新增处理器注册器
     * @param handlerType
     * @param handlerRegister
     */
    public void addHandlerRegister(HandlerType handlerType, HandlerRegister handlerRegister) {
        handlerRegisterMap.put(handlerType.name(), handlerRegister);
    }

    public HandlerInfo getHandler(String taskType) {
        return commonTaskExecutorMap.get(taskType);
    }

    @SuppressWarnings("rawtypes" /* because CommonTaskHandler<?> is always CommonTaskHandler<Object> */)
    public void initHandler() {
        Map<String, SchedulerTaskHandler> commonTaskHandlerBeanMap = applicationContext.getBeansOfType(SchedulerTaskHandler.class);
        for (Map.Entry<String, SchedulerTaskHandler> entry : commonTaskHandlerBeanMap.entrySet()) {
            cn.doanything.framework.scheduler.handler.annotation.TaskHandler taskHandler = AnnotatedElementUtils.findMergedAnnotation(entry.getValue().getClass(), cn.doanything.framework.scheduler.handler.annotation.TaskHandler.class);
            Assert.isTrue(taskHandler != null && StringUtils.isNotBlank(taskHandler.taskType()), "处理器注解和任务类型不能为空");
            handlerRegisterMap.get(taskHandler.handlerType().name()).registry(this, entry.getValue(), taskHandler);
        }
    }

    public HandlerContainer(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
}
