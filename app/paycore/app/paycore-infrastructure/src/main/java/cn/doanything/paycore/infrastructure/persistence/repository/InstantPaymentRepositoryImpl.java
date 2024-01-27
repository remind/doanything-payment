package cn.doanything.paycore.infrastructure.persistence.repository;

import cn.doanything.paycore.domain.payorder.BasePayOrder;
import cn.doanything.paycore.domain.instant.InstantPayment;
import cn.doanything.paycore.domain.payorder.PayOrder;
import cn.doanything.paycore.domain.payorder.RefundOrder;
import cn.doanything.paycore.domain.repository.InstantPaymentRepository;
import cn.doanything.paycore.infrastructure.persistence.repository.inner.BasePayOrderInnerRepository;
import cn.doanything.paycore.infrastructure.persistence.repository.inner.FundDetailInnerRepository;
import cn.doanything.paycore.types.asset.BelongTo;
import cn.doanything.paycore.types.funds.FundDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author wxj
 * 2024/1/17
 */
@Repository
public class InstantPaymentRepositoryImpl extends AbstractPaymentRepository implements InstantPaymentRepository {

    @Autowired
    private BasePayOrderInnerRepository basePayOrderInnerRepository;

    @Autowired
    private FundDetailInnerRepository fundDetailInnerRepository;

    @Override
    public void store(InstantPayment payment) {
        if(storePayment(payment)) {
            basePayOrderInnerRepository.store(payment.getPayOrder());
        }
    }

    @Override
    public void load(String paymentId) {
        InstantPayment payment = (InstantPayment) loadPayment(paymentId, false);
        if (payment != null) {
            List<BasePayOrder> basePayOrders = basePayOrderInnerRepository.loadByPaymentId(paymentId);
            if (!CollectionUtils.isEmpty(basePayOrders)) {
                List<FundDetail> fundDetails = fundDetailInnerRepository.loadByPaymentId(paymentId);
                basePayOrders.forEach(basePayOrder -> {
                    if (basePayOrder instanceof PayOrder payOrder) {
                        payment.setPayOrder(payOrder);
                    } else if (basePayOrder instanceof RefundOrder refundOrder) {
                        payment.getRefundOrderList().add(refundOrder);
                    }
                    fillFundDetails(basePayOrder, fundDetails);
                });
            }
        }
    }

    private void fillFundDetails(BasePayOrder basePayOrder, List<FundDetail> fundDetails) {
        fundDetails.forEach(fundDetail -> {
            if (fundDetail.getOrderId().equals(basePayOrder.getOrderId())) {
                if (fundDetail.getBelongTo() == BelongTo.PAYEE) {
                    basePayOrder.addPayeeFundDetail(fundDetail);
                } else if (fundDetail.getBelongTo() == BelongTo.PAYER) {
                    basePayOrder.addPayerFundDetail(fundDetail);
                }
            }
        });
    }
 }
