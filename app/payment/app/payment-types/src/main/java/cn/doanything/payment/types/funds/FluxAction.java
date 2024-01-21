package cn.doanything.payment.types.funds;

import cn.doanything.commons.enums.CodeEnum;
import lombok.Getter;

/**
 * @author wxj
 * 2024/1/15
 */
@Getter
public enum FluxAction implements CodeEnum {

    INCREASE("INCREASE", "增加", 3),
    REDUCE("REDUCE", "减少", 1);


    private String code;

    private String displayName;

    /**
     * 执行顺序
     */
    private int sort;

    FluxAction(String code, String displayName, int sort) {
        this.code = code;
        this.displayName = displayName;
        this.sort = sort;
    }

}
