package cn.doanything.framework.scheduler;

/**
 * @author wxj
 * 2023/12/29
 */
public interface CommTaskRegister {

    /**
     * 注册任务
     *
     * @param taskType  任务类型
     * @param bizId     业务ID，业务ID+任务类型幂等
     * @return 任务ID
     */
    String registryTask(String taskType, String bizId);
}
