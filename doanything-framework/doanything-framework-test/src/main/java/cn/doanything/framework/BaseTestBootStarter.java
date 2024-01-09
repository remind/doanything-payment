package cn.doanything.framework;

import cn.hutool.core.util.RandomUtil;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

/**
 * @author wxj
 * 2024/1/9
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public abstract class BaseTestBootStarter {

    public String getUUID() {
        String uuid = UUID.randomUUID().toString();
        return uuid.replace("-","").substring(0,32);
    }

    public String randomPersonalMemberId() {
        return "100000000" + RandomUtil.randomNumbers(3);
    }
}
