package cn.doanything.payment.application.flux.external;

import cn.doanything.payment.application.flux.ExecutorResult;
import cn.doanything.payment.application.flux.FluxExecutor;
import cn.doanything.payment.domain.flux.AssetFluxDetail;
import cn.doanything.payment.domain.flux.AssetFluxOrder;

/**
 * @author wxj
 * 2024/1/21
 */
public class ExternalFluxExecutor implements FluxExecutor {
    @Override
    public ExecutorResult execute(AssetFluxOrder fluxOrder, AssetFluxDetail fluxDetail) {
        return null;
    }
}
