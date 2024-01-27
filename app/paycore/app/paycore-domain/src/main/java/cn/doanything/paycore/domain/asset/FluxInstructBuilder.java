package cn.doanything.paycore.domain.asset;

import cn.doanything.paycore.domain.flux.FluxInstruction;
import cn.doanything.paycore.types.funds.FundDetail;

/**
 * @author wxj
 * 2024/1/26
 */
public interface FluxInstructBuilder {

    FluxInstruction build(FundDetail fundDetail);
}
