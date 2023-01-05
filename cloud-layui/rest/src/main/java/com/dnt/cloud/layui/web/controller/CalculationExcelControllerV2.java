package com.dnt.cloud.layui.web.controller;

import com.dnt.cloud.layui.web.utils.ExcelMarketUploadUtils;
import com.dnt.cloud.layui.web.utils.ExportCalculationExcelUtilV2;
import com.dnt.cloud.layui.web.utils.LayuiReturnUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author dramatic
 */
@RestController
public class CalculationExcelControllerV2 {

    private static Logger logger = LoggerFactory.getLogger(CalculationExcelControllerV2.class);

    /**
     * 生成新的计算数据
     * http://127.0.0.1:8080/cloud-layui/calculationExcelV2/excelImportMarketData
     *
     * @return
     */
    @RequestMapping(value = "/calculationExcelV2/excelImportMarketData", method = RequestMethod.GET)
    public String excelImportMarketData() {
        logger.info("导入开始--->{}");
        String filePath = "/Users/dramatic/dramatic/excel/2007-2021_process/CM";
        File f = new File(filePath);
        File[] files = f.listFiles();
        String[] arrs = new String[]{"W_CM.xls", "WCM.xls", "X_COUNTRY_CM.xls"};
        logger.info("数量--->{}", files.length);
        List<List<String>> list = null;
        //储存所有文件的 CM数据集合
        Map<String, Map<String, BigDecimal>> cmAllMAp = new LinkedHashMap<>(10);
        //储存所有的年份统计结果
        Map<String, Map<String, List<BigDecimal>>> commodityYearMAp = new LinkedHashMap<>(10);
        if (files.length != 0) {
            //循环所有的文件
            for (File file : files) {
                FileItem fileItem = createFileItem(file);
                MultipartFile multipartFile = new CommonsMultipartFile(fileItem);
                if (multipartFile != null && !multipartFile.isEmpty()) {
                    String importName = multipartFile.getOriginalFilename();
                    if (!Arrays.asList(arrs).contains(importName)) {

                        //储存当前文件的 CM数据集合
                        Map<String, BigDecimal> cmMAp = new LinkedHashMap<>(10);
                        try {
                            logger.info("名称-->{}", importName);
                            list = ExcelMarketUploadUtils.readExcel(multipartFile);
                            if (list != null) {
                                for (List<String> datalist : list) {
                                    String commodity = datalist.get(5);
                                    String cm = datalist.get(16);
                                    if (cmMAp.get(commodity) != null) {
                                        BigDecimal c = cmMAp.get(commodity);
                                        BigDecimal t = c.add(new BigDecimal(cm));
                                        cmMAp.put(commodity, t);
                                    } else {
                                        cmMAp.put(commodity, new BigDecimal(cm));
                                    }
                                    //封装年份数据 map
                                    commodityYearMAp = obtainYearData(datalist, commodityYearMAp, commodity, new BigDecimal(cm));
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        cmAllMAp.put(importName, cmMAp);
                    }
                }
            }
            //处理统计表格  CM数据表格
            dealCfAllMap(cmAllMAp);
            //处理年份相关数据
            dealCommodityYearMAp(commodityYearMAp);
            //新加计算  （四种文件、各个年份的各个国家的数据）
            eachCountryEachYear();
        }
        logger.info("导入结束--->{}");
        return LayuiReturnUtils.LayuiReturnLogin("上传Excel数据", 1);
    }

    /**
     * 处理统计表格  CM数据表格
     *
     * @param cmAllMAp
     */
    public void dealCfAllMap(Map<String, Map<String, BigDecimal>> cmAllMAp) {
        //处理统计表格  CM数据表格
        if (cmAllMAp != null) {
            Map<String, List<BigDecimal>> conCfMAp = new LinkedHashMap<>(10);
            List<String> nameList = new ArrayList<>();
            //转化 map存储顺序
            for (Map.Entry<String, Map<String, BigDecimal>> cmData : cmAllMAp.entrySet()) {
                String fileName = cmData.getKey();
                nameList.add(fileName);
                Map<String, BigDecimal> cmMap = cmData.getValue();

                for (Map.Entry<String, BigDecimal> map : cmMap.entrySet()) {
                    String commodity = map.getKey();
                    BigDecimal total = map.getValue();

                    if (conCfMAp.get(commodity) != null) {
                        List<BigDecimal> fileList = conCfMAp.get(commodity);
                        int index = nameList.indexOf(fileName);
                        if (index == fileList.size()) {
                            fileList.add(nameList.indexOf(fileName), total);
                        } else {
                            fileList.add(new BigDecimal(0));
                            fileList.add(total);
                        }
                        conCfMAp.put(commodity, fileList);
                    } else {
                        List<BigDecimal> fileList = new ArrayList<>();
                        fileList.add(total);
                        conCfMAp.put(commodity, fileList);
                    }
                }
            }
            //封装 excel数据
            List<List<String>> cmList = new ArrayList<>();
            nameList.add(0, "Commodity.Code");
            cmList.add(nameList);
            for (Map.Entry<String, List<BigDecimal>> cmData : conCfMAp.entrySet()) {
                List<String> l = new ArrayList<>();
                //品类
                String commodity = cmData.getKey();
                l.add(commodity);
                for (BigDecimal cm : cmData.getValue()) {
                    l.add(String.valueOf(cm));
                }
                cmList.add(l);
            }
            //导出到指定目录
            ExportCalculationExcelUtilV2.exportDataOfTotal(cmList, "WCM.xls");
        }
    }

    private static FileItem createFileItem(File file) {
        FileItemFactory factory = new DiskFileItemFactory(16, null);
        FileItem item = factory.createItem("textField", "text/plain", true, file.getName());
        int bytesRead = 0;
        byte[] buffer = new byte[8192];
        try {
            FileInputStream fis = new FileInputStream(file);
            OutputStream os = item.getOutputStream();
            while ((bytesRead = fis.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return item;
    }

    /**
     * 封装年份数据
     *
     * @param datalist
     * @param commodityYearMAp
     * @param commodity
     * @param cmTotal
     */
    private Map<String, Map<String, List<BigDecimal>>> obtainYearData(List<String> datalist, Map<String, Map<String,
            List<BigDecimal>>> commodityYearMAp, String commodity, BigDecimal cmTotal) {
        //处理年份
        String year = datalist.get(1);
        //储存四种
        List<BigDecimal> yearList;
        Map<String, List<BigDecimal>> yearMap = new TreeMap<>();

        if (commodityYearMAp.get(commodity) != null) {
            yearMap = commodityYearMAp.get(commodity);
            if (yearMap != null) {
                yearList = yearMap.get(year);
                if (CollectionUtils.isNotEmpty(yearList)) {
                    BigDecimal one = yearList.get(0);
                    one = one.add(cmTotal);

                    yearList = new ArrayList<>();
                    yearList.add(0, one);
                    yearMap.put(year, yearList);
                    commodityYearMAp.put(commodity, yearMap);
                } else {
                    yearList = new ArrayList<>();
                    yearList.add(cmTotal);
                    yearMap.put(year, yearList);
                    commodityYearMAp.put(commodity, yearMap);
                }
            } else {
                yearList = new ArrayList<>();
                yearList.add(cmTotal);
                yearMap.put(year, yearList);
                commodityYearMAp.put(commodity, yearMap);
            }
        } else {
            yearList = new ArrayList<>();
            yearList.add(cmTotal);
            yearMap.put(year, yearList);
            commodityYearMAp.put(commodity, yearMap);
        }
        return commodityYearMAp;
    }

    /**
     * 处理年份相关数据
     *
     * @param commodityYearMAp
     */
    public void dealCommodityYearMAp(Map<String, Map<String, List<BigDecimal>>> commodityYearMAp) {
        //处理年份相关数据
        if (commodityYearMAp != null) {
            List<String> nameList = new ArrayList<>();
            nameList.add(0, "Commodity.Code");
            //封装 excel数据
            List<List<String>> cmList = new ArrayList<>();
            cmList.add(nameList);
            //转化 map存储顺序
            for (Map.Entry<String, Map<String, List<BigDecimal>>> yearData : commodityYearMAp.entrySet()) {
                String commodity = yearData.getKey();
                List<String> f = new ArrayList<>(15);
                f.add(commodity);
                for (int i = 0; i < 15; i++) {
                    f.add("0");
                }
                Map<String, List<BigDecimal>> yearMap = yearData.getValue();
                for (Map.Entry<String, List<BigDecimal>> map : yearMap.entrySet()) {
                    String year = map.getKey();
                    if (!nameList.contains(year)) {
                        nameList.add(year);
                    }
                }
                for (Map.Entry<String, List<BigDecimal>> map : yearMap.entrySet()) {
                    List<BigDecimal> list = map.getValue();
                    String year = map.getKey();
                    if (CollectionUtils.isNotEmpty(list)) {
                        BigDecimal four = list.get(0);
                        int index = nameList.indexOf(year);
                        f.set(index, String.valueOf(four));
                    }
                }
                cmList.add(f);
            }
            //导出到指定目录
            ExportCalculationExcelUtilV2.exportDataOfYear(cmList, "W_CM.xls");
        }
    }


    /**
     * 新加计算  （四种文件、各个年份的各个国家的数据）
     *
     * @return
     */
    public String eachCountryEachYear() {
        //从新获取
        String filePath = "/Users/dramatic/dramatic/excel/2007-2021_process/CM";
        File f = new File(filePath);
        File[] files = f.listFiles();
        String[] arrs = new String[]{"W_CM.xls", "WCM.xls", "X_COUNTRY_CM.xls"};

        List<List<String>> list;
        //储存所有文件的 CM数据集合
        Map<String, Map<String, BigDecimal>> cmAllMAp = new LinkedHashMap<>(10);
        if (files.length != 0) {
            //循环所有的文件
            for (File file : files) {
                FileItem fileItem = createFileItem(file);
                MultipartFile multipartFile = new CommonsMultipartFile(fileItem);
                if (multipartFile != null && !multipartFile.isEmpty()) {

                    String importName = multipartFile.getOriginalFilename();

                    if (!Arrays.asList(arrs).contains(importName)) {
                        String[] arr = importName.split(".xls");
                        importName = arr[0];
                        //储存当前文件的 CM数据集合
                        Map<String, BigDecimal> cmMAp = new LinkedHashMap<>(10);
                        try {
                            list = ExcelMarketUploadUtils.readExcel(multipartFile);
                            if (list != null) {
                                for (List<String> datalist : list) {
                                    String year = datalist.get(1);
                                    String cm = datalist.get(16);

                                    Map<String, BigDecimal> cmMap = cmAllMAp.get(year);
                                    if (cmMap != null) {
                                        BigDecimal value = cmMap.get(importName);
                                        if (value == null) {
                                            value = new BigDecimal(0);
                                        }
                                        value = value.add(new BigDecimal(cm));
                                        cmMap.put(importName, value);
                                    } else {
                                        cmMap = new HashMap<>();
                                        cmMap.put(importName, new BigDecimal(cm));
                                    }
                                    cmAllMAp.put(year, cmMap);
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            //导出到文件
            dealEachCountryEachYearMap(cmAllMAp);
        }
        logger.info("导入结束--->{}");
        return LayuiReturnUtils.LayuiReturnLogin("上传Excel数据", 1);
    }

    /**
     * 新加计算  （四种文件、各个年份的各个国家的数据）
     *
     * @param cmAllMAp
     */
    public void dealEachCountryEachYearMap(
            Map<String, Map<String, BigDecimal>> cmAllMAp) {
        logger.info("cmAllMAp--->{}", cmAllMAp);
        //封装 excel数据
        List<List<String>> cmList = new ArrayList<>();

        //相关数据
        if (cmAllMAp != null) {
            //初始化
            List<String> o = new ArrayList<>(15);
            o.add("YEAR");
            for (Map.Entry<String, Map<String, BigDecimal>> cmData : cmAllMAp.entrySet()) {
                Map<String, BigDecimal> yearMap = cmData.getValue();
                for (Map.Entry<String, BigDecimal> map : yearMap.entrySet()) {
                    String country = map.getKey();
                    if (!o.contains(country)) {
                        o.add(country);
                    }
                }
            }
            cmList.add(o);
            //转化 map
            for (Map.Entry<String, Map<String, BigDecimal>> cmData : cmAllMAp.entrySet()) {
                String year = cmData.getKey();
                List<String> l = new ArrayList<>(22);
                l.add(year);
                for (int i = 0; i < 22; i++) {
                    l.add("0");
                }
                Map<String, BigDecimal> yearMap = cmData.getValue();
                for (Map.Entry<String, BigDecimal> map : yearMap.entrySet()) {
                    String country = map.getKey();
                    int index = o.indexOf(country);
                    BigDecimal value = map.getValue();
                    l.set(index, String.valueOf(value));
                }
                cmList.add(l);
            }
            //导出到指定目录
            ExportCalculationExcelUtilV2.exportDataOfCountry(cmList, "X_COUNTRY_CM.xls");
        }
    }


}
