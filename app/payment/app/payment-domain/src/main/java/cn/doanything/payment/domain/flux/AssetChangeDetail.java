package cn.doanything.payment.domain.flux;

import cn.doanything.commons.lang.Entity;
import lombok.Data;

/**
 * @author wxj
 * 2024/1/20
 */
@Data
public class AssetChangeDetail extends Entity {

    /**
     * 交换
     */
    private String fluxId;

    private String detailId;
}
