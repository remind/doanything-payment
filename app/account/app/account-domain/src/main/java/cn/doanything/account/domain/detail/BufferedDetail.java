package cn.doanything.account.domain.detail;

import cn.doanything.account.types.enums.BufferDetailStatus;
import lombok.Data;

/**
 * 缓冲明细
 *
 * @author wxj
 * 2023/12/19
 */
@Data
public class BufferedDetail extends AccountDetail {

    /**
     * 状态
     */
    private BufferDetailStatus status;

    /**
     * 执行次数
     **/
    private Integer executeCount;
}
