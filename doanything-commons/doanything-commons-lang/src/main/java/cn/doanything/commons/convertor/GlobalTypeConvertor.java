package cn.doanything.commons.convertor;

import cn.doanything.commons.enums.CodeEnum;
import cn.doanything.commons.enums.EnableEnum;
import cn.doanything.commons.enums.ResultStatusEnum;
import cn.doanything.commons.lang.utils.EnumUtil;
import cn.hutool.core.util.StrUtil;

import java.util.List;

/**
 * 全局类型转换
 *
 * @author wxj
 * 2024/1/4
 */
public interface GlobalTypeConvertor {

    /**
     * list分隔符
     */
    String LIST_SEPARATOR = ",";

    default String getCode(CodeEnum codeEnum) {
        return codeEnum == null ? null : codeEnum.getCode();
    }

    default EnableEnum toEnableEnum(String code) {
        return EnumUtil.getByCode(EnableEnum.class, code);
    }

    default List<String> toList(String str) {
        return StrUtil.split(str, LIST_SEPARATOR);
    }

    default String toStr(List<String> list) {
        return StrUtil.join(LIST_SEPARATOR, list);
    }

    default ResultStatusEnum toResultStatusEnum(String code) {
        return EnumUtil.getByCode(ResultStatusEnum.class, code);
    }
}
