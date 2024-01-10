package cn.doanything.member.facade.personal.dto;

import cn.doanything.member.types.personal.PersonalBaseInfo;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 个人用户详情
 * @author wxj
 * 2023/12/11
 */
@Data
public class PersonalDetailInfo extends PersonalBaseInfo {

    /**
     * 用户ID
     */
    private String memberId;

    /**
     * 创建时间
     */
    private LocalDateTime gmtCreate;

    /**
     * 修改时间
     */
    private LocalDateTime gmtModified;
}
