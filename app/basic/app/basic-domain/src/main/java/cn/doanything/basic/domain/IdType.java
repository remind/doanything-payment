package cn.doanything.basic.domain;

import cn.doanything.commons.enums.BizIdType;

/**
 * ID业务类型
 * @author wxj
 * 2024/1/9
 */
public enum IdType implements BizIdType {

    MNS_MESSAGE_DETAIL("1", "01", "seq_message_detail_id","消息详情"),
    OSS_FILE_INFO("2", "01", "seq_oss_file_info_id","文件ID"),
    ;

    /**
     * 1位数字
     */
    private final String parentCode;

    /**
     * 2位数字
     */
    private final String code;

    private final String seqName;

    private final String name;

    IdType(String parentCode, String code, String seqName, String name) {
        this.parentCode = parentCode;
        this.code = code;
        this.seqName = seqName;
        this.name = name;
    }

    public String getParentCode() {
        return parentCode;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    @Override
    public String getBizTypeCode() {
        return parentCode + code;
    }

    @Override
    public String getSeqName() {
        return seqName;
    }

}
