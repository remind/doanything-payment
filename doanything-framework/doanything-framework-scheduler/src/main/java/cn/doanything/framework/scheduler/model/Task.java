package cn.doanything.framework.scheduler.model;

import lombok.Data;

import java.util.Date;
import java.util.Objects;

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

    public Task() {
    }

    public Task(String bizId, String type) {
        this.bizId = Objects.requireNonNull(bizId);;
        this.type = Objects.requireNonNull(type);;
        this.status = TaskStatus.WAIT;
        this.executeCount = 0;
        this.nextExecuteTime = new Date();
    }
}
