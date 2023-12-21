package cn.doanything.account.application.entry;

import cn.doanything.account.facade.dto.AccountingRequest;
import cn.doanything.account.facade.dto.EntryDetail;
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
        EntryDetail entryDetail1 = new EntryDetail();
        entryDetail1.setVoucherNo("123");
        entryDetail1.setAccountNo("200100200110000000000001");
        entryDetail1.setSuiteNo("1");
        entryDetail1.setAmount(new Money(1));
        entryDetail1.setCrDr(CrDr.CREDIT);
        entryDetail1.setMemo("测试入账1");

        EntryDetail entryDetail2 = new EntryDetail();
        entryDetail2.setVoucherNo("456");
        entryDetail2.setAccountNo("200100200110000000100001");
        entryDetail2.setSuiteNo("1");
        entryDetail2.setAmount(new Money(1));
        entryDetail2.setCrDr(CrDr.DEBIT);
        entryDetail2.setMemo("测试入账2");

        request.setEntryDetails(Lists.newArrayList(entryDetail1, entryDetail2));
        accountEntryService.process(request);
    }
}
