package cn.doanything.commons.sequence;

import org.junit.Test;

/**
 * @author wxj
 * 2023/12/10
 */
public class IdGeneratorTest {

    @Test
    public void testSuccessGetId() {
        System.out.println(IdGenerator.getId("001", "001", "00",12345678l));
    }
}
