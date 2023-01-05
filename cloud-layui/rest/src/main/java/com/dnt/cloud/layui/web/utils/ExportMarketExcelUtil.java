package com.dnt.cloud.layui.web.utils;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author dramatic
 */
public class ExportMarketExcelUtil {

	private static Logger logger = LoggerFactory.getLogger(ExportMarketExcelUtil.class);

	public static void importData(List<String> dataText,String importname, int errornum,
			HttpServletRequest request, HttpServletResponse response,int datasize,int successsixe,int hknum,int htnum) {
		if (dataText != null) {
			try {

				String filePath = "C:\\chartscache";
				File file = new File(filePath);
				if (!file.exists()) {
					file.mkdir();
				}

				createExcel(request, response, dataText, errornum, importname, datasize, successsixe, hknum, htnum);

			} catch (Exception e) {
				logger.error("导出数据时异常！", e);
			}

		}
		
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @param dataText  数据list
	 * @param exportnum 导出error数量
	 * @param datasize  导入excel总数量
	 * @param successsixe  导入excel成功总数量
	 * @param hknum  导出大业态数量
	 * @param htnum  导出小业态数量
	 * @throws ServletException   
	 * @throws IOException
	 */
	public static void createExcel(HttpServletRequest request,
			HttpServletResponse response, List<String> dataText,
			int exportnum,String importname,int datasize,int successsixe,int hknum,int htnum)
			throws ServletException, IOException {

		// 第一步，创建一个webbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet("echart");
		// 3.创建单元格样式
		CellStyle cellStyle = wb.createCellStyle();
		// 设置这些样式
		cellStyle.setFillForegroundColor(HSSFColor.RED.index);
		cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		sheet.setDefaultColumnWidth(20);
		// 定义单元格为字符串类型
		// 4.创建Excel工作表的行
		HSSFRow headRow = null;
		//导出首行提示错误数量
		headRow = sheet.createRow(0);
		headRow.createCell(0).setCellValue("导入异常数据数量:"+exportnum+"条");
		headRow.createCell(1).setCellValue("导入excel总数量"+datasize+"条");
		headRow.createCell(2).setCellValue("导入excel成功总数量"+successsixe+"条");
		headRow.createCell(3).setCellValue("导出大业态数量"+hknum+"条");
		headRow.createCell(4).setCellValue("导出小业态数量"+htnum+"条");
		
		HSSFRow row = null;
		row = sheet.createRow(1);
		if (dataText != null && dataText.size() > 0) {
			
			/*//创建条件标题
			String[] queryData = dataText.get(0).split(";");
			String[] newQueryData = new String[6];
			newQueryData[0] = queryData[0].split(",")[0];
			newQueryData[1] = queryData[0].split(",")[1];
			newQueryData[2] = queryData[1].split(",")[0];
			newQueryData[3] = queryData[1].split(",")[1];
			newQueryData[4] = queryData[2].split(",")[0];
			newQueryData[5] = queryData[2].split(",")[1];
			for(int i=0;i<newQueryData.length;i++){
				row.createCell(i).setCellValue(newQueryData[i]);
			}*/
			
			//获取一下，看下需要创建几个标题,创建标题
//			row = sheet.createRow(0);
			String[] str = dataText.get(0).split(";");
			for(int x = 0; x < str.length; x++){
				row.createCell(x).setCellValue(str[x].split(":")[0]);
			}
			
			//填充数据
			for (int j=0;j<dataText.size();j++) {
				//创建一行
				row = sheet.createRow(j+2);
				//把每行的值都取出来
				String[] newStr = dataText.get(j).split(";");
				//把值塞进去
				for(int y=0;y<newStr.length;y++){
					row.createCell(y).setCellValue(newStr[y].split(":")[1]);
				}
			}
		}

		try {
			OutputStream out = null;
			response.setContentType("application/x-msdownload");
			response.setHeader("Content-Disposition", "attachment; filename="
					+ URLEncoder.encode(importname+".xls", "UTF-8"));
			out = response.getOutputStream();
			wb.write(out);
			out.flush();
			out.close();
		} catch (Exception e) {
			logger.error("Echart生成Excel时异常！", e);
		}
	}
}
