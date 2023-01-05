package com.dnt.cloud.biz.common.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * CSV操作(导出和导入)
 * 
 * @author wjm
 * @version 1.0 Nov 24, 2015 4:30:58 PM
 */
public class CSVUtil {
	
	private static Logger logger = LoggerFactory.getLogger(CSVUtil.class);

	/**
	 * 导出
	 * 
	 * @param file
	 *            csv文件(路径+文件名)，csv文件不存在会自动创建
	 * @param dataList
	 *            数据
	 * @return
	 */
	public static boolean exportCsv(File file, List<String> dataList) {
		boolean isSucess = false;

		FileOutputStream out = null;
		OutputStreamWriter osw = null;
		BufferedWriter bw = null;
		try {
			out = new FileOutputStream(file);
			osw = new OutputStreamWriter(out, "gbk");
			bw = new BufferedWriter(osw);
			if (dataList != null && !dataList.isEmpty()) {
				for (String data : dataList) {
					bw.append(data).append("\r\n");
				}
			}
			isSucess = true;
		} catch (Exception e) {
			isSucess = false;
			logger.error("客流数据文件创建失败",e);
		} finally {
			if (bw != null) {
				try {
					bw.close();
					bw = null;
				} catch (IOException e) {
					logger.error("客流数据文件IO关闭异常",e);
				}
			}
			if (osw != null) {
				try {
					osw.close();
					osw = null;
				} catch (IOException e) {
					logger.error("客流数据文件IO关闭异常",e);
				}
			}
			if (out != null) {
				try {
					out.close();
					out = null;
				} catch (IOException e) {
					logger.error("客流数据文件IO关闭异常",e);
				}
			}
		}

		return isSucess;
	}

	/**
	 * 导入
	 * 
	 * @param file
	 *            csv文件(路径+文件)
	 * @return
	 */
	public static List<String> importCsv(File file) {
		List<String> dataList = new ArrayList<String>();

		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			String line = "";
			while ((line = br.readLine()) != null) {
				dataList.add(line);
			}
		} catch (Exception e) {
		} finally {
			if (br != null) {
				try {
					br.close();
					br = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return dataList;
	}
}