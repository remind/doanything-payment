package cn.doanything.account.infrastructure.convertor;

import cn.doanything.account.types.accounting.AccountTitleRange;
import cn.doanything.account.types.accounting.AccountTitleStatus;
import cn.doanything.account.types.accounting.AccountTitleType;
import cn.doanything.account.types.buffer.BufferedRuleStatus;
import cn.doanything.account.types.enums.*;
import cn.doanything.commons.lang.utils.EnumUtil;
import org.mapstruct.Mapper;

/**
 * @author wxj
 * 2023/12/16
 */
@Mapper(componentModel = "spring")
public interface EnumsConvertor {

    default AccountTitleType toAccountTitleType(String code) {
        return EnumUtil.getByCode(AccountTitleType.class, code);
    }

    default BalanceDirection toBalanceDirection(String code) {
        return EnumUtil.getByCode(BalanceDirection.class, code);
    }

    default AccountTitleStatus toAccountTitleStatus(String code) {
        return EnumUtil.getByCode(AccountTitleStatus.class, code);
    }

    default AccountTitleRange toAccountTitleRange(String code) {
        return EnumUtil.getByCode(AccountTitleRange.class, code);
    }

    default AccountAttribute toAccountAttribute(String code) {
        return EnumUtil.getByCode(AccountAttribute.class, code);
    }

    default CrDr toCrDr(String code) {
        return EnumUtil.getByCode(CrDr.class, code);
    }

    default DenyStatus toDenyStatus(String code) {
        return EnumUtil.getByCode(DenyStatus.class, code);
    }

    default IODirection toIODirection(String code) {
        return EnumUtil.getByCode(IODirection.class, code);
    }

    default BufferDetailStatus toBufferDetailStatus(String code) {
        return EnumUtil.getByCode(BufferDetailStatus.class, code);
    }

    default BufferedRuleStatus toBufferedRuleStatus(String code) {
        return EnumUtil.getByCode(BufferedRuleStatus.class, code);
    }


}
