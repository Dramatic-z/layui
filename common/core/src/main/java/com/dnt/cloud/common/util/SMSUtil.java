package com.dnt.cloud.common.util;

import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>短信工具类</p>
 * @author wangchanggeng
 * @version $Id: SMSUtil.java, v 0.1 2010-4-27 下午03:23:11 wangchanggeng Exp $
 */
public class SMSUtil {

    private static Logger logger          = LoggerFactory.getLogger(SMSUtil.class);
    //private static String httpUrl         = "http://61.172.252.136:8081/shanda/sendsms.aspx?phone=%s&msg=%s&pwd=JZJDdED33s2423";
    private static String httpUrl         = "http://211.136.104.52:9090/submit.asp?cpid=netfinworks001&pwd=8hyoj2nq&pid=100102&phone=%s&msg=%s";
    private static String defaultEncoding = "gb2312";

    /**
     * 发送短信
     * @param mobile
     * @param msg
     */
    public static void sendSms(String mobile, String msg) {

        String result = "";
        try {
            msg = java.net.URLEncoder.encode(msg, defaultEncoding);
        } catch (UnsupportedEncodingException e1) {
            logger.error("异常", e1);
        }
        try {
            String url = String.format(httpUrl, mobile, msg);
            logger.info("Request SMS Gateway: " + url);
            result = URLUtil.readContents(url);
            logger.info("Return Code: " + result);
        } catch (Exception e) {
            logger.error("异常", e);
        }

    }

    /**
     * 发送短信给多个手机号
     * @param mobiles
     * @param msg
     */
    public static void sendSms(String[] mobiles, String msg) {

        for (String mobile : mobiles) {
            sendSms(mobile, msg);
        }
    }
    
}
