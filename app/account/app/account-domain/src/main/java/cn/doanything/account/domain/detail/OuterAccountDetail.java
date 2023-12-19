package cn.doanything.account.domain.detail;

import lombok.Data;

import java.util.List;

/**
 * @author wxj
 * 2023/12/16
 */
@Data
public class OuterAccountDetail extends AccountDetail {

    /**
     * 资金类型
     */
    private String fundType;

    /**
     * 会员ID
     */
    private String memberId;

    /**
     * 子户明细
     */
    private List<OuterSubAccountDetail> outerSubAccountDetails;
}
