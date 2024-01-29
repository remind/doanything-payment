package cn.doanything.paycore.infrastructure.persistence.convertor;

import cn.doanything.commons.convertor.ReadWriteConvertor;
import cn.doanything.paycore.domain.flux.FluxInstruction;
import cn.doanything.paycore.infrastructure.convertor.EnumsConvertor;
import cn.doanything.paycore.infrastructure.persistence.dataobject.FluxInstructionDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author wxj
 * 2024/1/29
 */
@Mapper(componentModel = "spring", uses = {EnumsConvertor.class})
public interface FluxInstructionDalConvertor extends ReadWriteConvertor<FluxInstruction, FluxInstructionDO> {

    @Override
    @Mapping(target = "amount", expression = "java(toMoney(instructionDO.getAmount(), instructionDO.getCurrencyCode()))")
    FluxInstruction toEntity(FluxInstructionDO instructionDO);

    @Override
    @Mapping(target = "amount", expression = "java(toAmountValue(instruction.getAmount()))")
    @Mapping(target = "currencyCode", expression = "java(toCurrencyCode(instruction.getAmount()))")
    FluxInstructionDO toDo(FluxInstruction instruction);
}
