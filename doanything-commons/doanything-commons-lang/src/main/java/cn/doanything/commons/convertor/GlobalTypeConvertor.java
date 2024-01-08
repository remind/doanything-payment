package cn.doanything.commons.convertor;

import cn.doanything.commons.enums.CodeEnum;
import cn.doanything.commons.enums.EnableEnum;
import cn.doanything.commons.lang.utils.EnumUtil;

/**
 * @author wxj
 * 2024/1/4
 */
public interface GlobalTypeConvertor {

    default String getCode(CodeEnum codeEnum) {
        return codeEnum == null? null : codeEnum.getCode();
    }
    default EnableEnum toEnableEnum(String code) {
        return EnumUtil.getByCode(EnableEnum.class, code);
    }

    default String toEnableEnum(EnableEnum enumObject) {
        return enumObject.getCode();
    }
}
