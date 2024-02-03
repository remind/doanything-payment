package cn.doanything.trade.facade;

import cn.doanything.trade.application.transfer.TransferService;
import cn.doanything.trade.transfer.TransferFacade;
import cn.doanything.trade.transfer.TransferRequest;
import cn.doanything.trade.transfer.TransferResponse;

/**
 * @author wxj
 * 2024/2/2
 */
public class TransferFacadeImpl implements TransferFacade {

    private TransferService transferService;

    @Override
    public TransferResponse transfer(TransferRequest request) {
        return null;
    }
}
