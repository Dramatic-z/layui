package com.dnt.cloud.layui.web.utils;

import jxl.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;


/**
 * @author dramatic
 */
public class ExcelMarketUploadUtils {

    /**
     * 获取Excel数据
     *
     * @param file
     * @return
     * @throws Exception
     */
    public static List<List<String>> readExcel(MultipartFile file) throws Exception {

        // 创建输入流，读取Excel
        InputStream is = file.getInputStream();
        // jxl提供的Workbook类
        Workbook wb = Workbook.getWorkbook(is);
        // 只有一个sheet,直接处理
        // 创建一个Sheet对象
        Sheet sheet = wb.getSheet(0);
        // 得到所有的行数
        int rows = sheet.getRows();
        // 所有的数据
        List<List<String>> allData = new ArrayList<List<String>>();
        // 此处需要越过第一行   如不需越过第一行则设置j从0开始
        for (int j = 1; j < rows; j++) {
            List<String> oneData = new ArrayList<String>();
            // 得到每一行的单元格的数据
            Cell[] cells = sheet.getRow(j);
            for (int k = 0; k < cells.length; k++) {
                Cell cell = cells[k];
                String text = cell.getContents().trim();
                if (cell.getType() == CellType.NUMBER) {
                    //特殊处理小数位问题
                    NumberCell nc = (NumberCell) cells[k];
                    double value = nc.getValue();

                    NumberFormat nf = NumberFormat.getInstance();
                    //保留4位小数
                    nf.setMaximumFractionDigits(8);
                    nf.setRoundingMode(RoundingMode.HALF_UP);
                    nf.setGroupingUsed(false);
                    text = nf.format(value);
                }
                oneData.add(text);
            }
            // 存储每一条数据
            allData.add(oneData);
            // 打印出每一条数据
            //System.out.println(oneData);
        }
        return allData;
    }

}
