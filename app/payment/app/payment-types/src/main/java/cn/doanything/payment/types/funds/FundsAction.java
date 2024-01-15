package cn.doanything.payment.types.funds;

import lombok.Getter;

/**
 * @author wxj
 * 2024/1/15
 */
@Getter
public enum FundsAction {

    INCREASE("INCREASE", "增加", 3),
    REDUCE("REDUCE","减少", 1);


    private String code;

    private String name;

    /**
     * 执行顺序
     */
    private int sort;

    FundsAction(String code, String name, int sort) {
        this.code = code;
        this.name = name;
        this.sort = sort;
    }

}
