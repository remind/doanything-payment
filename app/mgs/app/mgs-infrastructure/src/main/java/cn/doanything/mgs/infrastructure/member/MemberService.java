package cn.doanything.mgs.infrastructure.member;

import cn.doanything.member.facade.personal.PersonalManageFacade;
import cn.doanything.member.facade.personal.dto.PersonalDetailInfo;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

/**
 * @author wxj
 * 2023/12/14
 */
@Component
public class MemberService {

//    @DubboReference
    private PersonalManageFacade personalManageFacade;


    public PersonalDetailInfo query(String memberId) {
        return personalManageFacade.query(memberId);
    }
}
