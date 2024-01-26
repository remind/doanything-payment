package cn.doanything.paycore.domain.flux;

import cn.doanything.commons.lang.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * 资产交换单
 *
 * @author wxj
 * 2024/1/20
 */

public class AssetFluxOrder extends Entity {

    /**
     * 支付总单号
     */
    @Setter
    @Getter
    private String paymentId;

    /**
     * 支付订单号
     */
    @Setter
    @Getter
    private String orderId;

    /**
     * 交换ID
     */
    @Setter
    @Getter
    private String fluxOrderId;

    /**
     * 交换状态
     */
    @Setter
    @Getter
    private InstructStatus status;

    private FluxInstructChain first = null;

    private FluxInstructChain last = null;

    private List<String> newFluxInstructIds = new ArrayList<>();

    private List<String> updateFluxInstructIds = new ArrayList<>();
    private List<String> deleteFluxInstructIds = new ArrayList<>();

    public void initFluxInstructs(List<AssetFluxInstruct> assetFluxInstructs) {
        assetFluxInstructs.forEach(this::addFluxInstruct);
    }

    public void addFluxInstruct(AssetFluxInstruct assetFluxInstruct) {
        if (first == null) {
            first = new FluxInstructChain(assetFluxInstruct);
            last = new FluxInstructChain(assetFluxInstruct);
        } else {
            if (last == null) {
                last = new FluxInstructChain(assetFluxInstruct);
                first.setNext(last);
                last.setPrev(first);
            } else {
                FluxInstructChain fluxInstructChain = new FluxInstructChain(assetFluxInstruct);
                fluxInstructChain.setPrev(last);
                last.setNext(fluxInstructChain);
            }
        }
        newFluxInstructIds.add(assetFluxInstruct.getFluxInstructId());
    }

    public void insertFluxInstruct(AssetFluxInstruct assetFluxInstruct, AssetFluxInstruct newAssetFluxInstruct) {
        FluxInstructChain fluxInstructChain = find(assetFluxInstruct);
        FluxInstructChain newFluxInstructChain = new FluxInstructChain(newAssetFluxInstruct);
        newFluxInstructChain.setNext(fluxInstructChain.getNext());
        newFluxInstructChain.setPrev(fluxInstructChain);
        fluxInstructChain.setNext(newFluxInstructChain);
        newFluxInstructIds.add(assetFluxInstruct.getFluxInstructId());
    }

    public void deleteAfterFluxInstruct(AssetFluxInstruct assetFluxInstruct) {
        FluxInstructChain fluxInstructChain = find(assetFluxInstruct);
        FluxInstructChain next = fluxInstructChain.getNext();
        fluxInstructChain.setNext(null);
        while (next != null) {
            deleteFluxInstructIds.add(next.getAssetFluxInstruct().getFluxOrderId());
            next = next.getNext();
        }
    }

    public AssetFluxInstruct find(String fluxInstructId) {
        FluxInstructChain fluxInstructChain = first;
        while (fluxInstructChain != null) {
            if (fluxInstructChain.getAssetFluxInstruct().getFluxInstructId().equals(fluxInstructId)) {
                return fluxInstructChain.getAssetFluxInstruct();
            }
            fluxInstructChain = fluxInstructChain.getNext();
        }
        return null;
    }

    public AssetFluxInstruct getExecuteFluxInstruct() {
        FluxInstructChain fluxInstructChain = first;
        while (fluxInstructChain != null) {
            if (fluxInstructChain.getAssetFluxInstruct().getStatus() == InstructStatus.INIT) {
                return fluxInstructChain.getAssetFluxInstruct();
            }
            fluxInstructChain = fluxInstructChain.getNext();
        }
        return null;
    }

    public List<AssetFluxInstruct> getAllFluxInstructs() {
        List<AssetFluxInstruct> assetFluxInstructs = new ArrayList<>();
        FluxInstructChain fluxInstructChain = first;
        while (fluxInstructChain != null) {
            assetFluxInstructs.add(fluxInstructChain.getAssetFluxInstruct());
            fluxInstructChain = fluxInstructChain.getNext();
        }
        return assetFluxInstructs;
    }



    private FluxInstructChain find(AssetFluxInstruct assetFluxInstruct) {
        FluxInstructChain fluxInstructChain = first;
        while (fluxInstructChain != null) {
            if (fluxInstructChain.getAssetFluxInstruct().getFluxInstructId().equals(assetFluxInstruct.getFluxInstructId())) {
                return fluxInstructChain;
            }
            fluxInstructChain = fluxInstructChain.getNext();
        }
        return null;
    }

    @Getter
    private static class FluxInstructChain {

        private final AssetFluxInstruct assetFluxInstruct;
        @Setter
        private FluxInstructChain prev;
        @Setter
        private FluxInstructChain next;

        public FluxInstructChain(AssetFluxInstruct assetFluxInstruct) {
            this.assetFluxInstruct = assetFluxInstruct;
        }

    }

}
