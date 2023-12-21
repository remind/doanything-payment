package cn.doanything.account.application.entry;

import cn.doanything.account.facade.dto.AccountingRequest;
import cn.doanything.account.facade.dto.AccountingRequestDetail;
import cn.doanything.account.types.enums.CrDr;
import cn.doanything.commons.lang.types.Money;
import jakarta.annotation.Resource;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author wxj
 * 2023/12/21
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class AccountEntryServiceTest {

    @Resource
    private AccountEntryService accountEntryService;

    @Test
    public void success() {
        AccountingRequest request = new AccountingRequest();
        request.setAccountingDate("20231221");
        request.setRequestNo("123456");
        AccountingRequestDetail requestDetail = new AccountingRequestDetail();
        requestDetail.setAccountNo("200100200110000000000001");
        requestDetail.setAmount(new Money(1));
        requestDetail.setCrDr(CrDr.CREDIT);
        request.setAccountingRequestDetails(Lists.newArrayList(requestDetail));
        accountEntryService.process(request);
    }
}
