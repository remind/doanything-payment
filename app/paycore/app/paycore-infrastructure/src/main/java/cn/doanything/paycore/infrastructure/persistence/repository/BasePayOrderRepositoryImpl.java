package cn.doanything.paycore.infrastructure.persistence.repository;

import cn.doanything.paycore.domain.payorder.BasePayOrder;
import cn.doanything.paycore.domain.repository.BasePayOrderRepository;
import cn.doanything.paycore.infrastructure.persistence.convertor.PayOrderDalConvertor;
import cn.doanything.paycore.infrastructure.persistence.dataobject.OrderExtensionDO;
import cn.doanything.paycore.infrastructure.persistence.mapper.OrderExtensionMapper;
import cn.doanything.paycore.infrastructure.persistence.mapper.PayOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author wxj
 * 2024/1/27
 */
@Repository
public class BasePayOrderRepositoryImpl implements BasePayOrderRepository {

    @Autowired
    private PayOrderMapper dalMapper;

    @Autowired
    private PayOrderDalConvertor dalConvertor;

    @Autowired
    private OrderExtensionMapper extensionMapper;

    @Override
    @SuppressWarnings("rawtypes")
    public void reStore(BasePayOrder payOrder) {
        dalMapper.updateById(dalConvertor.toDo(payOrder));
        extensionMapper.updateById(buildOrderExtensionDO(payOrder));
    }

    @SuppressWarnings("rawtypes")
    private OrderExtensionDO buildOrderExtensionDO(BasePayOrder payOrder) {
        OrderExtensionDO orderExtensionDO = new OrderExtensionDO();
        orderExtensionDO.setPaymentId(payOrder.getPaymentId());
        orderExtensionDO.setOrderId(payOrder.getOrderId());
        orderExtensionDO.setContent(payOrder.getOrderExtension());
        return orderExtensionDO;
    }
}
