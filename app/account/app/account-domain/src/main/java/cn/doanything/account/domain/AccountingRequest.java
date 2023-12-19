package cn.doanything.account.domain;

import cn.doanything.commons.lang.Entity;
import lombok.Data;

/**
 * 入账请求明细
 *
 * @author wxj
 * 2023/12/19
 */
@Data
public class AccountingRequest extends Entity {

    /**
     * 入账请求流水号，唯一，一般由支付按统一规则生成
     */
    private String request_no;

    /**
     * 会计日
     **/
    private String accountingDate;
}
