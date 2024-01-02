package cn.doanything.member.facade.personal;

import cn.doanything.commons.exceptions.BizException;
import cn.doanything.commons.lang.utils.AssertUtil;
import cn.doanything.commons.response.GlobalResultCode;
import cn.doanything.commons.response.ResponseResult;
import cn.doanything.member.domain.personal.service.PasswordDomainService;
import cn.doanything.member.types.PasswordType;
import cn.doanything.member.types.PasswordUseType;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @author wxj
 * 2024/1/1
 */
@DubboService
public class PasswordFacadeImpl implements PasswordFacade {

    @Autowired
    private PasswordDomainService passwordDomainService;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Override
    public ResponseResult<String> create(String memberId, PasswordUseType useType, PasswordType type, String password) {
        AssertUtil.isNotNull(memberId, GlobalResultCode.ILLEGAL_PARAM, "memberId不能为空");
        AssertUtil.isNotNull(useType, GlobalResultCode.ILLEGAL_PARAM, "useType不能为空");
        AssertUtil.isNotNull(password, GlobalResultCode.ILLEGAL_PARAM, "password不能为空");
        return transactionTemplate.execute(status -> {
            try {
                passwordDomainService.create(memberId, useType, type, password);
            } catch (BizException e) {
                return ResponseResult.fail(e.getResultCode(), e.getMessage());
            }
            return ResponseResult.success();
        });
    }

    @Override
    public ResponseResult<String> loginValidate(String loginName, PasswordType type, String password) {
        AssertUtil.isNotNull(loginName, GlobalResultCode.ILLEGAL_PARAM, "loginName不能为空");
        AssertUtil.isNotNull(type, GlobalResultCode.ILLEGAL_PARAM, "type不能为空");
        AssertUtil.isNotNull(password, GlobalResultCode.ILLEGAL_PARAM, "password不能为空");
        return transactionTemplate.execute(status -> {
            try {
                passwordDomainService.loginValidate(loginName, type, password);
            } catch (BizException e) {
                return ResponseResult.fail(e.getResultCode(), e.getMessage());
            }
            return ResponseResult.success();
        });
    }
}
