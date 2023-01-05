package com.dnt.cloud.layui.web.utils;

import java.util.Base64;
import java.util.Base64.Decoder;

import org.springframework.web.multipart.MultipartFile;

public class Base64ToMultipartUtils {
	
	
	public static MultipartFile base64ToMultipart(String base64) {
	    String[] baseStrs = base64.split(",");

    // BASE64Decoder decoder = new BASE64Decoder();
		Decoder decoder = Base64.getDecoder();
		byte[] b = new byte[0];
		b = decoder.decode(baseStrs[1]);

		for(int i = 0; i < b.length; ++i) {
		    if (b[i] < 0) {
		        b[i] += 256;
		    }
		}
		return new BASE64DecodedMultipartFile(b, baseStrs[0]);
	}
	
}
