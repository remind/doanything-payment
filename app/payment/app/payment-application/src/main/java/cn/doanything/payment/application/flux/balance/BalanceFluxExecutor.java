package cn.doanything.payment.application.flux.balance;

import cn.doanything.payment.application.flux.ExecutorResult;
import cn.doanything.payment.application.flux.FluxExecutor;
import cn.doanything.payment.domain.flux.AssetFluxInstruct;
import cn.doanything.payment.domain.flux.AssetFluxOrder;

/**
 * @author wxj
 * 2024/1/22
 */
public class BalanceFluxExecutor implements FluxExecutor {
    @Override
    public ExecutorResult execute(AssetFluxOrder fluxOrder, AssetFluxInstruct fluxInstruct) {
        return null;
    }
}
