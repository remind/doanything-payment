package cn.doanything.commons.response.page;

/**
 * 分页信息
 * @author wxj
 * 2024/1/12
 */
public class Paging {

    /**
     * 每页显示条数，默认 10
     */
    private long size = 10;

    /**
     * 当前页
     */
    private long current = 1;

    public Paging() {
    }
    public Paging(long size, long current) {
        this.size = size;
        this.current = current;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getCurrent() {
        return current;
    }

    public void setCurrent(long current) {
        this.current = current;
    }
}
