package cn.doanything.paycore.infrastructure.persistence.repository;

import cn.doanything.paycore.domain.flux.FluxInstruction;
import cn.doanything.paycore.domain.flux.FluxOrder;
import cn.doanything.paycore.domain.repository.FluxInstructionRepository;
import cn.doanything.paycore.domain.repository.FluxOrderRepository;
import cn.doanything.paycore.infrastructure.persistence.convertor.FluxOrderDalConvertor;
import cn.doanything.paycore.infrastructure.persistence.dataobject.FluxOrderDO;
import cn.doanything.paycore.infrastructure.persistence.mapper.FluxOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author wxj
 * 2024/1/28
 */
@Repository
public class FluxOrderRepositoryImpl implements FluxOrderRepository {

    @Autowired
    private FluxOrderMapper dalMapper;

    @Autowired
    private FluxOrderDalConvertor dalConvertor;

    @Autowired
    private FluxInstructionRepository instructionRepository;

    @Override
    public void store(FluxOrder fluxOrder) {
        FluxOrderDO fluxOrderDO = dalConvertor.toDo(fluxOrder);
        dalMapper.insert(fluxOrderDO);
        List<FluxInstruction> fluxInstructions = fluxOrder.getAllFluxInstructs();
        if (!CollectionUtils.isEmpty(fluxInstructions)) {
            for (FluxInstruction fluxInstruction : fluxInstructions) {
                instructionRepository.store(fluxInstruction);
            }
        }

    }
}
