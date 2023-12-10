package cn.doanything.member.domain.config;

/**
 * 常量
 * @author wxj
 * 2023/12/10
 */
public interface MemberConstants {

    /**
     * 会员ID序列名称
     */
    String MEMBER_ID_SEQ_NAME = "seq_member_id";

    /**
     * 会员ID序列长度
     */
    int MEMBER_ID_SEQ_LENGTH = 10;

    /**
     * 会员ID不足长度时补位字符
     */
    char MEMBER_ID_FIX_CHAR = '0';

}
