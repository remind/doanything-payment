package cn.doanything.account.types.enums;

import cn.doanything.commons.enums.CodeEnum;

/**
 * 缓冲明细状态
 * @author wxj
 * 2023/12/21
 */
public enum BufferDetailStatus implements CodeEnum {
    INIT("1", "待入账"),
    PROCESSING("2", "处理中"),
    SUCCESS("3", "成功"),
    FAIL("4", "失败"),
    ;

    private final String code;
    private final String displayName;

    private BufferDetailStatus(String code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }

    /**
     * 根据code获取枚举
     *
     * @param code
     * @return BufferDetailStatus
     */
    public static BufferDetailStatus getByCode(String code) {
        for (BufferDetailStatus enumObject : BufferDetailStatus.values()) {
            if (enumObject.getCode().equals(code)) {
                return enumObject;
            }
        }
        return null;
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
