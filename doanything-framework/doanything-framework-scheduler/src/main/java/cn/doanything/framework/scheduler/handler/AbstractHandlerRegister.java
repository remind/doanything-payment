package cn.doanything.framework.scheduler.handler;

import org.springframework.core.ResolvableType;

import java.lang.reflect.Type;

/**
 * 处理器注册器父类
 * Created by 2023/12/13
 *
 * @author wxj
 * @version V1.0
 * @description:
 */
public abstract class AbstractHandlerRegister implements HandlerRegister {

    /**
     * 获取参数类型
     *
     * @param handler
     * @return
     */
    protected Class<?> getParamClass(SchedulerTaskHandler<?> handler) {
        return (Class<?>) ResolvableType.forType(getGenericClassType(handler)).getGeneric(0).getType();
    }

    /**
     * 获取带泛型的类型
     *
     * @param handler
     * @return
     */
    private Type getGenericClassType(SchedulerTaskHandler<?> handler) {
        Type result = getTypeFromInterface(handler);
        return result != null ? result : getTypeFromClass(handler);
    }

    /**
     * 从父接口获取
     *
     * @param handler
     * @return
     */
    private Type getTypeFromInterface(SchedulerTaskHandler<?> handler) {
        for (Type type : handler.getClass().getGenericInterfaces()) {
            if (ResolvableType.forType(SchedulerTaskHandler.class).isAssignableFrom(ResolvableType.forType(type))) {
                return type;
            }
        }
        return null;
    }

    /**
     * 从父类获取
     * 说明：这里理应做一下判断，判断父类是否有实现接口CommTaskHandler
     * 但是在父类的泛型如果也有extends关系，判断就会失效，暂时移除，即abstract ParentTaskHandler<T extends TaskObject> implements CommTaskHandler<T>
     * 判断代码：&& ResolvableType.forType(CommonTaskHandler.class).isAssignableFrom(ResolvableType.forType(handler.getClass().getGenericSuperclass()))
     *
     * @param handler
     * @return
     */
    private Type getTypeFromClass(SchedulerTaskHandler<?> handler) {
        return handler.getClass().getGenericSuperclass() != null
                ? handler.getClass().getGenericSuperclass() : null;
    }

}
