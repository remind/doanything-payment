package cn.doanything.paycore.infrastructure.persistence.repository;

import cn.doanything.paycore.domain.flux.FluxInstruction;
import cn.doanything.paycore.domain.repository.FluxInstructionRepository;
import org.springframework.stereotype.Repository;

/**
 * @author wxj
 * 2024/1/28
 */
@Repository
public class FluxInstructionRepositoryImpl implements FluxInstructionRepository {
    @Override
    public void reStore(FluxInstruction fluxInstruction) {

    }
}
