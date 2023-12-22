package cn.doanything.account.facade.manger;

import cn.doanything.account.facade.manager.AccountManagerFacade;
import cn.doanything.account.facade.manager.dto.OuterAccountAddRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Currency;

/**
 * @author wxj
 * 2023/12/22
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class AccountManagerFacadeTest {

    @Autowired
    private AccountManagerFacade accountManagerFacade;

    @Test
    public void testAddOuterAccount() {
        OuterAccountAddRequest request = new OuterAccountAddRequest();
        request.setMemberId("100000002");
        request.setAccountType("101");
        request.setAccountName("基本户");
        accountManagerFacade.addOuterAccount(request);
    }
}
