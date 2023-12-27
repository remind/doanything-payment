package cn.doanything.account.infrastructure.convertor;

import cn.doanything.account.types.accounting.AccountTitleRange;
import cn.doanything.account.types.accounting.AccountTitleStatus;
import cn.doanything.account.types.accounting.AccountTitleType;
import cn.doanything.account.types.buffer.BufferedRuleStatus;
import cn.doanything.account.types.enums.*;
import cn.doanything.commons.enums.CodeEnum;
import org.mapstruct.Mapper;

/**
 * @author wxj
 * 2023/12/16
 */
@Mapper(componentModel = "spring")
public interface EnumsConvertor {

    default <T extends Enum<?> & CodeEnum> T getByCode(Class<T> enumCls, String code) {
        T[] enumConstants = enumCls.getEnumConstants();
        for (T t : enumConstants) {
            if (t.getCode().equals(code)) return t;
        }
        return null;
    }

    default AccountTitleType toAccountTitleType(String titleType) {
        return AccountTitleType.getByCode(titleType);
    }

    default String toAccountTitleTypeCode(AccountTitleType accountTitleType) {
        return accountTitleType == null ? null : accountTitleType.getCode();
    }

    default BalanceDirection toBalanceDirection(String direction) {
        return BalanceDirection.getByCode(direction);
    }

    default String toBalanceDirectionCode(BalanceDirection balanceDirection) {
        return balanceDirection == null ? null : balanceDirection.getCode();
    }

    default AccountTitleStatus toAccountTitleStatus(String status) {
        return AccountTitleStatus.getByCode(status);
    }

    default String toAccountTitleStatusCode(AccountTitleStatus accountTitleStatus) {
        return accountTitleStatus == null ? null : accountTitleStatus.getCode();
    }

    default AccountTitleRange toAccountTitleRange(String accountTitleRange) {
        return AccountTitleRange.getByCode(accountTitleRange);
    }

    default String toAccountTitleRangeCode(AccountTitleRange accountTitleRange) {
        return accountTitleRange == null ? null : accountTitleRange.getCode();
    }

    default AccountAttribute toAccountAttribute(String accountAttribute) {
        return AccountAttribute.getByCode(accountAttribute);
    }

    default String toAccountAttributeCode(AccountAttribute accountAttribute) {
        return accountAttribute == null ? null : accountAttribute.getCode();
    }

    default CrDr toCrDr(String crDrCode) {
        return CrDr.getByCode(crDrCode);
    }

    default String toCrDrCode(CrDr crDr) {
        return crDr == null ? null : crDr.getCode();
    }

    default DenyStatus toDenyStatus(String code) {
        return DenyStatus.getByCode(code);
    }

    default String toDenyStatusCode(DenyStatus enumObject) {
        return enumObject == null ? null : enumObject.getCode();
    }

    default IODirection toIODirection(String code) {
        return IODirection.getByCode(code);
    }

    default String toIODirectionCode(IODirection enumObject) {
        return enumObject == null ? null : enumObject.getCode();
    }

    default BufferDetailStatus toBufferDetailStatus(String code) {
        return BufferDetailStatus.getByCode(code);
    }

    default String toBufferDetailStatusCode(BufferDetailStatus enumObject) {
        return enumObject == null ? null : enumObject.getCode();
    }

    default BufferedRuleStatus toBufferedRuleStatus(String code) {
        return BufferedRuleStatus.getByCode(code);
    }

    default String toBufferedRuleStatusCode(BufferedRuleStatus enumObject) {
        return enumObject == null ? null : enumObject.getCode();
    }

}
