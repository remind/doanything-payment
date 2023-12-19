package cn.doanything.account.application.Processor;

import cn.doanything.account.application.processor.AccountOperationGroup;
import cn.doanything.account.application.processor.UpdateBalanceProcessor;
import cn.doanything.account.application.processor.impl.OuterUpdateBalanceProcessor;
import cn.doanything.account.domain.detail.AccountDetail;
import jakarta.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author wxj
 * 2023/12/18
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class OuterUpdateBalanceProcessorTest {

    @Resource(type = OuterUpdateBalanceProcessor.class)
    UpdateBalanceProcessor updateBalanceProcessor;

    @Test
    public void updateSuccess() {
        String accountNo = "200100200110000000000001";
        AccountOperationGroup operationGroup = new AccountOperationGroup();
        operationGroup.setAccountNo(accountNo);
        AccountDetail accountDetail = new AccountDetail();
        accountDetail.setSeqNo(123l);
        operationGroup.getDetails().add(accountDetail);
        updateBalanceProcessor.update(operationGroup);
    }
}
