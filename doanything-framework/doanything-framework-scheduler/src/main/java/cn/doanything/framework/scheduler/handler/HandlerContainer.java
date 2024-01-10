package cn.doanything.framework.scheduler.handler;

import cn.doanything.framework.scheduler.model.HandlerInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 处理器容器
 */
@Slf4j
@Component
public class HandlerContainer {

    private final Map<String, HandlerInfo> commonTaskExecutorMap = new ConcurrentHashMap<>();

    @Autowired
    private Map<String, HandlerRegister> handlerRegisterMap;
    @Autowired
    private ApplicationContext applicationContext;

    /**
     * 新增处理器
     *
     * @param taskType
     * @param handlerInfo
     */
    public void addHandler(String taskType, HandlerInfo handlerInfo) {
        Assert.notNull(taskType, "任务类型不能为空");
        Assert.notNull(handlerInfo, "执行器信息不能为空");
        Assert.isTrue(!commonTaskExecutorMap.containsKey(taskType), "通用任务处理器注册重复,taskType=" + taskType);
        commonTaskExecutorMap.put(taskType, handlerInfo);
    }

    public HandlerInfo getHandler(String taskType) {
        return commonTaskExecutorMap.get(taskType);
    }

    public void initHandler() {
        Map<String, SchedulerTaskHandler> commonTaskHandlerBeanMap = applicationContext.getBeansOfType(SchedulerTaskHandler.class);
        for (Map.Entry<String, SchedulerTaskHandler> entry : commonTaskHandlerBeanMap.entrySet()) {
            cn.doanything.framework.scheduler.handler.annotation.TaskHandler taskHandler = AnnotatedElementUtils.findMergedAnnotation(entry.getValue().getClass(), cn.doanything.framework.scheduler.handler.annotation.TaskHandler.class);
            Assert.isTrue(taskHandler != null && StringUtils.isNotBlank(taskHandler.taskType()), "处理器注解和任务类型不能为空");
            handlerRegisterMap.get(HandlerRegister.HANDLER_REGISTER_PREFIX + taskHandler.handlerType().name()).registry(this, entry.getValue(), taskHandler);
        }
    }

}
