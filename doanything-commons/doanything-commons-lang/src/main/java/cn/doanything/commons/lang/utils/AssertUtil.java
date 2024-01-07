package cn.doanything.commons.lang.utils;

import cn.doanything.commons.exceptions.BizException;
import cn.doanything.commons.response.ResultCode;

/**
 * @author wxj
 * 2023/12/16
 */
public class AssertUtil {

    public static void isTrue(boolean expression, ResultCode resultCode, String message) {
        if (!expression) {
            throw new BizException(resultCode, message);
        }
    }

    public static void isTrue(boolean expression, ResultCode resultCode) {
        isTrue(expression, resultCode, null);
    }
    public static void isTrue(boolean expression, String message) {
        isTrue(expression, null, message);
    }

    public static void isFalse(boolean expression, ResultCode resultCode) {
        isFalse(expression, resultCode, null);
    }
    public static void isFalse(boolean expression, String message) {
        isFalse(expression, null, message);
    }

    public static void isFalse(boolean expression, ResultCode resultCode, String message) {
        isTrue(!expression, resultCode, message);
    }

    public static void isNotNull(Object object, ResultCode resultCode) {
        isNotNull(object, resultCode, null);
    }

    public static void isNotNull(Object object, String message) {
        isNotNull(object, null, message);
    }

    public static void isNotNull(Object object, ResultCode resultCode, String message) {
        isTrue(object != null, resultCode, message);
    }

    public static void isNull(Object object, ResultCode resultCode) {
        isNull(object, resultCode, null);
    }

    public static void isNull(Object object, String message) {
        isNull(object, null, message);
    }

    public static void isNull(Object object, ResultCode resultCode, String message) {
        isTrue(object == null, resultCode, message);
    }
}
