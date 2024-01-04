package cn.doanything.member.infrastructure.persistence.repository;

import cn.doanything.member.domain.repository.MemberAccountRepository;
import cn.doanything.member.infrastructure.persistence.convertor.MemberAccountDalConvertor;
import cn.doanything.member.infrastructure.persistence.mapper.MemberAccountMapper;
import cn.doanything.member.types.MemberAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wxj
 * 2024/1/4
 */
@Repository
public class MemberAccountRepositoryImpl implements MemberAccountRepository {

    @Autowired
    private MemberAccountDalConvertor dalConvertor;

    @Autowired
    private MemberAccountMapper mapper;

    @Override
    public void store(MemberAccount memberAccount) {
        mapper.insert(dalConvertor.toDo(memberAccount));
    }

    @Override
    public void store(List<MemberAccount> memberAccounts) {
        memberAccounts.forEach(this::store);
    }
}
