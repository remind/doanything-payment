package cn.doanything.basic.facade.oss;

import cn.doanything.basic.facade.oss.dto.UploadFile;
import cn.doanything.commons.response.ResponseResult;
import cn.doanything.framework.BaseTestBootStarter;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

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
        File file = new File("/Users/wxj/Desktop/2.html");
        uploadFile.setFileName(file.getName());
        uploadFile.setInput(new FileInputStream(file));
        uploadFile.setSize(file.length());
        ResponseResult<String> result = ossFacade.upload(uploadFile);
        System.out.println(result.getData());
        Assert.assertTrue(result.isSuccess());
    }
}
