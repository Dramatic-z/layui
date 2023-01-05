package com.dnt.cloud.layui.web.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dnt.cloud.layui.web.domain.WeatherDomain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.zip.GZIPInputStream;

/**
 * @author dramatic
 */
@Component
public class WeatherUtils {
	
	private static Logger logger = LoggerFactory.getLogger(WeatherUtils.class);
	
	
	
	public static void main(String[] args) {
		WeatherDomain domain = sendRequestByPath("101190101");
		logger.info("--->{}",domain.getR_mare());
	}

	/**
	 * 查询天气
	 * @param code
	 * @return
	 */
	public static WeatherDomain sendRequestByPath(String code){
		PrintWriter out = null;
        BufferedReader in = null;
		InputStream is = null;
		StringBuffer buffer = new StringBuffer();
		
		String Url = "https://devapi.qweather.com/v7/weather/3d?";
		String Appkey ="0b2b112887e34b469c949b01a36bdfe7";
		try {
			// "&gzip=n" 不使用压缩
			String parm = "location=" + code + "&key=" + Appkey + "&lang=" + "zh";
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

            //解决默认请求 压缩 导致结果乱码的问题
			is = new GZIPInputStream(conn.getInputStream());
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
	
	
	public static WeatherDomain dealWeatherData(String res){
		//封装城市天气单体对象
		WeatherDomain domain = new WeatherDomain();
		Map<String, Object> map = JSON.parseObject(res);
		try {
			if("200".equals(map.get("code"))){
				JSONArray mapArry = JSON.parseArray(String.valueOf(map.get("daily")));
				JSONObject obj = mapArry.getJSONObject(0);
				domain.setR_mare(obj.get("textDay").toString());
				domain.setMax_r_num(obj.get("tempMax").toString());
				domain.setMin_r_num(obj.get("tempMin").toString());
			}else{
				logger.info("获取天气状态异常!  状态码->{}",map.get("code"));
			}
		}catch (Exception e) {
			logger.info("获取天气状态异常! ->{}",e);
		}finally {
			return domain;
		}
	}

}
