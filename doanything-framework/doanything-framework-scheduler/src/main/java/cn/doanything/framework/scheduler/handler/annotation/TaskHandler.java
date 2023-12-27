package cn.doanything.framework.scheduler.handler.annotation;


import cn.doanything.framework.scheduler.model.HandlerType;
import cn.doanything.framework.scheduler.model.LockModel;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface TaskHandler {

    @AliasFor(annotation = Component.class)
    String value() default "";

    /**
     * 任务类型
     * @return
     */
    String taskType();

    /**
     * 处理器类型，默认为本地
     * @return
     */
    HandlerType handlerType() default HandlerType.LOCAL;

    /**
     * 锁模式，默认为宽松模式，仅在本地任务有效
     * @return
     */
    LockModel lockModel() default LockModel.KIND;
}
