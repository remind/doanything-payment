package cn.doanything.framework.scheduler.model;

import lombok.Data;

import java.util.Date;

/**
 * @author wxj
 * 2023/12/26
 */
@Data
public class Task {
    /**
     * 任务ID
     */
    private String id;

    /**
     * 业务ID
     */
    private String bizId;

    /**
     * 任务类型
     */
    private String type;

    /**
     * 任务参数
     */
    private String param;

    /**
     * 任务状态
     */
    private TaskStatus status;

    /**
     * 执行次数
     */
    private int executeCount;

    /**
     * 下次执行时间
     */
    private Date nextExecuteTime;

    /**
     * 开始执行时间
     */
    private Date startExecuteTime;

    /**
     * 环境
     */
    private String env;
}
