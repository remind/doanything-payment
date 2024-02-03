package cn.doanything.trade.transfer;

/**
 * @author wxj
 * 2024/2/2
 */
public interface TransferFacade {

    /**
     * 转账
     * @param request
     * @return
     */
    TransferResponse transfer(TransferRequest request);
}
