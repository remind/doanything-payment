package cn.doanything.commons.terminal;

import cn.doanything.commons.enums.CodeEnum;
import lombok.Getter;


/**
 * @author wxj
 * 2024/2/3
 */
@Getter
public enum Terminal implements CodeEnum {

    WEB("WEB", "浏览器"),
    GATEWAY("GATEWAY", "网关"),
    MOBILE("MOBILE", "移动端");

    private String code;

    private String displayName;

    Terminal(String code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }
}
