package cn.doanything.basic.facade.oss;

import cn.doanything.basic.application.oss.FileDownloadService;
import cn.doanything.basic.application.oss.FileUploadService;
import cn.doanything.basic.domain.oss.FileInfo;
import cn.doanything.basic.facade.oss.dto.UploadFile;
import cn.doanything.commons.response.ResponseResult;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.InputStream;

/**
 * @author wxj
 * 2024/1/11
 */
@DubboService
public class OssFacadeImpl implements OssFacade {

    @Autowired
    private FileUploadService fileUploadService;

    @Autowired
    private FileDownloadService fileDownloadService;
    @Override
    public ResponseResult<String> upload(UploadFile uploadFile) {
        FileInfo fileInfo = fileUploadService.upload(uploadFile);
        return ResponseResult.success(fileInfo.getFileId());
    }

    @Override
    public ResponseResult<InputStream> download(String fileId) {
        return ResponseResult.success(fileDownloadService.download(fileId));
    }
}
