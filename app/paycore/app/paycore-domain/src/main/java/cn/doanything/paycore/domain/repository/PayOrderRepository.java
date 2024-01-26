package cn.doanything.paycore.domain.repository;

import cn.doanything.paycore.domain.BasePayOrder;

/**
 * @author wxj
 * 2024/1/26
 */
public interface PayOrderRepository {

    @SuppressWarnings("rawtypes")
    void reStore(BasePayOrder payOrder);
}
