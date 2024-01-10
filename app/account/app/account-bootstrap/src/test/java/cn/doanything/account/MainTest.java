package cn.doanything.account;

import java.time.LocalDateTime;
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

        System.out.println(LocalDateTime.now().plusDays(1).isBefore(LocalDateTime.now()));
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
