package cn.doanything.account;

import cn.doanything.account.domain.AccountDomainConstants;
import org.apache.commons.lang3.StringUtils;

/**
 * @author wxj
 * 2023/12/22
 */
public class MainTest {

    public static void main(String[] args) {
        int i = 125;
        System.out.println(String.format("%0 " + String.valueOf(AccountDomainConstants.OUTER_ACCOUNT_NO_MAX_INC).length() +"d",i));
    }
}
