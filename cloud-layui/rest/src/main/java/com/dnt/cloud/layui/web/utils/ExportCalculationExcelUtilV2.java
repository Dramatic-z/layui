package com.dnt.cloud.layui.web.utils;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * @author dramatic
 */
public class ExportCalculationExcelUtilV2 {

    private static Logger logger = LoggerFactory.getLogger(ExportCalculationExcelUtilV2.class);

    /**
     * 导出单个 excel文件
     *
     * @param dataText
     * @param importName
     */
    public static void exportDataOfTotal(
            List<List<String>> dataText, String importName) {
        if (dataText != null) {
            try {
                String filePath = "Users/dramatic/chartscache";
                File file = new File(filePath);
                if (!file.exists()) {
                    file.mkdir();
                }
                createExcel2(dataText, importName);
            } catch (Exception e) {
                logger.error("导出数据时异常！", e);
            }
        }
    }

    /**
     * 导出单个 excel文件  年份数据
     *
     * @param dataText
     * @param importName
     */
    public static void exportDataOfYear(
            List<List<String>> dataText, String importName) {
        if (dataText != null) {
            try {
                String filePath = "Users/dramatic/chartscache";
                File file = new File(filePath);
                if (!file.exists()) {
                    file.mkdir();
                }
                createExcel3(dataText, importName);
            } catch (Exception e) {
                logger.error("导出数据时异常！", e);
            }
        }
    }

    public static void createExcel2(
            List<List<String>> dataText, String importName)
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
        HSSFRow row = null;
        int i = 0;
        row = sheet.createRow(0);
        if (dataText != null && dataText.size() > 0) {

            //循环遍历一下我们的dataText
            for (int x = 0; x < dataText.size(); x++) {
                row = sheet.createRow(i + x);
                List<String> strs = dataText.get(x);
                for (int j = 0; j < strs.size(); j++) {
                    String num = strs.get(j);
                    num = "NA".equals(num) ? "0" : num;
                    if(x > 0){
                        row.createCell(j).setCellValue(Double.valueOf(num));
                    }else{
                        row.createCell(j).setCellValue(num);
                    }
                }
            }
        }

        try {
            File file = new File("/Users/dramatic/dramatic/excel/2007-2021_process/CM/" + importName);
            FileOutputStream fos = new FileOutputStream(file);
            wb.write(fos);
            fos.flush();
            fos.close();
        } catch (Exception e) {
            logger.error("Echart生成Excel时异常！", e);
        }
    }

    public static void createExcel3(
            List<List<String>> dataText, String importName) {
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
        HSSFRow row = null;
        int i = 0;
        row = sheet.createRow(0);
        if (dataText != null && dataText.size() > 0) {
            //循环遍历一下我们的dataText
            for (int x = 0; x < dataText.size(); x++) {
                row = sheet.createRow(i + x);
                List<String> strs = dataText.get(x);
                for (int j = 0; j < strs.size(); j++) {
                    String num = strs.get(j);
                    num = "NA".equals(num) ? "0" : num;
                    if(x > 0){
                        row.createCell(j).setCellValue(Double.valueOf(num));
                    }else{
                        row.createCell(j).setCellValue(num);
                    }
                }
            }
        }
        try {
            File file = new File("/Users/dramatic/dramatic/excel/2007-2021_process/CM/" + importName);
            FileOutputStream fos = new FileOutputStream(file);
            wb.write(fos);
            fos.flush();
            fos.close();
        } catch (Exception e) {
            logger.error("Echart生成Excel时异常！", e);
        }
    }


    /**
     * 导出单个 excel文件  省份数据
     *
     * @param dataText
     * @param importName
     */
    public static void exportDataOfCountry(
            List<List<String>> dataText, String importName) {
        if (dataText != null) {
            try {
                String filePath = "Users/dramatic/chartscache";
                File file = new File(filePath);
                if (!file.exists()) {
                    file.mkdir();
                }
                createExcel4(dataText, importName);
            } catch (Exception e) {
                logger.error("导出数据时异常！", e);
            }
        }
    }

    public static void createExcel4(
            List<List<String>> dataText, String importName) {
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
        HSSFRow row = null;
        int i = 0;
        row = sheet.createRow(0);
        if (dataText != null && dataText.size() > 0) {
            //循环遍历一下我们的dataText
            for (int x = 0; x < dataText.size(); x++) {
                row = sheet.createRow(i + x);
                List<String> strs = dataText.get(x);
                for (int j = 0; j < strs.size(); j++) {
                    String num = strs.get(j);
                    num = "NA".equals(num) ? "0" : num;
                    if(x > 0){
                        row.createCell(j).setCellValue(Double.valueOf(num));
                    }else{
                        row.createCell(j).setCellValue(num);
                    }
                }
            }
        }
        try {
            File file = new File("/Users/dramatic/dramatic/excel/2007-2021_process/CM/" + importName);
            FileOutputStream fos = new FileOutputStream(file);
            wb.write(fos);
            fos.flush();
            fos.close();
        } catch (Exception e) {
            logger.error("Echart生成Excel时异常！", e);
        }
    }

}
