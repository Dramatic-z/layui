package com.dnt.cloud.biz.common.util;

import java.util.Date;

import com.dnt.cloud.common.lang.StringUtil;
import com.dnt.cloud.core.common.DateUtil;
import com.dnt.cloud.core.common.RandomUtil;

public class SequenceNoUtil {
	
	/**
	 * 长沙的设备编码
	 * 14位日期+4位随机串
	 * @return
	 */
	public static String getDeviceCode(){
		String dateStr = DateUtil.getLongDateString(new Date());
		String uuidStr = RandomUtil.random(4);
		if (StringUtil.isNotBlank(dateStr) && StringUtil.isNotBlank(uuidStr)) {
			String orderNo = dateStr+uuidStr;
			return orderNo.replace("-", "");
		}
		return null;
	}

	/**
	 * 订单号生成方法
	 * 14位日期字符串+11位随机串
	 * @return
	 */
	public static String getOrderNo() {
		
		//14位日期字符串-yyyyMMddHHmmss
		String dateStr = DateUtil.getLongDateString(new Date());
		String uuidStr = RandomUtil.random(11);
		
		if (StringUtil.isNotBlank(dateStr) && StringUtil.isNotBlank(uuidStr)) {
			String orderNo = dateStr+uuidStr;
			return orderNo.replace("-", "");
		}
		return null;
	}
	/**
	 * 生成批次号
	 * 规则：前缀-14位日期-14位随机字符串
	 * @param prefix 前缀
	 * @return
	 */
	public static String getBatchNo(String prefix) {
		String dateStr = DateUtil.getLongDateString(new Date());
		String randomStr = RandomUtil.random(14);
		String batchNo = String.format("%s-%s-%s",prefix, dateStr, randomStr);
		return batchNo;
	}
	
	
	public static void main(String[] args) {
//		System.out.println(getOrderNo());
//		System.out.println(getBatchNo("TO"));
//		Set<String> noSet = new HashSet<String>(); 
//		for (int i = 0; i < 2000; i++) {
//			String no = getLicenseCode();
//			noSet.add(no);
//			System.out.println(no);
//		}
//		System.out.println(noSet.size());
		System.out.println(getStoreCode("20180119-EW9E8DEY"));
		System.out.println(getCameraCode("20180119-EW9E8DEY-J4D3"));
	}
	
	/**
	 * 获取17位商户编码
	 * 编码格式：yyyyMMdd+"-"+8位随机大写字符
	 * 示例：20180119-EW9E8DEY
	 * @return
	 */
	public static String getMerchantCode(){
		String dateStr = DateUtil.getDateString(new Date());
		String randomStr = RandomUtil.random(8);
		String merchantCode = String.format("%s-%s", dateStr, randomStr);
		return merchantCode;
	}
	
	/**
	 * 获取门店编码 22位
	 * 编码格式：17位商户编码+"-"+4位随机大写字符
	 * 示例：20180119-EW9E8DEY-J4D3
	 * @return
	 */
	public static String getStoreCode(String merchantCode){
		String randomStr = RandomUtil.random(4);
		String storeCode = String.format("%s-%s", merchantCode, randomStr);
		return storeCode;
	}
	
	/**
	 * 获取摄像机编码 27位
	 * 编码格式：yyyyMMdd+"-"+4位随机大写字符
	 * 示例：20180119-EW9E8DEY-J4D3-BEBT
	 * @return
	 */
	public static String getCameraCode(String storeCode){
		String randomStr = RandomUtil.random(4);
		String cameraCode = String.format("%s-%s", storeCode, randomStr);
		return cameraCode;
	}
	
	
	
	/**
	 * 获取32位License编码
	 * 编码格式：yyyyMMddHHmmss+8位随机大写字符
	 * 示例：20180119115205C6B7DUQC   20180119115205YUJJGJ6W
	 * @return
	 */
	public static String getLicenseCode(){
		String dateStr = DateUtil.getLongDateString(new Date());
		String randomStr = RandomUtil.random(8);
		String licenseCode = String.format("%s%s", dateStr, randomStr);
		return licenseCode;
	}

}
