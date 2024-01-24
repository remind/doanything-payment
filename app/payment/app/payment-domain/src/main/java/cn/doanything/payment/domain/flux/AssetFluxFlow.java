package cn.doanything.payment.domain.flux;

import cn.doanything.commons.lang.Entity;
import cn.doanything.payment.types.asset.AssetType;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 资产交换流
 * @author wxj
 * 2024/1/24
 */
@Data
public class AssetFluxFlow extends Entity {

    private String fluxOrderId;

    private String fluxFlowId;

    private AssetType assetType;

    private List<String> fundDetailIds = new ArrayList<>();

    private String nextFluxFlowId;

    private int sort;



}
