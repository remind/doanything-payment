package cn.doanything.framework.scheduler.model;

import lombok.Data;

import java.time.LocalDateTime;
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
     * 任务参数
     */
    private String param;

    /**
     * 执行次数
     */
    private int executeCount;

    /**
     * 下次执行时间
     */
    private LocalDateTime nextExecuteTime;

    /**
     * 开始执行时间
     */
    private LocalDateTime startExecuteTime;

    public Task() {
    }

    public Task(String type, String bizId, LocalDateTime nextExecuteTime, String param) {
        this.type = Objects.requireNonNull(type);
        this.bizId = Objects.requireNonNull(bizId);
        this.status = TaskStatus.WAIT;
        this.executeCount = 0;
        this.nextExecuteTime = nextExecuteTime == null ? LocalDateTime.now() : nextExecuteTime;
        this.param = param;
    }
}
