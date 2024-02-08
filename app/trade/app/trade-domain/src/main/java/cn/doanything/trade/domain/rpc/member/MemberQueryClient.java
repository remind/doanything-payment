package cn.doanything.trade.domain.rpc.member;

/**
 * @author wxj
 * 2024/2/8
 */
public interface MemberQueryClient {

    /**
     * 获取默认账户
     * @param memberId
     * @return
     */
    String queryDefaultAccount(String memberId);
}
