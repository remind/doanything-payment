package cn.doanything.account.domain.buffer;

import cn.doanything.account.types.buffer.BufferedRuleStatus;
import cn.doanything.account.types.enums.CrDr;
import cn.doanything.commons.lang.Entity;
import lombok.Data;

/**
 * @author wxj
 * 2023/12/25
 */
@Data
public class BufferedRule extends Entity {

    /**
     * 规则ID
     */
    private String ruleId;

    /**
     * 帐号
     */
    private String accountNo;

    /**
     * 借贷标志，为空则均支持
     */
    private CrDr crDr;

    /**
     * 是否有效状态
     */
    private BufferedRuleStatus status;

    /**
     * 备注
     */
    private String memo;

}
