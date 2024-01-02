package cn.doanything.member.infrastructure.persistence.repository;

import cn.doanything.member.domain.personal.PersonalMember;
import cn.doanything.member.domain.repository.PersonalMemberRepository;
import cn.doanything.member.infrastructure.persistence.convertor.MemberDalConvertor;
import cn.doanything.member.infrastructure.persistence.dataobject.MemberDO;
import cn.doanything.member.infrastructure.persistence.mapper.MemberMapper;
import cn.doanything.member.infrastructure.persistence.mapper.PersonalMemberMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

/**
 * @author wxj
 * 2024/1/2
 */
@Repository
public class PersonalMemberRepositoryImpl implements PersonalMemberRepository {

    @Autowired
    private MemberDalConvertor memberDalConvertor;
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private PersonalMemberMapper personalMemberMapper;

    @Override
    public PersonalMember loadByLoginName(String loginName) {
        Assert.notNull(loginName, "loginName can not be null");
        LambdaQueryWrapper<MemberDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(MemberDO::getPhone, loginName).or(w -> w.eq(MemberDO::getEmail, loginName));
        MemberDO memberDO = memberMapper.selectOne(wrapper);
        if (memberDO != null) {
            return memberDalConvertor.toPersonalMember(memberDO, personalMemberMapper.selectById(memberDO.getMemberId()));
        }
        return null;
    }
}
