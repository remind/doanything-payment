import cn.hutool.crypto.SecureUtil;

import java.io.*;

/**
 * @author wxj
 * 2024/1/7
 */
public class MainTest {
    public static void main(String[] args) throws IOException {
        File file = new File("/Users/wxj/Desktop/2.html");

        InputStream is = new BufferedInputStream(new FileInputStream(file));
        is.mark(is.available());
        System.out.println(SecureUtil.md5(file));
        System.out.println(SecureUtil.md5(is));
        is.reset();
        System.out.println(SecureUtil.md5(is));
        System.out.println(SecureUtil.md5(is));
    }
}
