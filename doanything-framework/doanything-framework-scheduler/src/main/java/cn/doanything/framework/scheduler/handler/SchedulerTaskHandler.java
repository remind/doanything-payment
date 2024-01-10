package cn.doanything.framework.scheduler.handler;

/**
 * 任务处理器
 * @param
 */
public interface SchedulerTaskHandler {

    /**
     * 任务处理
     * @param taskId    任务ID
     * @param taskType  任务类型
     * @param bizId     业务ID
     * @param param 任务参数
     * @return
     */
    boolean handle(String taskId, String taskType, String bizId, String param);

    /**
     * 任务达到最大执行次数后还是失败时调用
     * @param taskId    任务ID
     * @param taskType  任务类型
     * @param bizId     业务ID
     * @param param 任务参数
     * @return
     */
    void failHandle(String taskId, String taskType, String bizId, String param);
}
