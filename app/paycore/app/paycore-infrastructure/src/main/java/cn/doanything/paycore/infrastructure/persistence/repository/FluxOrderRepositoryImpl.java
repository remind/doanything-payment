package cn.doanything.paycore.infrastructure.persistence.repository;

import cn.doanything.paycore.domain.flux.FluxInstruction;
import cn.doanything.paycore.domain.flux.FluxOrder;
import cn.doanything.paycore.domain.repository.FluxOrderRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wxj
 * 2024/1/28
 */
@Repository
public class FluxOrderRepositoryImpl implements FluxOrderRepository {
    @Override
    public void store(FluxOrder fluxOrder) {
        List<FluxInstruction> fluxInstructions = fluxOrder.getAllFluxInstructs();
    }
}
