package cn.doanything.member.domain.personal;

import cn.doanything.member.domain.Member;
import cn.doanything.member.types.GenderEnum;
import lombok.Data;

/**
 * @author wxj
 * 2023/12/10
 */
@Data
public class PersonalMember extends Member {

    /** 性别 */
    private GenderEnum gender;
}
