package cn.doanything.framework.scheduler;

import java.time.LocalDateTime;

/**
 * @author wxj
 * 2023/12/29
 */
public interface SchedulerTaskRegister {

    /**
     * 注册任务
     *
     * @param taskType  任务类型
     * @param bizId     业务ID，业务ID+任务类型幂等
     * @return 任务ID
     */
    String registryTask(String taskType, String bizId);

    /**
     * 注册任务
     *
     * @param taskType  任务类型
     * @param bizId     业务ID，业务ID+任务类型幂等
     * @param nextExecuteTime 下次执行时间
     * @return 任务ID
     */
    String registryTask(String taskType, String bizId, LocalDateTime nextExecuteTime);

    /**
     * 注册任务
     *
     * @param taskType  任务类型
     * @param bizId     业务ID，业务ID+任务类型幂等
     * @param param     参数
     * @return 任务ID
     */
    String registryTask(String taskType, String bizId, String param);

    /**
     * 注册任务
     *
     * @param taskType  任务类型
     * @param bizId     业务ID，业务ID+任务类型幂等
     * @param nextExecuteTime 下次执行时间
     * @param param     参数
     * @return 任务ID
     */
    String registryTask(String taskType, String bizId, LocalDateTime nextExecuteTime, String param);
}
