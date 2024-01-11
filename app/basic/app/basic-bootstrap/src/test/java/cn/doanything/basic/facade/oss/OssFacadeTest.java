package cn.doanything.basic.facade.oss;

import cn.doanything.basic.facade.oss.dto.UploadFile;
import cn.doanything.commons.exceptions.BizException;
import cn.doanything.commons.response.ResponseResult;
import cn.doanything.framework.BaseTestBootStarter;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;

/**
 * @author wxj
 * 2024/1/11
 */
public class OssFacadeTest extends BaseTestBootStarter {

    @Autowired
    private OssFacade ossFacade;

    @Test
    public void testUpload() throws FileNotFoundException {
        UploadFile uploadFile = new UploadFile();
        uploadFile.setScene("default");
        uploadFile.setMemberId(randomPersonalMemberId());
        File file = new File(System.getProperty("user.home") + "/doanything/oss/testuploadfile/2.txt");
        uploadFile.setFileName(file.getName());
        uploadFile.setInput(new FileInputStream(file));
        uploadFile.setSize(file.length());
        ResponseResult<String> result = ossFacade.upload(uploadFile);
        System.out.println(result.getData());
        Assert.assertTrue(result.isSuccess());
    }

    @Test
    public void testDownload() {
        ResponseResult<InputStream> result = ossFacade.download("202401110022019300000081");
        Assert.assertTrue(result.isSuccess());

        File file = FileUtil.newFile(System.getProperty("user.home") + "/doanything/oss/testdownloadfile/2.txt");
        file.deleteOnExit();
        if (!file.getParentFile().exists()) {
            FileUtil.mkdir(file.getParentFile());
        }
        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            IoUtil.copy(result.getData(), outputStream);
            result.getData().close();
            outputStream.close();
        } catch (Exception e) {
            throw new BizException("文件保存异常");
        }
    }
}
