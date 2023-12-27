package cn.doanything.framework.scheduler.distribute;


import cn.doanything.framework.scheduler.model.ExecuteResult;
import cn.doanything.framework.scheduler.model.HandlerInfo;
import cn.doanything.framework.scheduler.model.Task;

/**
 * 分发器
 */
public interface TaskDistributor {

    ExecuteResult process(Task task, HandlerInfo handlerInfo);

}
