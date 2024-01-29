package cn.doanything.paycore.infrastructure.persistence.repository;

import cn.doanything.paycore.domain.flux.FluxInstruction;
import cn.doanything.paycore.domain.repository.FluxInstructionRepository;
import cn.doanything.paycore.infrastructure.persistence.convertor.FluxInstructionDalConvertor;
import cn.doanything.paycore.infrastructure.persistence.dataobject.FluxInstructionDO;
import cn.doanything.paycore.infrastructure.persistence.mapper.FluxInstructionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author wxj
 * 2024/1/28
 */
@Repository
public class FluxInstructionRepositoryImpl implements FluxInstructionRepository {

    @Autowired
    private FluxInstructionMapper dalMapper;

    @Autowired
    private FluxInstructionDalConvertor dalConvertor;

    @Override
    public void store(FluxInstruction fluxInstruction) {
        FluxInstructionDO instructionDO = dalConvertor.toDo(fluxInstruction);
        dalMapper.insert(instructionDO);
    }

    @Override
    public void reStore(FluxInstruction fluxInstruction) {
        FluxInstructionDO instructionDO = dalConvertor.toDo(fluxInstruction);
        dalMapper.updateById(instructionDO);
    }
}
