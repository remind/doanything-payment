package cn.doanything.member.domain.personal.service.impl;

import cn.doanything.commons.exceptions.BizException;
import cn.doanything.commons.lang.utils.AssertUtil;
import cn.doanything.member.domain.MemberConstants;
import cn.doanything.member.domain.MemberResultCode;
import cn.doanything.member.domain.personal.PersonalMember;
import cn.doanything.member.domain.personal.PersonalPassword;
import cn.doanything.member.domain.personal.service.PersonalDomainService;
import cn.doanything.member.domain.repository.MemberRepository;
import cn.doanything.member.domain.repository.PersonalMemberRepository;
import cn.doanything.member.domain.repository.PersonalPasswordRepository;
import cn.doanything.member.types.*;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wxj
 * 2023/12/10
 */
@Service
public class PersonalDomainServiceImpl implements PersonalDomainService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PersonalMemberRepository personalMemberRepository;

    @Autowired
    private PersonalPasswordRepository personalPasswordRepository;

    @Override
    public void create(PersonalMember personalMember) {
        personalMember.setMemberId(memberRepository.genMemberId(MemberType.PERSONAL));
        personalMember.setStatus(MemberStatus.NORMAL);
        memberRepository.store(personalMember);
    }

    @Override
    public void loginValidate(String loginName, String password, PasswordType type) {
        PersonalMember personalMember = personalMemberRepository.loadByLoginName(loginName);
        AssertUtil.isNotNull(personalMember, MemberResultCode.MEMBER_NOT_EXISTS);
        List<PersonalPassword> personalPasswords = personalPasswordRepository.load(personalMember.getMemberId(), PasswordUseType.LOGIN_PASSWORD);

        long errorCount = personalPasswords.stream().collect(Collectors.summarizingInt(PersonalPassword::getErrorCount)).getSum();
        PersonalPassword personalPassword = personalPasswords.stream().filter(p -> p.getType().equals(type)).findFirst().orElse(null);

        AssertUtil.isNotNull(personalPassword, MemberResultCode.PASSWORD_ERROR, "没有密码");
        AssertUtil.isTrue(personalPassword.getStatus().equals(PasswordStatus.LOCK), MemberResultCode.PASSWORD_LOCKED);

        if (errorCount >= MemberConstants.PWD_MAX_ERROR_COUNT
                && DateUtils.isSameDay(new Date(), personalPassword.getLastErrorDate())) {
            throw new BizException(MemberResultCode.PASSWORD_LOCKED, "已失败" + MemberConstants.PWD_MAX_ERROR_COUNT
                    + "次，已锁定，请明天再试");
        }

        if (personalPassword.getPassword().equals(password)) {
            clearErrorCount(personalPasswords);
        } else {
            personalPassword.incErrorCount();
            personalPasswordRepository.reStore(personalPassword);
            if (personalPassword.getErrorCount() >= MemberConstants.PWD_MAX_ERROR_COUNT) {
                throw new BizException(MemberResultCode.PASSWORD_LOCKED, "已失败" + MemberConstants.PWD_MAX_ERROR_COUNT
                        + "次，已锁定，请明天再试");
            } else {
                throw new BizException(MemberResultCode.PASSWORD_ERROR, "密码错误，剩余"
                        + (MemberConstants.PWD_MAX_ERROR_COUNT - personalPassword.getErrorCount()) + "次");
            }
        }
    }

    private void clearErrorCount(List<PersonalPassword> personalPasswords) {
        personalPasswords.forEach(personalPassword -> {
            personalPassword.clearErrorCount();
            personalPasswordRepository.reStore(personalPassword);
        });
    }
}
