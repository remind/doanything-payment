package cn.doanything.framework.api.sequence;

import cn.doanything.commons.enums.BizIdType;
import cn.doanything.commons.enums.SystemCodeEnums;

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

    /**
     * 根据会员ID、系统编码、业务类型获取ID
     * @param memberId
     * @param systemCodeEnums
     * @param idType
     * @return
     */
    String getId(String memberId, SystemCodeEnums systemCodeEnums, BizIdType idType);

    /**
     * 根据分表位ID、系统编码、业务类型获取ID
     * @param routeId
     * @param systemCodeEnums
     * @param idType
     * @return
     */
    String getIdByRouteId(String routeId, SystemCodeEnums systemCodeEnums, BizIdType idType);
}
