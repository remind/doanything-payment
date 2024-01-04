package cn.doanything.account.facade.manger;

import cn.doanything.account.facade.manager.AccountManagerFacade;
import cn.doanything.account.facade.manager.dto.InnerAccountAddRequest;
import cn.doanything.account.facade.manager.dto.OuterAccountAddRequest;
import cn.doanything.commons.response.ResponseResult;
import cn.doanything.commons.response.GlobalResultCode;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
    public void testAddOuterAccountFail() {
        OuterAccountAddRequest request = new OuterAccountAddRequest();
        request.setMemberId("100000004");
        request.setAccountType("101");
        request.setAccountName("基本户");
        ResponseResult<String> result = accountManagerFacade.createOuterAccount(request);
        Assert.assertEquals(GlobalResultCode.FAIL.getCode(), result.getCode());
    }

    @Test
    public void testAddInnerAccountSuccess() {
        InnerAccountAddRequest request = new InnerAccountAddRequest();
        request.setAccountName("测试账户");
        request.setCurrencyCode("CNY");
        request.setTitleCode("4001001001");
        request.setMemo("测试账户备注");
        ResponseResult<String> result = accountManagerFacade.createInnerAccount(request);
        Assert.assertEquals(GlobalResultCode.SUCCESS.getCode(), result.getCode());
    }
}
