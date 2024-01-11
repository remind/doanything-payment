import cn.doanything.commons.exceptions.BizException;
import cn.hutool.crypto.SecureUtil;

import java.io.*;

/**
 * @author wxj
 * 2024/1/7
 */
public class MainTest {
    public static void main(String[] args) throws IOException {
        File file = new File("/Users/wxj/Desktop/2.txt");
        InputStream inputStream = new FileInputStream(file);

        inputStream = convert(inputStream);
        System.out.println(getHash(inputStream));
        System.out.println(getHash(inputStream));
        System.out.println(getHash(inputStream));

        System.out.println(System.getProperty("user.home"));
        File file1 = new File("~/Desktop/2.txt");
        System.out.println(file1.exists());

    }

    private static InputStream convert(InputStream inputStream) {
        if (!inputStream.markSupported()) {
            // 如果不支持mark，则转换为BufferedInputStream
            return new BufferedInputStream(inputStream);
        }
        return inputStream;
    }

    private static String getHash(InputStream input) {
        try {
            if (!input.markSupported()) {
                // 如果不支持mark，则转换为BufferedInputStream
                input = new BufferedInputStream(input);
            }
            input.mark(input.available());
            String hash = SecureUtil.md5(input);
            input.reset();
            return hash;
        } catch (IOException e) {
            throw new BizException("文件上传失败");
        }
    }
}
