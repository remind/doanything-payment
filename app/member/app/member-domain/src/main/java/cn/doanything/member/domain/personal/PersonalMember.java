package cn.doanything.member.domain.personal;

import cn.doanything.member.domain.Member;
import cn.doanything.member.types.Gender;
import cn.doanything.member.types.MemberType;
import lombok.Data;

/**
 * @author wxj
 * 2023/12/10
 */
@Data
public class PersonalMember extends Member {

    /**
     * 性别
     */
    private Gender gender;

    @Override
    public MemberType getMemberType() {
        return MemberType.PERSONAL;
    }
}
