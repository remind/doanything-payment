package cn.doanything.framework.scheduler.model;

/**
 * 任务状态
 *
 * @author wxj
 * 2023/12/26
 */
public enum TaskStatus {
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
    private final String name;

    TaskStatus(String code, String name) {
        this.code = code;
        this.name = name;
    }

    /**
     * 通过枚举code获得枚举
     *
     * @param code
     * @return TaskStatus
     */
    public static TaskStatus getByCode(String code) {
        for (TaskStatus enumObject : values()) {
            if (enumObject.getCode().equals(code)) {
                return enumObject;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
