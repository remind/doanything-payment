package cn.doanything.member.types.personal;

import cn.doanything.member.types.Gender;
import lombok.Data;

import java.io.Serializable;

/**
 * 个人会员基础信息
 * @author wxj
 * 2023/12/11
 */
@Data
public class PersonalBaseInfo implements Serializable {

    /**
     * 会员名称
     */
    private String memberName;

    /**
     * 性别
     */
    private Gender gender;
}
