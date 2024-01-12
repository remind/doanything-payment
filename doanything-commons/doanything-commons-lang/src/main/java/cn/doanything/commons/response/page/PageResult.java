package cn.doanything.commons.response.page;

import java.util.List;

/**
 * 分页结果
 * @author wxj
 * 2024/1/12
 */
public class PageResult<T> {

    /**
     * 分页信息
     */
    private Paging paging;

    /**
     * 总条数
     */
    private long total;

    /**
     * 数据
     */
    private List<T> records;

    public Paging getPaging() {
        return paging;
    }

    public void setPaging(Paging paging) {
        this.paging = paging;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }
}
