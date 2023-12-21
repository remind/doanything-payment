package cn.doanything.account.domain.repository;

import cn.doanything.account.domain.detail.BufferedDetail;

import java.util.List;

/**
 * @author wxj
 * 2023/12/21
 */
public interface BufferedDetailRepository {

    void store(List<BufferedDetail> bufferedDetails);
}
