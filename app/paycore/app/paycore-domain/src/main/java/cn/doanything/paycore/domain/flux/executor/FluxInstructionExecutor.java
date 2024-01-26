package cn.doanything.paycore.domain.flux.executor;

/**
 * @author wxj
 * 2024/1/26
 */
public interface FluxInstructionExecutor {

    FluxResult execute(FluxInstructChain instructChain);
}
