package cn.doanything.member.domain.personal.service.impl;

import cn.doanything.commons.enums.EnableEnum;
import cn.doanything.member.domain.MemberConstants;
import cn.doanything.member.domain.personal.PersonalMember;
import cn.doanything.member.domain.personal.service.PersonalDomainService;
import cn.doanything.member.domain.repository.MemberAccountRepository;
import cn.doanything.member.domain.repository.MemberRepository;
import cn.doanything.member.domain.repository.PersonalMemberRepository;
import cn.doanything.member.domain.repository.PersonalPasswordRepository;
import cn.doanything.member.domain.rpc.account.AccountDTO;
import cn.doanything.member.domain.rpc.account.AccountServiceClient;
import cn.doanything.member.types.MemberAccount;
import cn.doanything.member.types.MemberStatus;
import cn.doanything.member.types.MemberType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    private MemberAccountRepository memberAccountRepository;

    @Autowired
    private PersonalPasswordRepository personalPasswordRepository;

    @Autowired
    private AccountServiceClient accountServiceClient;

    @Override
    public void create(PersonalMember personalMember) {
        personalMember.setMemberId(memberRepository.genMemberId(MemberType.PERSONAL));
        personalMember.setStatus(MemberStatus.NORMAL);
        memberRepository.store(personalMember);
        createAccount(personalMember.getMemberId(), Arrays.asList(MemberConstants.PERSONAL_DEFAULT_ACCOUNT_TYPE));
    }

    private void createAccount(String memberId, List<String> accountTypes) {
        List<AccountDTO> accountDTOS = accountServiceClient.createAccount(memberId, accountTypes);
        List<MemberAccount> memberAccounts = new ArrayList<>();
        accountDTOS.forEach(accountDTO -> {
            MemberAccount account = new MemberAccount();
            account.setMemberId(memberId);
            account.setAccountType(accountDTO.getAccountType());
            account.setStatus(EnableEnum.ENABLE);
            account.setAccountNo(accountDTO.getAccountNo());
            memberAccounts.add(account);
        });
        memberAccountRepository.store(memberAccounts);
    }

}
