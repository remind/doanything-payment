package cn.doanything.member.facade.personal;

import cn.doanything.member.facade.personal.dto.PersonalAddRequest;
import cn.doanything.member.facade.personal.dto.PersonalDetailInfo;

/**
 * 个人会员管理
 * @author wxj
 * 2023/12/11
 */
public interface PersonalManageFacade {

    /**
     * 创建
     * @param request
     * @return
     */
    PersonalDetailInfo create(PersonalAddRequest request);

    /**
     * 查询个人会员
     * @param memberId
     * @return
     */
    PersonalDetailInfo query(String memberId);
}
