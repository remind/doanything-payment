package cn.doanything.framework.api.sequence;

/**
 * 序列接口
 * @author wxj
 * 2023/12/10
 */
public interface SequenceService {

    /**
     * 获取下一个值
     * @param sequenceName  序列名称
     * @return
     */
    Long getNext(String sequenceName);
}
