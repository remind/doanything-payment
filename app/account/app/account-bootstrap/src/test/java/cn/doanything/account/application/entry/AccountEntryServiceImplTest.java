package cn.doanything.account.application.entry;
import cn.doanything.account.application.convertor.AccountingRequestConvertor;
import cn.doanything.account.application.entry.impl.AccountEntryServiceImpl;
import cn.doanything.account.domain.detail.BufferedDetail;
import cn.doanything.account.domain.repository.BufferedDetailRepository;
import cn.doanything.account.types.enums.BufferDetailStatus;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.transaction.support.TransactionTemplate;

import static org.mockito.Mockito.*;

public class AccountEntryServiceImplTest {
    @Mock
    private TransactionTemplate transactionTemplate;

    @Mock
    private BufferedDetailRepository bufferedDetailRepository;

    @Mock
    private AccountingRequestConvertor requestConvertor;

    @InjectMocks
    private AccountEntryServiceImpl accountEntryService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testProcessBufferedDetail() {
        String voucherNo = "12345";

        BufferedDetail bufferedDetail = new BufferedDetail();
        bufferedDetail.setStatus(BufferDetailStatus.INIT);
        bufferedDetail.setVoucherNo(voucherNo);
        bufferedDetail.setAccountNo("40010010011560001");

        when(bufferedDetailRepository.lock(voucherNo)).thenReturn(bufferedDetail);

        accountEntryService.processBufferedDetail(voucherNo);

        verify(requestConvertor).toAccountDetail(bufferedDetail);
        verify(transactionTemplate).executeWithoutResult(any());
    }
}
