package cn.doanything.payment.domain;

/**
 * @author wxj
 * 2024/1/15
 */
public interface OrderStatus {

    /**
     * 状态编码
     * @return
     */
    String getCode();

    /**
     * 状态名称
     * @return
     */
    String getName();
}
