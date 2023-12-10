package cn.doanything.commons.lang.utils;

import org.junit.Test;

/**
 * @author wxj
 * 2023/12/10
 */
public class IdGeneratorUtilTest {

    @Test
    public void testSuccessGetId() {
        System.out.println(IdGeneratorUtil.getId("001", "001", "00",12345678l));
    }
}
