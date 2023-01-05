package com.dnt.cloud.common.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dnt.cloud.common.lang.StringUtil;

/**
 * <p>URL工具类</p>
 * @author wangchanggeng
 * @version $Id: URLUtil.java, v 0.1 2010-2-20 下午05:25:23 wangchanggeng Exp $
 */
public class URLUtil {

    private static HttpURLConnection conn;
    private static OutputStream      out;
    public final static String       REQUEST_CHAR_SET  = "UTF-8";
    public final static String       RESPONSE_CHAR_SET = "UTF-8";
    public final static int          REQUEST_TIME_OUT  = 10 * 60 * 1000;
    private static Logger            logger            = LoggerFactory.getLogger(URLUtil.class);

    /**
     * 
     * @return
     */
    public OutputStream getOut() {
        return out;
    }

    /**
     * 非写入参数方式获取数据
     * @param urlStr
     * @return
     * @throws Exception
     */
    public static String readContents(String urlStr, String cookie) throws Exception {
        StringBuffer returnstr = new StringBuffer();
        try {

            URL url = new URL(urlStr);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset="
                                                    + REQUEST_CHAR_SET);
            if (!StringUtil.isBlank(cookie)) {
                conn.setRequestProperty("Cookie", cookie);
            }
            conn.setDoOutput(false);
            conn.setDoInput(true);
            conn.setAllowUserInteraction(false);
            conn.setUseCaches(false);
            conn.setRequestMethod("GET");
            conn.setReadTimeout(REQUEST_TIME_OUT);
            conn.setConnectTimeout(REQUEST_TIME_OUT);
            InputStream in = null;
            BufferedReader br = null;
            if (conn == null) {
                logger.info("[  URLConnection is null !!!  ]");
                throw new Exception("URLConnection is null !!!");
            }
            // 读取返回数据流
            in = conn.getInputStream();
            br = new BufferedReader(new InputStreamReader(in, RESPONSE_CHAR_SET));
            String inputLine;
            // 打印出返回流数据
            while ((inputLine = br.readLine()) != null) {
                returnstr.append(inputLine).append("\n");
            }
            logger.info(returnstr.toString());

        } catch (Exception ex) {
            logger.error("发生未知错误！", ex);

        }
        return returnstr.toString();
    }

    /**
     * 非写入参数方式获取数据
     * @param urlStr
     * @return
     * @throws Exception
     */
    public static String readContents(String urlStr) throws Exception {
        StringBuffer returnstr = new StringBuffer();
        try {

            URL url = new URL(urlStr);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset="
                                                    + REQUEST_CHAR_SET);

            conn.setDoOutput(false);
            conn.setDoInput(true);
            conn.setAllowUserInteraction(false);
            conn.setUseCaches(false);
            conn.setRequestMethod("GET");
            conn.setReadTimeout(REQUEST_TIME_OUT);
            conn.setConnectTimeout(REQUEST_TIME_OUT);
            InputStream in = null;
            BufferedReader br = null;
            if (conn == null) {
                logger.info("[  URLConnection is null !!!  ]");
                throw new Exception("URLConnection is null !!!");
            }
            // 读取返回数据流
            in = conn.getInputStream();
            br = new BufferedReader(new InputStreamReader(in, RESPONSE_CHAR_SET));
            String inputLine;
            // 打印出返回流数据
            while ((inputLine = br.readLine()) != null) {
                returnstr.append(inputLine).append("\n");
            }
            logger.info(returnstr.toString());

        } catch (Exception ex) {
            logger.error("发生未知错误！", ex);

        }
        return returnstr.toString();
    }

    /**
     * 连接URL地址
     * @param urlStr
     * @return
     * @throws Exception
     */
    public static HttpURLConnection connect(String urlStr) throws Exception {

        try {

            URL url = new URL(urlStr);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset="
                                                    + REQUEST_CHAR_SET);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setAllowUserInteraction(false);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setReadTimeout(REQUEST_TIME_OUT);
            conn.setConnectTimeout(REQUEST_TIME_OUT);

        } catch (Exception ex) {
            logger.error("发生未知错误！", ex);

        }
        return conn;
    }

    /**
     * 写入参数获取返回值
     * @param str
     * @return
     * @throws Exception
     */
    public static String writeContents(String str) throws Exception {

        StringBuffer returnstr = new StringBuffer();
        if (conn == null) {
            logger.info("[  URLConnection is null !!!  ]");
            throw new Exception("URLConnection is null !!!");
        }
        // 写入输出数据流
        OutputStreamWriter osw = null;
        try {
            OutputStream os = conn.getOutputStream();
            osw = new OutputStreamWriter(os);
            osw.write(str);
            osw.flush();
            InputStream in = null;
            BufferedReader br = null;
            if (conn == null) {
                logger.info("[  URLConnection is null !!!  ]");
                throw new Exception("URLConnection is null !!!");
            }
            // 读取返回数据流
            in = conn.getInputStream();
            br = new BufferedReader(new InputStreamReader(in, RESPONSE_CHAR_SET));
            String inputLine;
            // 打印出返回流数据
            while ((inputLine = br.readLine()) != null) {
                returnstr.append(inputLine).append("\n");
            }
            logger.info(returnstr.toString());

        } catch (Exception ex) {
            logger.error("发生未知错误！", ex);
        } finally {
            if (osw != null)
                osw.close();
        }
        return returnstr.toString();
    }

    /** 
      * 中断连接.
      */
    public static void disconnect() {
        conn.disconnect();
    }
}
