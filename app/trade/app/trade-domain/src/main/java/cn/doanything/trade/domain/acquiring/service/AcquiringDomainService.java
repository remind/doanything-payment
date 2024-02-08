package cn.doanything.trade.domain.acquiring.service;

/**
 * @author wxj
 * 2024/2/8
 */
public interface AcquiringDomainService {

    /**
     * 获取收款账户
     * @param partnerId
     * @param sellerId
     * @return
     */
    String getSellerAccount(String partnerId, String sellerId);
}

