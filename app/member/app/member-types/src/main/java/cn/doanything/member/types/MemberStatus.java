package cn.doanything.member.types;

/**
 * 会员状态
 *
 * @author wxj
 * 2023/12/11
 */
public enum MemberStatus {

    INACTIVE(0, "未激活"), NORMAL(1, "正常"), SLEEP(2, "休眠"), CANCEL(3, "销户");

    /**
     * 代码,只能为1位正整数
     */
    private final int code;
    /**
     * 信息
     */
    private final String message;

    MemberStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 通过代码获取枚举项
     *
     * @param code
     * @return
     */
    public static MemberStatus getByCode(Integer code) {
        if (code == null) {
            return null;
        }

        for (MemberStatus memberStatus : MemberStatus.values()) {
            if (memberStatus.getCode() == code) {
                return memberStatus;
            }
        }

        return null;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
