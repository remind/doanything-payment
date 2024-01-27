package cn.doanything.paycore.domain.repository;

import cn.doanything.paycore.domain.payorder.BasePayOrder;

/**
 * @author wxj
 * 2024/1/26
 */
public interface BasePayOrderRepository {

    @SuppressWarnings("rawtypes")
    void reStore(BasePayOrder payOrder);
}
