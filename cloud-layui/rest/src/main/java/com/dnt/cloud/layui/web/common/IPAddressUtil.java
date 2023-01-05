package com.dnt.cloud.layui.web.common;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>注释</p>
 * @author Teder Yin
 * @version $Id: IPAddressUtil.java, v 0.1 2016年9月12日 下午4:25:25 yindezhou Exp $
 */
public class IPAddressUtil {
	
	private static Logger logger=LoggerFactory.getLogger(IPAddressUtil.class);
	//获取ip地址
	public static String getIpAddr(HttpServletRequest request){  
	        String ipAddress = request.getHeader("x-forwarded-for");  
	        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {  
	            ipAddress = request.getHeader("Proxy-Client-IP");  
	        }  
	        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {  
	            ipAddress = request.getHeader("WL-Proxy-Client-IP");  
	        }  
	        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {  
	            ipAddress = request.getRemoteAddr();  
	            if(ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")){  
	                //根据网卡取本机配置的IP  
	                InetAddress inet=null;  
	                try {
						inet = InetAddress.getLocalHost();
					} catch (UnknownHostException e) {
						logger.error("ip地址转化异常",e);
					}
	                ipAddress=inet!=null? inet.getHostAddress():null;  
	            }  
	        }  
	        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割  
	        if(ipAddress!=null && ipAddress.length()>15){ //"***.***.***.***".length() = 15  
	            if(ipAddress.indexOf(",")>0){  
	                ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));  
	            }  
	        }  
	        return ipAddress;   
	}  

}
