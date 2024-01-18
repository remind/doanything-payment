package cn.doanything.payment.infrastructure.persistence.repository.inner;

import cn.doanything.payment.domain.BasePayOrder;
import cn.doanything.payment.infrastructure.persistence.convertor.PayOrderDalConvertor;
import cn.doanything.payment.infrastructure.persistence.dataobject.OrderExtensionDO;
import cn.doanything.payment.infrastructure.persistence.dataobject.PayOrderDO;
import cn.doanything.payment.infrastructure.persistence.mapper.OrderExtensionMapper;
import cn.doanything.payment.infrastructure.persistence.mapper.PayOrderMapper;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author wxj
 * 2024/1/18
 */
@Repository
public class BasePayOrderInnerRepository {

    @Autowired
    private PayOrderMapper dalMapper;

    @Autowired
    private PayOrderDalConvertor dalConvertor;

    private OrderExtensionMapper extensionMapper;

    public List<BasePayOrder> loadByPaymentId(String paymentId) {
        List<BasePayOrder> basePayOrders = dalConvertor.toBasePayOrder(dalMapper.selectList(buildPaymentIdQueryWrapper(paymentId)));
        Map<String, OrderExtensionDO> orderExtensionMap = getOrderExtensionMap(paymentId);
        basePayOrders.forEach(basePayOrder -> {
            OrderExtensionDO orderExtensionDO = orderExtensionMap.get(basePayOrder.getPaymentId());
            if (orderExtensionDO != null) {
                basePayOrder.setOrderExtension(orderExtensionDO.getContent());
            }
        });
        return basePayOrders;
    }

    public void store(BasePayOrder payOrder) {
        dalMapper.insert(dalConvertor.toDo(payOrder));

        if (StrUtil.isNotBlank(payOrder.getOrderExtension())) {
            OrderExtensionDO orderExtensionDO = new OrderExtensionDO();
            orderExtensionDO.setPaymentId(payOrder.getPaymentId());
            orderExtensionDO.setOrderId(payOrder.getOrderId());
            orderExtensionDO.setContent(payOrder.getOrderExtension());
            extensionMapper.insert(orderExtensionDO);
        }
    }

    public void reStore(BasePayOrder payOrder) {
        dalMapper.updateById(dalConvertor.toDo(payOrder));
    }

    private Map<String, OrderExtensionDO> getOrderExtensionMap(String paymentId) {
        List<OrderExtensionDO> orderExtensionDOs = extensionMapper.selectList(buildPaymentIdExtQueryWrapper(paymentId));
        return orderExtensionDOs.stream().collect(Collectors.toMap(OrderExtensionDO::getPaymentId, orderExtensionDO -> orderExtensionDO));
    }

    private Wrapper<OrderExtensionDO> buildPaymentIdExtQueryWrapper(String paymentId) {
        return new LambdaQueryWrapper<OrderExtensionDO>().eq(OrderExtensionDO::getPaymentId, paymentId);
    }
    private Wrapper<PayOrderDO> buildPaymentIdQueryWrapper(String paymentId) {
        return new LambdaQueryWrapper<PayOrderDO>().eq(PayOrderDO::getPaymentId, paymentId);
    }
}
