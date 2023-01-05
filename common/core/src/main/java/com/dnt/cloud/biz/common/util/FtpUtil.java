package com.dnt.cloud.biz.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dnt.cloud.common.lang.StringUtil;

public class FtpUtil {
	
	private static Logger logger = LoggerFactory.getLogger(FtpUtil.class);
	
	
	public static void main(String[] args) throws FileNotFoundException {
//		File f = new File("D:\\1537582967628.jpg");
//		FileInputStream fi = new FileInputStream(f);
//		FtpUtil.uploadFile("106.14.220.4", 21, "ftpuser", "dnt@ftp", 
//				"/tmp2/", "1537582967628.jpg", fi);
		
		File f = new File("D:\\123.jpg");
		FileInputStream fi = new FileInputStream(f);
		FtpUtil.uploadFile("116.62.45.218", 21, "nrcftpadmin", "dnt@ftp", 
				"/camera/snapshot/", "123.jpg", fi);
		
		
	}

	/**
	 * Description: 向FTP服务器上传文件
	 * 
	 * @param host
	 *            FTP服务器hostname
	 * @param port
	 *            FTP服务器端口
	 * @param username
	 *            FTP登录账号
	 * @param password
	 *            FTP登录密码
	 * @param basePath
	 *            FTP服务器基础目录
	 * @param filePath
	 *            FTP服务器文件存放路径。例如分日期存放：/2015/01/01。文件的路径为basePath+filePath
	 * @param filename
	 *            上传到FTP服务器上的文件名
	 * @param input
	 *            输入流
	 * @return 成功返回true，否则返回false
	 */
	public static boolean uploadFile(String host, int port, String username,
			String password, String filePath, String filename,
			InputStream input) {
		logger.info("进入方法");
		boolean result = false;
		FTPClient ftp = new FTPClient();
		try {
			int reply;
			ftp.connect(host, port);// 连接FTP服务器
			// 如果采用默认端口，可以使用ftp.connect(host)的方式直接连接FTP服务器
			ftp.enterLocalPassiveMode();
			ftp.login(username, password);// 登录
			reply = ftp.getReplyCode();
			logger.error("上传返回码--->{}",reply);
			if (!FTPReply.isPositiveCompletion(reply)) {
				logger.error("上传返回码错误---{}",reply);
				ftp.disconnect();
				return result;
			}
//			System.out.println(ftp.changeWorkingDirectory("/tmp/"));
			if (StringUtil.isNotBlank(filePath)) {
				
				// 切换到上传目录
				if (!ftp.changeWorkingDirectory(filePath)) {
					// 如果目录不存在创建目录
					String[] dirs = filePath.split("/");
					String tempPath = "";
					for (String dir : dirs) {
						if (null == dir || "".equals(dir)) {
							continue;
						}
						tempPath += "/" + dir;
						if (!ftp.changeWorkingDirectory(tempPath)) {
							if (!ftp.makeDirectory(tempPath)) {
								return result;
							} else {
								ftp.changeWorkingDirectory(tempPath);
							}
						}
					}
				}
			}
			// 设置上传文件的类型为二进制类型
			ftp.setFileType(FTP.BINARY_FILE_TYPE);
			ftp.setControlEncoding("UTF-8");
			// 上传文件
			if (!ftp.storeFile(filename, input)) {
				logger.info("storeFile 执行失败---{},{}",filename,input);
				return result;
			}
			input.close();
			ftp.logout();
			result = true;
		} catch (IOException e) {
			logger.error("FTP文件上传异常！",e);
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ioe) {
				}
			}
		}
		return result;
	}

	/**
	 * Description: 从FTP服务器下载文件
	 * 
	 * @param host
	 *            FTP服务器hostname
	 * @param port
	 *            FTP服务器端口
	 * @param username
	 *            FTP登录账号
	 * @param password
	 *            FTP登录密码
	 * @param remotePath
	 *            FTP服务器上的相对路径
	 * @param fileName
	 *            要下载的文件名
	 * @param localPath
	 *            下载后保存到本地的路径
	 * @return
	 */
	public static boolean downloadFile(String host, int port, String username,
			String password, String remotePath, String fileName,
			String localPath) {
		boolean result = false;
		FTPClient ftp = new FTPClient();
		try {
			int reply;
			ftp.connect(host, port);
			// 如果采用默认端口，可以使用ftp.connect(host)的方式直接连接FTP服务器
			ftp.login(username, password);// 登录
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return result;
			}
			ftp.changeWorkingDirectory(remotePath);// 转移到FTP服务器目录
			FTPFile[] fs = ftp.listFiles();
			for (FTPFile ff : fs) {
				if (ff.getName().equals(fileName)) {
					File localFile = new File(localPath + "/" + ff.getName());

					OutputStream is = new FileOutputStream(localFile);
					ftp.retrieveFile(ff.getName(), is);
					is.close();
				}
			}

			ftp.logout();
			result = true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ioe) {
				}
			}
		}
		return result;
	}
}