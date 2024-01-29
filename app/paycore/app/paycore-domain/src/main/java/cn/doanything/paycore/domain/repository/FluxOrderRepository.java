package cn.doanything.paycore.domain.repository;

import cn.doanything.paycore.domain.flux.FluxOrder;

/**
 * @author wxj
 * 2024/1/27
 */
public interface FluxOrderRepository {

    void store(FluxOrder fluxOrder);

    void reStore(FluxOrder fluxOrder);
}
