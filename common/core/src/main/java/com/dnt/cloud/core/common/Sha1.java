package com.dnt.cloud.core.common;

import java.security.DigestException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Sha1 {

	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * SHA1 安全加密算法
	 * 
	 * @param maps
	 *            参数key-value map集合
	 * @return
	 * @throws DigestException
	 */
	public String SHA1(Map<String, String> maps) throws DigestException {
		// 获取信息摘要 - 参数字典排序后字符串
		String decrypt = MagCore.createLinkString(MagCore.paraFilter(maps), false);;
		try {
			// 指定sha1算法
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
			digest.update(decrypt.getBytes());
			// 获取字节数组
			byte messageDigest[] = digest.digest();
			// Create Hex String
			StringBuffer hexString = new StringBuffer();
			// 字节数组转换为 十六进制 数
			for (int i = 0; i < messageDigest.length; i++) {
				String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
				if (shaHex.length() < 2) {
					hexString.append(0);
				}
				hexString.append(shaHex);
			}
			logger.info("sha1 参数排序后字符串:{},加密后结果:{}", decrypt, hexString);
			return hexString.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new DigestException("签名错误！");
		}
	}



}