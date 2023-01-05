 package com.dnt.cloud.layui.web.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.dnt.cloud.biz.common.util.FtpUtil;

public class PictureUploadUtils {
	
	private final static Logger logger = LoggerFactory.getLogger(PictureUploadUtils.class);
	
	
	/**
	 * 上传图片公共方法（单张）
	 * @param file
	 * @param FolderName
	 * @return
	 */
	public static String Uploadpic(MultipartFile file,String FolderName,String host,int port,String userName,String password
			,String root,String access) {
		String imageUrl = "";
		if (!file.isEmpty()) {
			InputStream in = null;
			try {
				in = file.getInputStream();
			} catch (IOException e) {
				logger.error("创建流异常", e);
			}
			String fileName = file.getOriginalFilename();
			String uuid = UUID.randomUUID().toString().replaceAll("-", "");
			String newFileName = uuid+ fileName.substring(fileName.lastIndexOf("."),fileName.length());
			//logger.info("--->{}",host,port,userName,password,root);
			boolean flag = FtpUtil.uploadFile(host, port, userName, password,
					root + "/" + FolderName, newFileName, in);
			logger.info("flag--->{}",flag);
			if (flag) {
				imageUrl = "http://" + access + "/" + root + "/"+ FolderName + "/" + newFileName;
			}
		} else {
			logger.info("上传图片获取流失败 --》{}",file);
		}
		return imageUrl;
	}

}
