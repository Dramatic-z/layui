package com.dnt.cloud.layui.web.utils;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

/**
 * @author dramatic
 */
public class HttpHeaderUtil {

    private static final Logger logger = LoggerFactory.getLogger(HttpHeaderUtil.class);

    /**
     * 发送get 带header的请求
     *
     * @param url   url
     * @param token token
     * @return return
     */
    public static String sendGetRequest(String url, String token) {
        // 获取连接客户端工具
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String entityStr = null;
        CloseableHttpResponse response = null;
        try {
            //由于GET请求的参数都是拼装在URL地址后方，所以我们要构建一个URL
            URIBuilder uriBuilder = new URIBuilder(url);
            // 根据带参数的URI对象构建GET请求对象
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            //添加请求头信息
            httpGet.addHeader("Authorization", token);
            // 传输的类型
            httpGet.addHeader("content-type", "multipart/form-data");
            // 执行请求
            response = httpClient.execute(httpGet);
            // 获得响应的实体对象
            HttpEntity entity = response.getEntity();
            // 使用Apache提供的工具类进行转换成字符串
            entityStr = EntityUtils.toString(entity, "UTF-8");
        } catch (ClientProtocolException e) {
            logger.error("Http协议出现问题", e);
        } catch (ParseException e) {
            logger.error("解析错误", e);
        } catch (URISyntaxException e) {
            logger.error("URI解析异常", e);
        } catch (IOException e) {
            logger.error("IO异常", e);
        } finally {
            // 释放连接
            if (null != response) {
                try {
                    response.close();
                    httpClient.close();
                } catch (IOException e) {
                    logger.error("释放连接出错", e);
                }
            }
        }
        return entityStr;
    }

    /**
     * 发送post 带header的请求
     *
     * @param url    url
     * @param header header
     * @param body   body
     * @return return
     */
    public static String sendPostRequest(String url, Map<String, String> header, String body) {
        StringBuilder result = new StringBuilder();
        BufferedReader in;
        PrintWriter out;
        try {
            // 设置 url
            URL realUrl = new URL(url);
            URLConnection connection = realUrl.openConnection();
            HttpURLConnection httpUrlConnection = (HttpURLConnection) connection;
            // 设置 header
            for (String key : header.keySet()) {
                httpUrlConnection.setRequestProperty(key, header.get(key));
            }
            // 设置请求 body
            httpUrlConnection.setDoOutput(true);
            httpUrlConnection.setDoInput(true);
            out = new PrintWriter(httpUrlConnection.getOutputStream());
            // 保存body
            out.print(body);
            // 发送body
            out.flush();
            if (HttpURLConnection.HTTP_OK != httpUrlConnection.getResponseCode()) {
                System.out.println("Http 请求失败，状态码：" + httpUrlConnection.getResponseCode());
                return null;
            }

            // 获取响应body
            in = new BufferedReader(new InputStreamReader(httpUrlConnection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
            logger.error("请求异常--->{}", e);
            return null;
        }
        return result.toString();
    }
}
