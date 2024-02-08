package cn.doanything.trade.domain.acquiring.service.impl;

import cn.doanything.trade.domain.acquiring.service.AcquiringDomainService;
import cn.doanything.trade.domain.rpc.member.MemberQueryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wxj
 * 2024/2/8
 */
@Service
public class AcquiringDomainServiceImpl implements AcquiringDomainService {

    @Autowired
    private MemberQueryClient memberQueryClient;
    @Override
    public String getSellerAccount(String partnerId, String sellerId) {
        return memberQueryClient.queryDefaultAccount(sellerId);
    }
}
