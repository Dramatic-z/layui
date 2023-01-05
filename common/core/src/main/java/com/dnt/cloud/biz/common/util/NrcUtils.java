package com.dnt.cloud.biz.common.util;

import static java.util.Locale.ENGLISH;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.dnt.cloud.common.NrcBizConstants;
import com.dnt.cloud.core.common.exceptions.NRCException;

/**
 * <p>系统工具类</p>
 * @author Ares
 * @version $Id: VfinanceUtils.java, 2014年12月22日 下午6:27:56 xiongbingyu Exp $
 */
public abstract class NrcUtils {
    private static String localIp;

    private NrcUtils() {
    }

    /**
     * 获取文件的真实后缀名。目前只支持JPG, GIF, PNG, BMP四种图片文件。
     * 
     * @param bytes 文件字节流
     * @return JPG, GIF, PNG or null
     */
    public static String getFileSuffix(byte[] bytes) {
        if (bytes == null || bytes.length < 10) {
            return null;
        }

        if (bytes[0] == 'G' && bytes[1] == 'I' && bytes[2] == 'F') {
            return "GIF";
        } else if (bytes[1] == 'P' && bytes[2] == 'N' && bytes[3] == 'G') {
            return "PNG";
        } else if (bytes[6] == 'J' && bytes[7] == 'F' && bytes[8] == 'I' && bytes[9] == 'F') {
            return "JPG";
        } else if (bytes[0] == 'B' && bytes[1] == 'M') {
            return "BMP";
        } else {
            return null;
        }
    }

    /**
     * 获取文件的真实媒体类型。目前只支持JPG, GIF, PNG, BMP四种图片文件。
     * 
     * @param bytes 文件字节流
     * @return 媒体类型(MEME-TYPE)
     */
    public static String getMimeType(byte[] bytes) {
        String suffix = getFileSuffix(bytes);
        String mimeType;

        if ("JPG".equals(suffix)) {
            mimeType = "image/jpeg";
        } else if ("GIF".equals(suffix)) {
            mimeType = "image/gif";
        } else if ("PNG".equals(suffix)) {
            mimeType = "image/png";
        } else if ("BMP".equals(suffix)) {
            mimeType = "image/bmp";
        } else {
            mimeType = "application/octet-stream";
        }

        return mimeType;
    }

    /**
     * 清除字典中值为空的项。
     * 
     * @param <V> 泛型
     * @param map 待清除的字典
     * @return 清除后的字典
     */
    public static <V> Map<String, V> cleanupMap(Map<String, V> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }

        Map<String, V> result = new HashMap<String, V>(map.size());
        Set<Entry<String, V>> entries = map.entrySet();

        for (Entry<String, V> entry : entries) {
            if (entry.getValue() != null) {
                result.put(entry.getKey(), entry.getValue());
            }
        }

        return result;
    }

    
    /**
     * 根据返回的JSON字符串获取时间搓
     * @param resStr 返回的JSON字符串
     * @return       签名类型
     */
    public static String getTimestampFromResponseJson(String resStr){
        int signStrIndex = resStr.indexOf("{\""+NrcBizConstants.TIMESTAMP+"\":");
        if(signStrIndex != -1){
            return resStr.substring(signStrIndex+13, resStr.indexOf(",", signStrIndex+13));
        }
        return "";
    }
    
    /**
     * 根据返回的JSON字符串获取Response内容
     * @param resStr 返回的JSON字符串
     * @return
     */
    public static String getSignContentFromResponseJson(String resStr){
        int signStrIndex = resStr.indexOf("_response\":");
        if(signStrIndex != -1){
            return resStr.substring(signStrIndex+11,resStr.length()-1);
        }
        return "";
    }
    


    /**
     * 把HashMap转换成JavaBean对象.
     * Bean属性需要有对应的set方法
     * <code>
     *  Person person = new Person();
     *  transMap2Bean(jsonMap, person);
     * </code>
     * @param map
     * @param obj
     */
    public static void transMap2Bean(Map<String, Object> map, Object obj) throws NRCException {
        // Map --> Bean 1: 利用Introspector,PropertyDescriptor实现 Map --> Bean  
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                if (map.containsKey(key)) {
                    Object value = map.get(key);
                    // 得到property对应的setter方法 
                    Method setter = property.getWriteMethod();
                    setter.invoke(obj, value);
                }
            }
        } catch (Exception e) {
            throw new NRCException(null,"transMap2Bean Error " + e.toString(), e);
        }
        return;
    }
    /**
     * 把HashMap转换成JavaBean对象.
     * Bean属性需要有对应的set方法
     * <code>
     *  Person person = new Person();
     *  transMap2Bean(jsonMap, person);
     * </code>
     * @param map
     * @param obj
     * @throws VfinanceApiException 
     */
    public static Map<String, Object> transBean2Map(Object obj) throws NRCException {
        // Bean --> Map 1: 利用Introspector和PropertyDescriptor 将Bean --> Map
        if(obj == null){
            return null;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                // 过滤class属性  
                if (!key.equals("class")) {
                    // 得到property对应的getter方法  
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(obj);
                    map.put(key, value);
                }
            }
        } catch (Exception e) {
            throw new NRCException(null,"transBean2Map Error " + e.toString(), e);
        }
        return map;
    }
    
    /**
     * 根据ApiMethodName获取Request、Response前缀
     * @param apiMethodName
     * @return
     */
    public static String getRequestNameByApi(String apiMethodName){
        if(apiMethodName == null) return null;
        String name = apiMethodName.substring(apiMethodName.lastIndexOf(".")+1);
        return name.substring(0, 1).toUpperCase(ENGLISH) + name.substring(1);
    }
    
    
    
    /**
     * 获取本机的网络IP
     */
    public static String getLocalNetWorkIp() {
        if (localIp != null) {
            return localIp;
        }
        try {
            Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress ip = null;
            while (netInterfaces.hasMoreElements()) {// 遍历所有的网卡
                NetworkInterface ni = (NetworkInterface) netInterfaces.nextElement();
                if (ni.isLoopback() || ni.isVirtual()) {// 如果是回环和虚拟网络地址的话继续
                    continue;
                }
                Enumeration<InetAddress> addresss = ni.getInetAddresses();
                while (addresss.hasMoreElements()) {
                    InetAddress address = addresss.nextElement();
                    if (address instanceof Inet4Address) {// 这里暂时只获取ipv4地址
                        ip = address;
                        break;
                    }
                }
                if (ip != null) {
                    break;
                }
            }
            if (ip != null) {
                localIp = ip.getHostAddress();
            } else {
                localIp = "127.0.0.1";
            }
        } catch (Exception e) {
            localIp = "127.0.0.1";
        }
        return localIp;
    }
    
}
