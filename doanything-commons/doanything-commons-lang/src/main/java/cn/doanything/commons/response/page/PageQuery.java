package cn.doanything.commons.response.page;

/**
 * @author wxj
 * 2024/1/13
 */
public class PageQuery {

    private Paging paging = new Paging();

    public Paging getPaging() {
        return paging;
    }

    public void setPaging(Paging paging) {
        this.paging = paging;
    }
}
