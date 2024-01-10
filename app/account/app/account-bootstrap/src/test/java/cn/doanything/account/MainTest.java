package cn.doanything.account;

import cn.doanything.account.domain.AccountDomainConstants;
import io.prometheus.client.Collector;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wxj
 * 2023/12/22
 */
public class MainTest {

    public static void main(String[] args) {
        List<Test> tests = List.of(new Test(1),new Test(2),new Test(3));
        System.out.println(tests.stream().collect(Collectors.summarizingInt(Test::getC)).getSum());

        System.out.println(LocalDateTime.now());
        System.out.println(new Date());

        System.out.println(LocalDateTime.now().plusDays(1).isBefore(LocalDateTime.now()));
        System.out.println(DateUtils.addMinutes(new Date(), 1).compareTo(new Date()));
    }

    static class Test {
        int c;

        public Test(int c) {
            this.c = c;
        }

        public int getC() {
            return c;
        }
    }
}
