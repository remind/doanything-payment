package cn.doanything.paycore.domain.flux.service;

import cn.doanything.paycore.domain.flux.FluxInstruction;
import cn.doanything.paycore.domain.flux.FluxOrder;
import cn.doanything.paycore.types.funds.FundDetail;

import java.util.List;

/**
 * @author wxj
 * 2024/1/26
 */
public interface FluxOrderDomainService {

    /**
     * 处理交换
     * @param fluxOrder
     * @param payerFundDetails
     * @param payeeFundDetails
     */
    void process(FluxOrder fluxOrder, List<FundDetail> payerFundDetails, List<FundDetail> payeeFundDetails);
    void failHandle(FluxOrder fluxOrder, FluxInstruction failInstruct);
    void reverse(FluxOrder fluxOrder, FluxInstruction failInstruct);
}
