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
public class ExportCalculationExcelUtil {

    private static Logger logger = LoggerFactory.getLogger(ExportCalculationExcelUtil.class);

    public static void exportData(
            List<List<String>> dataText, String importName) {
        if (dataText != null) {
            ///Users/dramatic/dramatic/excel/2007-2021_process/
            try {
                String filePath = "Users/dramatic/chartscache";
                File file = new File(filePath);
                if (!file.exists()) {
                    file.mkdir();
                }
                createExcel(dataText, importName);
            } catch (Exception e) {
                logger.error("导出数据时异常！", e);
            }
        }
    }

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

    public static void createExcel(
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

            row.createCell(0).setCellValue(" ");
            row.createCell(1).setCellValue("Year");
            row.createCell(2).setCellValue("Trade.Flow");
            row.createCell(3).setCellValue("Reporter");
            row.createCell(4).setCellValue("Partner");
            row.createCell(5).setCellValue("Commodity.Code");
            row.createCell(6).setCellValue("Commodity");
            row.createCell(7).setCellValue("Qty.Unit");
            row.createCell(8).setCellValue("Qty");
            row.createCell(9).setCellValue("Netweight..kg.");
            row.createCell(10).setCellValue("Trade.Value..US..");
            row.createCell(11).setCellValue("AGB");
            row.createCell(12).setCellValue("AGB计算结果");
            row.createCell(13).setCellValue("CF");
            row.createCell(14).setCellValue("CF计算结果");

            //循环遍历一下我们的dataText
            for (int x = 0; x < dataText.size(); x++) {
                row = sheet.createRow(i + x + 1);
                List<String> strs = dataText.get(x);

                row.createCell(0).setCellValue(strs.get(0));
                row.createCell(1).setCellValue(strs.get(1));
                row.createCell(2).setCellValue(strs.get(2));
                row.createCell(3).setCellValue(strs.get(3));
                row.createCell(4).setCellValue(strs.get(4));
                row.createCell(5).setCellValue(strs.get(5));
                row.createCell(6).setCellValue(strs.get(6));
                row.createCell(7).setCellValue(strs.get(7));
                row.createCell(8).setCellValue(strs.get(8));

                String c9 = strs.get(9);
                c9 = "NA".equals(c9) ? "0" : c9;
                row.createCell(9).setCellValue(Double.valueOf(c9));

                String c10 = strs.get(10);
                c10 = "NA".equals(c10) ? "0" : c10;
                row.createCell(10).setCellValue(Double.valueOf(c10));
                row.createCell(11).setCellValue(strs.get(11));
                row.createCell(12).setCellValue(Double.valueOf(strs.get(12)));
                row.createCell(13).setCellValue(strs.get(13));
                row.createCell(14).setCellValue(Double.valueOf(strs.get(14)));
            }
        }

        try {
            File file = new File("/Users/dramatic/dramatic/excel/2007-2021_process/" + importName);
            FileOutputStream fos = new FileOutputStream(file);
            wb.write(fos);
            fos.flush();
            fos.close();
        } catch (Exception e) {
            logger.error("Echart生成Excel时异常！", e);
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
            File file = new File("/Users/dramatic/dramatic/excel/2007-2021_process/" + importName);
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
            File file = new File("/Users/dramatic/dramatic/excel/2007-2021_process/" + importName);
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
            File file = new File("/Users/dramatic/dramatic/excel/2007-2021_process/" + importName);
            FileOutputStream fos = new FileOutputStream(file);
            wb.write(fos);
            fos.flush();
            fos.close();
        } catch (Exception e) {
            logger.error("Echart生成Excel时异常！", e);
        }
    }

    /**
     * 导出单个 excel文件  CM数据
     *
     * @param dataText
     * @param importName
     */
    public static void exportDataOfCM(
            List<List<String>> dataText, String importName) {
        if (dataText != null) {
            try {
                String filePath = "Users/dramatic/chartscache";
                File file = new File(filePath);
                if (!file.exists()) {
                    file.mkdir();
                }
                createExcel5(dataText, importName);
            } catch (Exception e) {
                logger.error("导出数据时异常！", e);
            }
        }
    }

    public static void createExcel5(
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

            row.createCell(0).setCellValue(" ");
            row.createCell(1).setCellValue("Year");
            row.createCell(2).setCellValue("Trade.Flow");
            row.createCell(3).setCellValue("Reporter");
            row.createCell(4).setCellValue("Partner");
            row.createCell(5).setCellValue("Commodity.Code");
            row.createCell(6).setCellValue("Commodity");
            row.createCell(7).setCellValue("Qty.Unit");
            row.createCell(8).setCellValue("Qty");
            row.createCell(9).setCellValue("Netweight..kg.");
            row.createCell(10).setCellValue("Trade.Value..US..");
            row.createCell(11).setCellValue("AGB");
            row.createCell(12).setCellValue("AGB计算结果");
            row.createCell(13).setCellValue("CF");
            row.createCell(14).setCellValue("CF计算结果");
            row.createCell(15).setCellValue("CE");
            row.createCell(16).setCellValue("CM");

            //循环遍历一下我们的dataText
            for (int x = 0; x < dataText.size(); x++) {
                row = sheet.createRow(i + x + 1);
                List<String> strs = dataText.get(x);

                row.createCell(0).setCellValue(strs.get(0));
                row.createCell(1).setCellValue(strs.get(1));
                row.createCell(2).setCellValue(strs.get(2));
                row.createCell(3).setCellValue(strs.get(3));
                row.createCell(4).setCellValue(strs.get(4));
                row.createCell(5).setCellValue(strs.get(5));
                row.createCell(6).setCellValue(strs.get(6));
                row.createCell(7).setCellValue(strs.get(7));
                row.createCell(8).setCellValue(strs.get(8));

                String c9 = strs.get(9);
                c9 = "NA".equals(c9) ? "0" : c9;
                row.createCell(9).setCellValue(Double.valueOf(c9));

                String c10 = strs.get(10);
                c10 = "NA".equals(c10) ? "0" : c10;
                row.createCell(10).setCellValue(Double.valueOf(c10));
                row.createCell(11).setCellValue(strs.get(11));
                row.createCell(12).setCellValue(Double.valueOf(strs.get(12)));
                row.createCell(13).setCellValue(strs.get(13));
                row.createCell(14).setCellValue(Double.valueOf(strs.get(14)));
                row.createCell(15).setCellValue(Double.valueOf(strs.get(15)));
                row.createCell(16).setCellValue(Double.valueOf(strs.get(16)));
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
