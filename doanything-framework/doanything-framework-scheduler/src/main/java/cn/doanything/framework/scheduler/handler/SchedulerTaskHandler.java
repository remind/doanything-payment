package cn.doanything.framework.scheduler.handler;

/**
 * 任务处理器
 * @param <T>
 */
public interface SchedulerTaskHandler<T> {

    /**
     * 任务处理
     * @param taskId    任务ID
     * @param taskType  任务类型
     * @param bizId     业务ID
     * @param taskParam 任务参数
     * @return
     */
    boolean handle(Long taskId, String taskType, String bizId, T taskParam);

    /**
     * 任务达到最大执行次数后还是失败时调用
     * @param taskId    任务ID
     * @param taskType  任务类型
     * @param bizId     业务ID
     * @param taskParam 任务参数
     * @return
     */
    void failHandle(Long taskId, String taskType, String bizId, T taskParam);
}
