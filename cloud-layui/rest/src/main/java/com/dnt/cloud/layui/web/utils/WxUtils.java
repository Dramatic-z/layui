package com.dnt.cloud.layui.web.utils;

import java.security.MessageDigest;
import java.util.Arrays;

/**
 * 微信 Sign工具类
 * @author dramatic
 */
public class WxUtils {

    private static final String Token = "DNTWXMESSAGEPUSH";

    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static boolean CheckSignature(String signature, String timestamp, String nonce) {

        // 1）将token、timestamp、nonce三个参数进行字典序排序
        // 2）将三个参数字符串拼接成一个字符串进行sha1加密
        // 3）开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
        String[] strs = new String[]{Token, timestamp, nonce};

        Arrays.sort(strs);

        String str = strs[0] + strs[1] + strs[2];

        String mysignature = sha1(str);
        return mysignature.equals(signature);
    }

    private static String sha1(String str) {

        if (str == null) {
            return null;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
            messageDigest.update(str.getBytes());
            return getFormattedText(messageDigest.digest());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String getFormattedText(byte[] bytes) {

        int len = bytes.length;
        StringBuilder buf = new StringBuilder(len * 2);
        // 把密文转换成十六进制的字符串形式
        for (int j = 0; j < len; j++) {
            buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
            buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
        }
        return buf.toString();
    }

}
