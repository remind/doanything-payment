package cn.doanything.commons.lang.utils;

import cn.doanything.commons.enums.CodeEnum;

/**
 * @author wxj
 * 2023/12/27
 */
public class EnumUtil {

    /**
     * 获取枚举实例
     * @param enumCls
     * @param code
     * @return
     * @param <T>
     */
    public static <T extends Enum<?> & CodeEnum> T getByCode(Class<T> enumCls, String code) {
        T[] enumConstants = enumCls.getEnumConstants();
        for (T t : enumConstants) {
            if (t.getCode().equals(code)) return t;
        }
        return null;
    }
}
