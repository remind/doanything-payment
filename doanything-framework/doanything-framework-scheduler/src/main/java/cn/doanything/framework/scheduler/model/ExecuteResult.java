package cn.doanything.framework.scheduler.model;

import lombok.Getter;

@Getter
public class ExecuteResult {

    /**
     * 任务ID
     */
    private final String taskId;

    /**
     * 状态
     */
    private final boolean success;

    /**
     * 消息
     */
    private final String message;

    /**
     * 开始时间
     */
    private Long startTime;

    /**
     * 结束时间
     */
    private Long endTime;

    public ExecuteResult(String taskId, boolean success, String message) {
        this.taskId = taskId;
        this.success = success;
        this.message = message;
    }

    public void setTime(Long startTime, Long endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public static ExecuteResult success(String taskId) {
        return new ExecuteResult(taskId, true, "");
    }

    public static ExecuteResult fail(String taskId, String message) {
        return new ExecuteResult(taskId, false, message);
    }

}
