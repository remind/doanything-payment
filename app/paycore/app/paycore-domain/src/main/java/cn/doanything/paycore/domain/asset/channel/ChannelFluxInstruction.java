package cn.doanything.paycore.domain.asset.channel;

import cn.doanything.paycore.domain.flux.FluxInstruction;
import cn.doanything.paycore.types.asset.AssetInfo;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;

/**
 * @author wxj
 * 2024/1/25
 */
public class ChannelFluxInstruction extends FluxInstruction {

    public AssetInfo getAsset() {
        String assetInfo = getAssetInfo();
        return StrUtil.isNotBlank(assetInfo) ? JSONUtil.toBean(assetInfo, AssetInfo.class) : null;
    }

    public void setAsset(AssetInfo asset) {
        setAssetInfo(JSONUtil.toJsonStr(asset));
    }

    public String getClearingAccountNo() {
        return getExtValue("clearingAccountNo");
    }

    public void setClearingAccountNo(String clearingAccountNo) {
        putExtValue("clearingAccountNo", clearingAccountNo);
    }

    public String getClearDate() {
        return getExtValue("clearDate");
    }

    public void setClearDate(String clearDate) {
        putExtValue("clearDate", clearDate);
    }
}
