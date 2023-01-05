package com.dnt.cloud.core.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * <p>
 * MD5工具类
 * </p>
 * 
 * @author hazyhao
 *
 */
public class MD5Util {

	private MD5Util() {
	}

	static MessageDigest getDigest() {
		try {
			return MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	public static byte[] md5(byte[] data) {
		return getDigest().digest(data);
	}

	public static byte[] md5(String data) {
		return md5(data.getBytes());
	}

	public static String md5Hex(byte[] data) {
		return HexUtil.toHexString(md5(data));
	}

	public static String md5Hex(String data) {
		return HexUtil.toHexString(md5(data));
	}

	public static String md5HexToUpperCase(String data) {
		return HexUtil.toHexString(md5(data)).toUpperCase();
	}
	
	public static String md5DuoToUpperCase(String sourceStr) {
		String result = "";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(sourceStr.getBytes());
			byte b[] = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			result = buf.toString();
			/*System.out.println("MD5(" + sourceStr + ",32) = " + result);
			System.out.println("MD5(" + sourceStr + ",16) = "
					+ buf.toString().substring(8, 24));*/
		} catch (NoSuchAlgorithmException e) {
			System.out.println(e);
		}
		return result.toUpperCase();
	}

}
