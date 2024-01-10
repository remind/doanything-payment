package cn.doanything.framework.scheduler.model;

import cn.doanything.commons.enums.CodeEnum;

/**
 * 任务状态
 *
 * @author wxj
 * 2023/12/26
 */
public enum TaskStatus implements CodeEnum {
    WAIT("W", "待执行"),
    PROCESS("P", "执行中"),
    FAIL("F", "失败"),
    SUCCESS("S", "成功");
    /**
     * 编码
     */
    private final String code;

    /**
     * 名称
     */
    private final String displayName;

    TaskStatus(String code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }


    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getDisplayName() {
        return this.displayName;
    }
}
