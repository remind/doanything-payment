package cn.doanything.account.infrastructure.persistence.dataobject;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 会计科目表
 * </p>
 *
 * @author wxj
 * @since 2023-12-18
 */
@TableName("ta_account_title")
public class AccountTitleDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 科目代码
     */
    @TableId("title_code")
    private String titleCode;

    /**
     * 科目名称
     */
    private String titleName;

    /**
     * 科目级别
     */
    private Short titleLevel;

    /**
     * 父科目代码
     */
    private String parentTitleCode;

    /**
     * 是否为叶子节点：1 是， 0 否
     */
    private String leaf;

    /**
     * 类型：1（资产类）；2（负债类）；3(所有者权益)；4（共同类）5(损益类)
     */
    private String type;

    /**
     * 余额方向：1:借 2:贷 0:双向
     */
    private String balanceDirection;

    /**
     * 状态：1（有效）；0（无效）
     */
    private String status;

    /**
     * 适用范围：1.内部科目;2,外部科目
     */
    private String titleRange;

    /**
     * 备注
     */
    private String memo;

    /**
     * 创建时间
     */
    private LocalDateTime gmtCreate;

    /**
     * 最后修改时间
     */
    private LocalDateTime gmtModified;

    public String getTitleCode() {
        return titleCode;
    }

    public void setTitleCode(String titleCode) {
        this.titleCode = titleCode;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public Short getTitleLevel() {
        return titleLevel;
    }

    public void setTitleLevel(Short titleLevel) {
        this.titleLevel = titleLevel;
    }

    public String getParentTitleCode() {
        return parentTitleCode;
    }

    public void setParentTitleCode(String parentTitleCode) {
        this.parentTitleCode = parentTitleCode;
    }

    public String getLeaf() {
        return leaf;
    }

    public void setLeaf(String leaf) {
        this.leaf = leaf;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBalanceDirection() {
        return balanceDirection;
    }

    public void setBalanceDirection(String balanceDirection) {
        this.balanceDirection = balanceDirection;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitleRange() {
        return titleRange;
    }

    public void setTitleRange(String titleRange) {
        this.titleRange = titleRange;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
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
        return "AccountTitleDO{" +
        "titleCode = " + titleCode +
        ", titleName = " + titleName +
        ", titleLevel = " + titleLevel +
        ", parentTitleCode = " + parentTitleCode +
        ", leaf = " + leaf +
        ", type = " + type +
        ", balanceDirection = " + balanceDirection +
        ", status = " + status +
        ", titleRange = " + titleRange +
        ", memo = " + memo +
        ", gmtCreate = " + gmtCreate +
        ", gmtModified = " + gmtModified +
        "}";
    }
}
