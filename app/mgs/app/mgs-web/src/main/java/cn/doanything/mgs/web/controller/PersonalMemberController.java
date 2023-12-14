package cn.doanything.mgs.web.controller;

import cn.doanything.commons.response.ResponseResult;
import cn.doanything.member.facade.personal.dto.PersonalDetailInfo;
import cn.doanything.mgs.infrastructure.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wxj
 * 2023/12/14
 */
@RestController
@RequestMapping("/member/personal/")
public class PersonalMemberController {

    @Autowired
    private MemberService memberService;

    @RequestMapping("query")
    public ResponseResult queryMember(@RequestParam String memberId) {
        if ("1".equals(memberId)) {
            throw new RuntimeException("11");
        }
        PersonalDetailInfo personalDetailInfo = memberService.query(memberId);
        return ResponseResult.success(personalDetailInfo);
    }

}
