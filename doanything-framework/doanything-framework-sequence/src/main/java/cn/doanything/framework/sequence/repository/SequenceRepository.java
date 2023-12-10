package cn.doanything.framework.sequence.repository;

import cn.doanything.framework.sequence.domain.Sequence;

import java.util.List;

/**
 * 序列仓储
 * @author wxj
 * 2023/12/10
 */
public interface SequenceRepository {

    List<Sequence> loadAll();

    Sequence lock(String var1);

    void update(String var1, Long var2, Long var3);
}
