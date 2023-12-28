package cn.doanything.account.facade.accounting;

import cn.doanything.account.domain.accounting.AccountTitle;
import cn.doanything.account.domain.accounting.service.AccountTitleDomainService;
import cn.doanything.account.domain.repository.AccountTitleRepository;
import cn.doanything.account.facade.accounting.convertor.AccountTitleConvertor;
import cn.doanything.account.facade.accounting.dto.AccountTitleAddRequest;
import cn.doanything.commons.exceptions.BizException;
import cn.doanything.commons.lang.utils.AssertUtil;
import cn.doanything.commons.response.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author wxj
 * 2023/12/16
 */
@Slf4j
public class AccountTitleManagerFacadeImpl implements AccountTitleManagerFacade {

    @Autowired
    private AccountTitleRepository accountTitleRepository;

    @Autowired
    private AccountTitleConvertor accountTitleConvertor;

    @Autowired
    private AccountTitleDomainService accountTitleDomainService;

    @Override
    public ResponseResult<String> createAccountTitle(AccountTitleAddRequest request) {
        String parentTitleCode = request.getParentTitleCode();
        try {
            AccountTitle parentAccountTitle = null;
            if (StringUtils.isNotBlank(parentTitleCode)) {
                parentAccountTitle = accountTitleRepository.load(parentTitleCode);
                AssertUtil.isNotNull(parentAccountTitle, "父科目为空");
            }
            AccountTitle accountTitle = accountTitleConvertor.toEntity(request);
            accountTitleDomainService.setAccountTitleLevel(accountTitle, parentAccountTitle);
            accountTitleRepository.store(accountTitle);
        } catch (BizException e) {
            log.info("新增科目异常,titleCode={}", request.getTitleCode());
            return ResponseResult.fail(e.getMessage());
        }
        return ResponseResult.success();
    }

    @Override
    public ResponseResult<String> updateAccountTitle(AccountTitleAddRequest request) {
        return null;
    }
}
