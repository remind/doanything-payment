package cn.doanything.member.domain.personal.service.impl;

import cn.doanything.commons.exceptions.BizException;
import cn.doanything.commons.lang.utils.AssertUtil;
import cn.doanything.member.domain.MemberConstants;
import cn.doanything.member.domain.MemberResultCode;
import cn.doanything.member.domain.personal.PersonalMember;
import cn.doanything.member.domain.personal.PersonalPassword;
import cn.doanything.member.domain.personal.service.PasswordDomainService;
import cn.doanything.member.domain.repository.MemberRepository;
import cn.doanything.member.domain.repository.PersonalMemberRepository;
import cn.doanything.member.domain.repository.PersonalPasswordRepository;
import cn.doanything.member.types.PasswordStatus;
import cn.doanything.member.types.PasswordType;
import cn.doanything.member.types.PasswordUseType;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wxj
 * 2024/1/2
 */
@Service
public class PasswordDomainServiceImpl implements PasswordDomainService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PersonalMemberRepository personalMemberRepository;

    @Autowired
    private PersonalPasswordRepository personalPasswordRepository;

    @Override
    public void create(String memberId, PasswordUseType useType, PasswordType type, String password) {
        PersonalPassword personalPassword = personalPasswordRepository.load(memberId, useType, type);
        if (personalPassword == null) {
            personalPassword = new PersonalPassword();
            personalPassword.setMemberId(memberId);
            personalPassword.setUseType(useType);
            personalPassword.setType(type);
            personalPassword.setPassword(password);
            personalPassword.setStatus(PasswordStatus.NORMAL);
            personalPasswordRepository.store(personalPassword);
        } else {
            AssertUtil.isFalse(personalPassword.getStatus().equals(PasswordStatus.LOCK), MemberResultCode.PASSWORD_LOCKED);
            personalPassword.setPassword(password);
            personalPasswordRepository.reStore(personalPassword);
        }
    }

    @Override
    public void change(String memberId, PasswordUseType useType, PasswordType type, String oldPassword, String newPassword) {
        PersonalPassword personalPassword = personalPasswordRepository.load(memberId, useType, type);
        AssertUtil.isNotNull(personalPassword, MemberResultCode.PASSWORD_NOT_EXISTS);
        AssertUtil.isTrue(personalPassword.getPassword().equals(oldPassword), MemberResultCode.PASSWORD_ERROR);
        AssertUtil.isFalse(personalPassword.getStatus().equals(PasswordStatus.LOCK), MemberResultCode.PASSWORD_LOCKED);
        personalPassword.setPassword(newPassword);
        personalPasswordRepository.reStore(personalPassword);
    }

    @Override
    public void loginValidate(String loginName, PasswordType type, String password) {
        PersonalMember personalMember = personalMemberRepository.loadByLoginName(loginName);
        AssertUtil.isNotNull(personalMember, MemberResultCode.MEMBER_NOT_EXISTS);
        List<PersonalPassword> personalPasswords = personalPasswordRepository.load(personalMember.getMemberId(), PasswordUseType.LOGIN_PASSWORD);

        PersonalPassword currentPassword = personalPasswords.stream().filter(p -> p.getType().equals(type)).findFirst().orElse(null);
        validate(personalPasswords, currentPassword);

        if (currentPassword.getPassword().equals(password)) {
            clearErrorCount(personalPasswords);
        } else {
            currentPassword.incErrorCount();
            personalPasswordRepository.reStore(currentPassword);
            if (currentPassword.getErrorCount() >= MemberConstants.PWD_MAX_ERROR_COUNT) {
                throw new BizException(MemberResultCode.PASSWORD_LOCKED, "已失败" + MemberConstants.PWD_MAX_ERROR_COUNT
                        + "次，已锁定，请明天再试");
            } else {
                throw new BizException(MemberResultCode.PASSWORD_ERROR, "密码错误，剩余"
                        + (MemberConstants.PWD_MAX_ERROR_COUNT - currentPassword.getErrorCount()) + "次");
            }
        }
    }

    private void validate(List<PersonalPassword> personalPasswords, PersonalPassword currentPassword) {
        AssertUtil.isNotNull(currentPassword, MemberResultCode.PASSWORD_ERROR, "没有密码");
        AssertUtil.isFalse(currentPassword.getStatus().equals(PasswordStatus.LOCK), MemberResultCode.PASSWORD_LOCKED);

        long errorCount = personalPasswords.stream().collect(Collectors.summarizingInt(PersonalPassword::getErrorCount)).getSum();
        if (errorCount >= MemberConstants.PWD_MAX_ERROR_COUNT
                && DateUtils.isSameDay(new Date(), currentPassword.getLastErrorDate())) {
            throw new BizException(MemberResultCode.PASSWORD_LOCKED, "已失败" + MemberConstants.PWD_MAX_ERROR_COUNT
                    + "次，已锁定，请明天再试");
        }
    }

    private void clearErrorCount(List<PersonalPassword> personalPasswords) {
        personalPasswords.forEach(personalPassword -> {
            if (personalPassword.clearErrorCount()) {
                personalPasswordRepository.reStore(personalPassword);
            }
        });
    }
}
