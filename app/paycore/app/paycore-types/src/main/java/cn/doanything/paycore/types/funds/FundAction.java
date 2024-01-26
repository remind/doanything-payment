package cn.doanything.paycore.types.funds;

import cn.doanything.commons.enums.CodeEnum;
import lombok.Getter;

/**
 * @author wxj
 * 2024/1/15
 */
@Getter
public enum FundAction implements CodeEnum {

    UNFREEZE("UNFREEZE", "解冻", 4),
    FREEZE("FREEZE", "冻结", 3),
    INCREASE("INCREASE", "增加", 2),
    REDUCE("REDUCE", "减少", 1);

    private String code;

    private String displayName;

    /**
     * 执行顺序
     */
    private int sort;

    FundAction(String code, String displayName, int sort) {
        this.code = code;
        this.displayName = displayName;
        this.sort = sort;
    }

    public FundAction reverse() {
        switch (this) {
            case FREEZE:
                return UNFREEZE;
            case UNFREEZE:
                return FREEZE;
            case INCREASE:
                return REDUCE;
            case REDUCE:
                return INCREASE;
            default:
                return null;
        }
    }

}
