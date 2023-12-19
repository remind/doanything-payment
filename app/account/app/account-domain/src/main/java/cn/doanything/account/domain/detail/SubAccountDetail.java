package cn.doanything.account.domain.detail;

import lombok.Data;

/**
 * @author wxj
 * 2023/12/17
 */
@Data
public class SubAccountDetail extends AbstractDetail {

    /**
     * 资金类型
     */
    private String fundType;
}