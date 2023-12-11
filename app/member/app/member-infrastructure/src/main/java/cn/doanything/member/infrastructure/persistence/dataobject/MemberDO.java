package cn.doanything.member.infrastructure.persistence.dataobject;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author wxj
 * @since 2023-12-11
 */
@TableName("tm_member")
public class MemberDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 会员ID
     */
    @TableId("member_id")
    private String memberId;

    /**
     * 会员类型
     */
    private Byte memberType;

    /**
     * 状态
     */
    private Byte status;

    /**
     * 创建时间
     */
    private LocalDateTime gmtCreate;

    /**
     * 修改时间
     */
    private LocalDateTime gmtModified;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public Byte getMemberType() {
        return memberType;
    }

    public void setMemberType(Byte memberType) {
        this.memberType = memberType;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
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
        return "MemberDO{" +
        "memberId = " + memberId +
        ", memberType = " + memberType +
        ", status = " + status +
        ", gmtCreate = " + gmtCreate +
        ", gmtModified = " + gmtModified +
        "}";
    }
}
