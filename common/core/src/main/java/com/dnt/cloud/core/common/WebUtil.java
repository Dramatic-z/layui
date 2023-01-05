package com.dnt.cloud.core.common;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;

import com.dnt.cloud.common.lang.StringUtil;
import com.dnt.cloud.core.common.exceptions.ReturnCode;
import com.dnt.cloud.core.common.exceptions.ValidateException;

/**
 * <p>Web Tool</p>
 * @author zc
 * @version $Id: WebUtil.java, v 0.1 2015年7月7日 下午5:55:42 zhoucong Exp $
 */
public final class WebUtil {
	
	/**
     * 校验并获取字符集编码
     */
   public static Charset checkAndGetEncoding(String inputCharset) {
        if (StringUtils.isBlank(inputCharset)) {
            throw new ValidateException(ReturnCode.INPUT_CHARSET_ERROR);
        }
        try {
            Charset charset = Charset.forName(inputCharset);
            if ("UTF-8".equalsIgnoreCase(inputCharset) || "GBK".equalsIgnoreCase(inputCharset)
                || "GB2312".equalsIgnoreCase(inputCharset)) {
                return charset;
            }
            throw new ValidateException(ReturnCode.INPUT_CHARSET_ERROR, "无法识别编码类型：" + inputCharset);
        } catch (Throwable e) {
            throw new ValidateException(ReturnCode.INPUT_CHARSET_ERROR);
        }
    }
	
	/**
     * 将请求参数转换为待处理参数
     * @throws UnsupportedEncodingException
     */
    public static Map<String, String> convertParam(Map<String, String[]> parameters,String httpMethod ,Charset charset)
                                                                                 throws UnsupportedEncodingException {
        if (MapUtils.isEmpty(parameters)) {
            throw new ValidateException(ReturnCode.REQUIRED_FIELD_NOT_EXIST);
        }
        Map<String, String> result = new HashMap<String, String>(parameters.size());
        for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
            if (entry.getValue() == null || entry.getValue().length == 0) {
                result.put((String) entry.getKey(), null);
            } else {
                String value;
                if ("GET".equals(httpMethod)) {
                    //value = StringUtils.trim(new String(entry.getValue()[0].getBytes("utf-8"), charset));
                    value = StringUtils.trim(entry.getValue()[0]);
                } else {
                    value = StringUtils
                        .trim(URLDecoder.decode(entry.getValue()[0], charset.name()));
                }
                result.put(StringUtils.trim(entry.getKey()), value);
            }
        }
        return result;
    }

    /**
     * 防止XSS攻击处理
     */
    public static void auniXSS(Map<String, String> params) {
        Iterator<Entry<String, String>> it = params.entrySet().iterator();
        while (it.hasNext()) {
            Entry<String, String> entry = it.next();
            if (entry.getValue() != null) {
                entry.setValue(encode(entry.getValue()));
            }
        }
    }

    /**
     * 将包含转义字符的信息  编码成html 转义标签
     * @param value
     * @return
     */
    public static String encode(String value) {
        if (value != null) {
            return value.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;")
                .replaceAll("\"", "&quot;").replaceAll("'", "&acute;");
        } else {
            return null;
        }
    }

    /**
     * html标签 解码
     * @param value
     * @return
     */
    public static String decode(String value) {
        if (value != null) {
            return value.replaceAll("&lt;", "<").replaceAll("&gt;", ">").replaceAll("&quot;", "\"")
                .replaceAll("&acute;", "'").replaceAll("&amp;", "&");
        } else {
            return null;
        }
    }
    
    public static void main(String[] args) {
    	/*
    	String from = WebUtils.encode("2017/12/12 17:24:00");
		String url = "http://192.168.2.6/cgi-bin/operator/countreport.cgi?reportfmt=table&from="+from+"&to=now&counter=active&sampling=600&order=ascending&value=abs";
		String url = "http://192.168.2.6/nvc-cgi/reporthm.cgi?reportfmt=csv&from=yesterday&to=now&table=1&individual=no";
    	System.out.println(sendCameraGet(url,null));
		try {
			getCameraSnapshotImg(
					"http://192.168.2.206/nvc-cgi/admin/snapshot.fcgi", null,
					"a.jpg", "d:/");
		} catch (Exception e) {
			e.printStackTrace();
		}
		*/
    	Long begin = System.currentTimeMillis();
		List<String[]> res = queryHeatMapData("http://192.168.2.206/nvc-cgi/reporthm.cgi?reportfmt=csv&from=yesterday&to=now&table=1&individual=no", null);
		for (String[] strs : res) {
			for (int i = 0; i < strs.length; i++) {
				System.out.print(strs[i]+",");
			}
			System.out.println();
		}
		Long end = System.currentTimeMillis();
		System.out.println(end-begin);
	}
    
    /**
	 * 发送智能摄像头get请求
	 */
	public static String sendCameraGet(String urlPath, String charset) {
		// 连接主机的超时时间（单位：毫秒）
		System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
		// 从主机读取数据的超时时间（单位：毫秒）
		System.setProperty("sun.net.client.defaultReadTimeout", "10000");
		if(StringUtil.isBlank(charset)){
			charset = "UTF-8";
		}
		StringBuffer resultBuffer = null;
		// 构建请求参数
		BufferedReader br = null;
		try {
			URL url = null;
			url = new URL(urlPath);
			URLConnection con = url.openConnection();
			// 设置请求属性
			con.setRequestProperty("accept", "*/*");
			con.setRequestProperty("connection", "Keep-Alive");
			con.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
			con.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 设置设备登录账号/密码  BASIC64编码
			con.setRequestProperty("Authorization", "Basic cm9vdDpwYXNz");
			// 建立连接·
			con.connect();
			resultBuffer = new StringBuffer();
			br = new BufferedReader(new InputStreamReader(con.getInputStream(),
					charset));
			String temp;
			while ((temp = br.readLine()) != null) {
				resultBuffer.append(temp).append("\r\n");
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					br = null;
					throw new RuntimeException(e);
				}
			}
		}
		return resultBuffer.toString();
	}
	
	/**
	 * 获取摄像头快照图片
	 */
	public static boolean getCameraSnapshotImg(String urlPath, String charset,String fileName,String savePath) {
		boolean result = false;
		// 连接主机的超时时间（单位：毫秒）
		System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
		// 从主机读取数据的超时时间（单位：毫秒）
		System.setProperty("sun.net.client.defaultReadTimeout", "10000");
		if(StringUtil.isBlank(charset)){
			charset = "UTF-8";
		}
		// 构建请求参数
		BufferedReader br = null;
		try {
			URL url = null;
			url = new URL(urlPath);
			URLConnection con = url.openConnection();
			// 设置请求属性
			con.setRequestProperty("accept", "*/*");
			con.setRequestProperty("connection", "Keep-Alive");
			con.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
			con.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 设置设备登录账号/密码  BASIC64编码
			con.setRequestProperty("Authorization", "Basic cm9vdDpwYXNz");
			//得到输入流  
	        InputStream inputStream = con.getInputStream();
	        //获取自己数组  
	        byte[] getData = readInputStream(inputStream);      
	  
	        if(StringUtil.isBlank(savePath)){
	        	String webRootPath = WebUtil.class.getResource("/").getPath();
	        	savePath = webRootPath+"/static/snapshotimg";
	        }
	        //文件保存位置  
	        File saveDir = new File(savePath);  
	        if(!saveDir.exists()){  
	            saveDir.mkdir();
	        }
	        File file = new File(saveDir+File.separator+fileName);   
	        file.deleteOnExit();
	        FileOutputStream fos = new FileOutputStream(file);       
	        fos.write(getData);
	        if(fos!=null){  
	            fos.close();    
	        }  
	        if(inputStream!=null){  
	            inputStream.close();  
	        }
	        result = true;
		} catch (Exception e) {
			result = false;
			throw new RuntimeException(e);
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					result = false;
					br = null;
					throw new RuntimeException(e);
				}
			}
		}
		return result;
	}
	
	/**
	 * 发送智能摄像头热力图数据
	 */
	public static List<String[]> queryHeatMapData(String urlPath, String charset) {
		List<String[]> list = new ArrayList<String[]>();
		// 连接主机的超时时间（单位：毫秒）
		System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
		// 从主机读取数据的超时时间（单位：毫秒）
		System.setProperty("sun.net.client.defaultReadTimeout", "10000");
		if(StringUtil.isBlank(charset)){
			charset = "UTF-8";
		}
		// 构建请求参数
		BufferedReader br = null;
		try {
			URL url = null;
			url = new URL(urlPath);
			URLConnection con = url.openConnection();
			// 设置请求属性
			con.setRequestProperty("accept", "*/*");
			con.setRequestProperty("connection", "Keep-Alive");
			con.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
			con.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 设置设备登录账号/密码  BASIC64编码
			con.setRequestProperty("Authorization", "Basic cm9vdDpwYXNz");
			// 建立连接·
			con.connect();
			br = new BufferedReader(new InputStreamReader(con.getInputStream(), charset));
			String temp;
			while ((temp = br.readLine()) != null) {
				String[] data = temp.split(",");
				if(data==null || data.length<5){
					continue;
				}else{
					list.add(data);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					br = null;
					throw new RuntimeException(e);
				}
			}
		}
		return list;
	}
	
	/** 
     * 从输入流中获取字节数组 
     * @param inputStream 
     * @return 
     * @throws IOException 
     */  
    public static  byte[] readInputStream(InputStream inputStream) throws IOException {    
        byte[] buffer = new byte[1024];    
        int len = 0;    
        ByteArrayOutputStream bos = new ByteArrayOutputStream();    
        while((len = inputStream.read(buffer)) != -1) {    
            bos.write(buffer, 0, len);    
        }    
        bos.close();    
        return bos.toByteArray();    
    }    
  

}
