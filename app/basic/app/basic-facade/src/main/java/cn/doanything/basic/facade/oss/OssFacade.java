package cn.doanything.basic.facade.oss;

import cn.doanything.basic.facade.oss.dto.UploadFile;
import cn.doanything.commons.response.ResponseResult;

/**
 * @author wxj
 * 2024/1/11
 */
public interface OssFacade {

    /**
     * 上传文件
     * @param uploadFile
     * @return
     */
    ResponseResult<String> upload(UploadFile uploadFile);
}
