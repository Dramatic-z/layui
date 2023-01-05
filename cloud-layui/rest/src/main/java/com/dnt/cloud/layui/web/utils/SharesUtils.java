package com.dnt.cloud.layui.web.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @author dramatic
 */
@Component
public class SharesUtils {
	
	private static Logger logger = LoggerFactory.getLogger(SharesUtils.class);

	public static void main(String[] args) {
		JSONObject domain = sendRequestByPath();
	}

	/**
	 * 查询股票
	 * @return
	 */
	public static JSONObject sendRequestByPath(){
		PrintWriter out = null;
        BufferedReader in = null;
		InputStream is = null;
		StringBuffer buffer = new StringBuffer();
		
		String Url = "http://web.juhe.cn/finance/exchange/rmbquot?";
		String Appkey ="2cd65d4dfcdce8e41ea44d39af591e44";
		try {
			String parm = "key=" + Appkey ;
			String url = Url + parm;
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection  conn = realUrl.openConnection();
            
            // 设置通用的请求属性 
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			conn.setRequestProperty("Accept-Charset", "utf-8");
			conn.setRequestProperty("contentType", "utf-8");
            
            // 建立实际的连接  
            conn.connect();

			is = conn.getInputStream();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(is,StandardCharsets.UTF_8));
            String line = "";
            while ((line = in.readLine()) != null) {
                buffer.append(line);
            }
        } catch (Exception e) {
        	logger.error("获取天气请求出错-->{}",e);
        } finally {
            // 关闭输出流、输入流
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
		return dealWeatherData(buffer.toString());
    }
	
	
	public static JSONObject dealWeatherData(String res){
		logger.info("res--->{}",res);
		Map<String, Object> map = JSON.parseObject(res);
		try {
			if("200".equals(map.get("resultcode"))){
				JSONArray mapArry = JSON.parseArray(String.valueOf(map.get("result")));
				JSONObject obj = mapArry.getJSONObject(0);
				return obj;
			}else{
				logger.info("获取汇率异常!  状态码->{}",map.get("code"));
			}
		}catch (Exception e) {
			logger.info("获取汇率异常! ->{}",e);
		}
		return null;
	}



}
