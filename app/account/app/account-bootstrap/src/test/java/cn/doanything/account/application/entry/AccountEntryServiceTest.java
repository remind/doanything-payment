package cn.doanything.account.application.entry;

import cn.doanything.account.facade.dto.AccountingRequest;
import cn.doanything.account.facade.dto.EntryDetail;
import cn.doanything.account.types.enums.CrDr;
import cn.doanything.commons.lang.types.Money;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

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
    public void outerSuccess() {
        AccountingRequest request = new AccountingRequest();
        request.setAccountingDate("20231221");
        request.setRequestNo(getUUID());
        EntryDetail entryDetail1 = new EntryDetail();
        entryDetail1.setVoucherNo(getUUID());
        entryDetail1.setAccountNo("200100200110000000215600001");
        entryDetail1.setSuiteNo("1");
        entryDetail1.setAmount(new Money(1));
        entryDetail1.setCrDr(CrDr.CREDIT);
        entryDetail1.setMemo("测试入账1");

        EntryDetail entryDetail2 = new EntryDetail();
        entryDetail2.setVoucherNo(getUUID());
        entryDetail2.setAccountNo("200100200110000000315600001");
        entryDetail2.setSuiteNo("1");
        entryDetail2.setAmount(new Money(1));
        entryDetail2.setCrDr(CrDr.DEBIT);
        entryDetail2.setMemo("测试入账2");

        request.setEntryDetails(Lists.newArrayList(entryDetail1, entryDetail2));
        accountEntryService.process(request);
    }

    @Test
    public void innerSuccess() {
        AccountingRequest request = new AccountingRequest();
        request.setAccountingDate("20231221");
        request.setRequestNo(getUUID());
        EntryDetail entryDetail1 = new EntryDetail();
        entryDetail1.setVoucherNo(getUUID());
        entryDetail1.setAccountNo("200100200110000000215600001");
        entryDetail1.setSuiteNo("1");
        entryDetail1.setAmount(new Money(1));
        entryDetail1.setCrDr(CrDr.CREDIT);
        entryDetail1.setMemo("测试入账1");

        EntryDetail entryDetail2 = new EntryDetail();
        entryDetail2.setVoucherNo(getUUID());
        entryDetail2.setAccountNo("40010010011560002");
        entryDetail2.setSuiteNo("1");
        entryDetail2.setAmount(new Money(1));
        entryDetail2.setCrDr(CrDr.DEBIT);
        entryDetail2.setMemo("测试入账2");

        request.setEntryDetails(Lists.newArrayList(entryDetail1, entryDetail2));
        accountEntryService.process(request);
    }

    @Test
    public void innerBufferSuccess() {
        AccountingRequest request = new AccountingRequest();
        request.setAccountingDate("20231221");
        request.setRequestNo(getUUID());
        EntryDetail entryDetail1 = new EntryDetail();
        entryDetail1.setVoucherNo(getUUID());
        entryDetail1.setAccountNo("200100200110000000215600001");
        entryDetail1.setSuiteNo("1");
        entryDetail1.setAmount(new Money(1));
        entryDetail1.setCrDr(CrDr.CREDIT);
        entryDetail1.setMemo("测试入账1");

        EntryDetail entryDetail2 = new EntryDetail();
        entryDetail2.setVoucherNo(getUUID());
        entryDetail2.setAccountNo("40010010011560001");
        entryDetail2.setSuiteNo("1");
        entryDetail2.setAmount(new Money(1));
        entryDetail2.setCrDr(CrDr.DEBIT);
        entryDetail2.setMemo("测试入账2");

        request.setEntryDetails(Lists.newArrayList(entryDetail1, entryDetail2));
        accountEntryService.process(request);
    }

    @Test
    public void testProcessBuffer() {
        accountEntryService.processBufferedDetail("b47a16164f8649ff992741d5ca17ac23");
    }


    private String getUUID() {
        String uuid = UUID.randomUUID().toString();
        return uuid.replace("-","").substring(0,32);
    }
}
