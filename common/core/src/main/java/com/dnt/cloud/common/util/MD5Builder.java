package com.dnt.cloud.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MD5Builder {
    static Logger logger      = LoggerFactory.getLogger(MD5Builder.class);
    // 用来将字节转换成 16 进制表示的字符
    static char   hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c',
            'd', 'e', 'f'    };

    /**
     * 对文件全文生成MD5摘要
     * @param file   要加密的文件
     * @return MD5摘要码
     */
    public static String getMD5(File file) {
        FileInputStream fis = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");

            if (logger.isDebugEnabled()) {
                logger.debug("MD5摘要长度：" + md.getDigestLength());
            }

            fis = new FileInputStream(file);
            byte[] buffer = new byte[2048];
            int length = -1;

            long s = System.currentTimeMillis();
            while ((length = fis.read(buffer)) != -1) {
                md.update(buffer, 0, length);
            }

            if (logger.isDebugEnabled()) {
                logger.debug("摘要生成成功,总用时: " + (System.currentTimeMillis() - s) + "ms");
            }

            byte[] b = md.digest();
            return byteToHexString(b);
        } catch (Exception ex) {
            logger.error("异常", ex);

            return null;
        } finally {
            try {
                fis.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * 对一段String生成MD5加密信息
     * @param message 要加密的String
     * @return 生成的MD5信息
     */
    public static String getMD5(String message) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] b;
            b = md.digest(message.getBytes("UTF-8"));

            return byteToHexString(b);
        } catch (UnsupportedEncodingException e) {
            logger.error("异常", e);

            return null;
        } catch (NoSuchAlgorithmException e) {
            logger.error("异常", e);

            return null;
        }
    }

    /**
     * 把byte[]数组转换成十六进制字符串表示形式
     * @param tmp    要转换的byte[]
     * @return 十六进制字符串表示形式
     */
    private static String byteToHexString(byte[] tmp) {
        // 用字节表示就是 16 个字节
        char str[] = new char[16 * 2]; // 每个字节用 16 进制表示的话，使用两个字符，
        // 所以表示成 16 进制需要 32 个字符
        int k = 0; // 表示转换结果中对应的字符位置
        for (int i = 0; i < 16; i++) { // 从第一个字节开始，对 MD5 的每一个字节
            // 转换成 16 进制字符的转换
            byte byte0 = tmp[i]; // 取第 i 个字节
            str[k++] = hexDigits[byte0 >>> 4 & 0xf]; // 取字节中高 4 位的数字转换, 
            // >>> 为逻辑右移，将符号位一起右移
            str[k++] = hexDigits[byte0 & 0xf]; // 取字节中低 4 位的数字转换
        }

        return new String(str);
    }

}
