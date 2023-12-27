package cn.doanything.framework.scheduler.repository.mybatis.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 调度任务
 * </p>
 *
 * @author wxj
 * @since 2023-12-27
 */
@TableName("tf_scheduler_task")
public class SchedulerTaskDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 任务ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

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
    private String status;

    /**
     * 执行次数
     */
    private Byte executeCount;

    /**
     * 下次执行时间
     */
    private Long nextExecuteTime;

    /**
     * 开始执行时间
     */
    private Long startExecuteTime;

    /**
     * 环境
     */
    private String env;

    /**
     * 创建时间
     */
    private LocalDateTime gmtCreate;

    /**
     * 最后修改时间
     */
    private LocalDateTime gmtModified;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBizId() {
        return bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Byte getExecuteCount() {
        return executeCount;
    }

    public void setExecuteCount(Byte executeCount) {
        this.executeCount = executeCount;
    }

    public Long getNextExecuteTime() {
        return nextExecuteTime;
    }

    public void setNextExecuteTime(Long nextExecuteTime) {
        this.nextExecuteTime = nextExecuteTime;
    }

    public Long getStartExecuteTime() {
        return startExecuteTime;
    }

    public void setStartExecuteTime(Long startExecuteTime) {
        this.startExecuteTime = startExecuteTime;
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public LocalDateTime getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(LocalDateTime gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public LocalDateTime getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(LocalDateTime gmtModified) {
        this.gmtModified = gmtModified;
    }

    @Override
    public String toString() {
        return "SchedulerTaskDO{" +
        "id = " + id +
        ", bizId = " + bizId +
        ", type = " + type +
        ", param = " + param +
        ", status = " + status +
        ", executeCount = " + executeCount +
        ", nextExecuteTime = " + nextExecuteTime +
        ", startExecuteTime = " + startExecuteTime +
        ", env = " + env +
        ", gmtCreate = " + gmtCreate +
        ", gmtModified = " + gmtModified +
        "}";
    }
}
