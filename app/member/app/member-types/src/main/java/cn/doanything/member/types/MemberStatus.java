package cn.doanything.member.types;

import cn.doanything.commons.enums.CodeEnum;

/**
 * 会员状态
 *
 * @author wxj
 * 2023/12/11
 */
public enum MemberStatus implements CodeEnum {

    INACTIVE("0", "未激活"),
    NORMAL("1", "正常"),
    SLEEP("2", "休眠"),
    CANCEL("3", "销户");

    /**
     * 代码,只能为1位正整数
     */
    private final String code;
    /**
     * 信息
     */
    private final String displayName;

    MemberStatus(String code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getDisplayName() {
        return this.displayName;
    }

}
