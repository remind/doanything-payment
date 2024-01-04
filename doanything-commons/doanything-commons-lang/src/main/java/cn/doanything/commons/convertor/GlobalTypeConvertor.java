package cn.doanything.commons.convertor;

import cn.doanything.commons.enums.EnableEnum;
import cn.doanything.commons.lang.utils.EnumUtil;

/**
 * @author wxj
 * 2024/1/4
 */
public interface GlobalTypeConvertor {

    default EnableEnum toEnableEnum(String code) {
        return EnumUtil.getByCode(EnableEnum.class, code);
    }

    default String toEnableEnum(EnableEnum enumObject) {
        return enumObject.getCode();
    }
}
