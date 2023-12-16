package cn.doanything.account.domain.detail;

import cn.doanything.account.types.enums.AccountAttribute;
import cn.doanything.commons.lang.types.Money;
import lombok.Data;

/**
 * @author wxj
 * 2023/12/16
 */
@Data
public class AccountDetail extends AbstractDetail {
    /**
     * 明细流水号
     */
    private Long seqNo;
    /**
     * 事务号（结算提交包号）
     */
    private String transactionNo;
    /**
     * 产品编码
     */
    private String productCode;
    /**
     * 支付编码
     */
    private String paymentCode;
    /** 操作类型 */
    /**
     * 交易前余额
     */
    private Money beforeAmount = new Money();
    /**
     * 摘要
     **/
    private String summary;
    /**
     * 会员号
     **/
    private String memeberId;
    /**
     * 账户类型
     **/
    private Integer accountType;
    /**
     * 账户属性
     **/
    private AccountAttribute accountAttribute;
}
