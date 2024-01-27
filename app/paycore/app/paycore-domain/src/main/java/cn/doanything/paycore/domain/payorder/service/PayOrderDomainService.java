package cn.doanything.paycore.domain.payorder.service;

import cn.doanything.paycore.domain.payorder.PayOrder;

/**
 * @author wxj
 * 2024/1/27
 */
public interface PayOrderDomainService {

    void pay(PayOrder payOrder);

}
