package cn.doanything.paycore.domain.payorder.service;

import cn.doanything.paycore.domain.payorder.PayOrder;
import cn.doanything.paycore.types.PayResult;

/**
 * @author wxj
 * 2024/1/27
 */
public interface PayOrderDomainService {

    PayResult pay(PayOrder payOrder);

}
