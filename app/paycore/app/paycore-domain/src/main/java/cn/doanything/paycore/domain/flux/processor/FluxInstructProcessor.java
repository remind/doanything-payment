package cn.doanything.paycore.domain.flux.processor;

import cn.doanything.paycore.domain.flux.FluxInstruction;
import cn.doanything.paycore.types.funds.FundDetail;

/**
 * @author wxj
 * 2024/1/26
 */
public interface FluxInstructProcessor {

    FluxInstruction build(FundDetail fundDetail);
}
