package com.dnt.cloud.layui.web.controller;

import com.alibaba.fastjson.JSON;
import com.dnt.cloud.common.lang.StringUtil;
import com.dnt.cloud.layui.web.utils.ExcelMarketUploadUtils;
import com.dnt.cloud.layui.web.utils.ExportCalculationExcelUtil;
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
import org.springframework.web.servlet.ModelAndView;

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
public class CalculationExcelController {

    private static Logger logger = LoggerFactory.getLogger(CalculationExcelController.class);

    private final static String TEST_VUE_PAGE = "/views/calculationExcel/marketTable";

    static Map<String, String> map_AGB = new HashMap<>();
    static Map<String, String> map_CF = new HashMap<>();

    static Map<String, String> map_TPE = new HashMap<>();
    static List<String> list_TPE = new ArrayList<>();

    public static void initLocationId() {
        String filePath = "/Users/dramatic/dramatic/excel/原始参数22-12-16.xls";
        File file = new File(filePath);
        FileItem fileItem = createFileItem(file);
        MultipartFile multipartFile = new CommonsMultipartFile(fileItem);
        try {
            List<List<String>> list = ExcelMarketUploadUtils.readExcel(multipartFile);
            for (List<String> datalist : list) {
                String Commodity = datalist.get(1);
                if (StringUtil.isNotBlank(Commodity)) {
                    String age = datalist.get(2);
                    String cf = datalist.get(3);
                    map_AGB.put(Commodity, age);
                    map_CF.put(Commodity, cf);
                } else {
                    break;
                }
            }
            logger.info("map_AGB-->{}", JSON.toJSONString(map_AGB));
            logger.info("map_CF-->{}", JSON.toJSONString(map_CF));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void initLocationId2() {
        String filePath = "/Users/dramatic/dramatic/excel/distance.xls";
        File file = new File(filePath);
        FileItem fileItem = createFileItem(file);
        MultipartFile multipartFile = new CommonsMultipartFile(fileItem);
        try {
            List<List<String>> list = ExcelMarketUploadUtils.readExcel(multipartFile);
            for (List<String> datalist : list) {
                String Country = datalist.get(0);
                if (StringUtil.isNotBlank(Country)) {
                    list_TPE.add(Country);
                    String tpe = datalist.get(1);

                    map_TPE.put(Country, tpe);
                } else {
                    break;
                }
            }
            logger.info("map_TPE-->{}", JSON.toJSONString(map_TPE));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 跳转页面
     *
     * @return
     */
    @RequestMapping(value = "/calculationExcel/indexPage", method = {RequestMethod.GET})
    public ModelAndView indexPage() {
        //查询相关内容
        return new ModelAndView(TEST_VUE_PAGE);
    }

    /**
     * Excel导入
     * http://127.0.0.1:8080/cloud-layui/calculationExcel/excelImportMarketData
     *
     * @return
     */
    @RequestMapping(value = "/calculationExcel/excelImportMarketData", method = RequestMethod.GET)
    public String excelImportMarketData() {
        logger.info("初始化开始--->{}");
        initLocationId();
        logger.info("导入开始--->{}");
        String filePath = "/Users/dramatic/dramatic/excel/1";
        File f = new File(filePath);
        File[] files = f.listFiles();
        logger.info("数量--->{}", files.length);
        List<List<String>> list = null;
        //储存所有文件的 CF数据集合
        Map<String, Map<String, BigDecimal>> cfAllMAp = new LinkedHashMap<>(10);
        //2007 CF
        Map<String, Map<String, BigDecimal>> cfAllMAp_2007 = new LinkedHashMap<>(10);
        //2019 CF
        Map<String, Map<String, BigDecimal>> cfAllMAp_2019 = new LinkedHashMap<>(10);
        //储存所有文件的 AGB数据集合
        Map<String, Map<String, BigDecimal>> agbAllMAp = new LinkedHashMap<>(10);
        //储存所有的年份统计结果
        Map<String, Map<String, List<BigDecimal>>> commodityYearMAp = new LinkedHashMap<>(10);
        if (files.length != 0) {
            //循环所有的文件
            for (File file : files) {
                FileItem fileItem = createFileItem(file);
                MultipartFile multipartFile = new CommonsMultipartFile(fileItem);
                if (multipartFile != null && !multipartFile.isEmpty()) {
                    String importName = multipartFile.getOriginalFilename();
                    //储存当前文件的 CF数据集合
                    Map<String, BigDecimal> cfMAp = new LinkedHashMap<>(10);
                    //2007 CF
                    Map<String, BigDecimal> cfMAp_2007 = new LinkedHashMap<>(10);
                    //2019 CF
                    Map<String, BigDecimal> cfMAp_2019 = new LinkedHashMap<>(10);
                    //储存当前文件的 AGB数据集合
                    Map<String, BigDecimal> agbMAp = new LinkedHashMap<>(10);
                    try {
                        list = ExcelMarketUploadUtils.readExcel(multipartFile);
                        if (list != null) {
                            for (List<String> datalist : list) {
                                BigDecimal ageTotal = new BigDecimal(0);
                                BigDecimal cfTotal = new BigDecimal(0);
                                //年份
                                String year = datalist.get(1);
                                String commodity = datalist.get(5);
                                String netWeight = datalist.get(9);
                                if (commodity.equals("441210")) {
                                    netWeight = datalist.get(8);
                                }
                                String age = map_AGB.get(commodity);
                                String cf = map_CF.get(commodity);
                                BigDecimal ageBigDecimal = new BigDecimal(age);
                                BigDecimal cfBigDecimal = new BigDecimal(cf);

                                netWeight.replace("NA", "0");
                                if (netWeight.matches("[0-9]+")) {
                                    BigDecimal weight = new BigDecimal(netWeight);
                                    //计算乘积
                                    ageTotal = weight.multiply(ageBigDecimal);
                                    cfTotal = weight.multiply(cfBigDecimal);
                                }
                                datalist.add(age);
                                datalist.add(String.valueOf(ageTotal));
                                if (agbMAp.get(commodity) != null) {
                                    BigDecimal c = agbMAp.get(commodity);
                                    BigDecimal t = c.add(ageTotal);
                                    agbMAp.put(commodity, t);
                                } else {
                                    agbMAp.put(commodity, ageTotal);
                                }
                                datalist.add(cf);
                                datalist.add(String.valueOf(cfTotal));
                                if (cfMAp.get(commodity) != null) {
                                    BigDecimal c = cfMAp.get(commodity);
                                    BigDecimal t = c.add(cfTotal);
                                    cfMAp.put(commodity, t);
                                } else {
                                    cfMAp.put(commodity, cfTotal);
                                }
                                //region 2007CF
                                if (year.equals("2007")) {
                                    if (cfMAp_2007.get(commodity) != null) {
                                        BigDecimal c = cfMAp_2007.get(commodity);
                                        BigDecimal t = c.add(cfTotal);
                                        cfMAp_2007.put(commodity, t);
                                    } else {
                                        cfMAp_2007.put(commodity, cfTotal);
                                    }
                                }
                                //endregion
                                //region 2019CF
                                if (year.equals("2019")) {
                                    if (cfMAp_2019.get(commodity) != null) {
                                        BigDecimal c = cfMAp_2019.get(commodity);
                                        BigDecimal t = c.add(cfTotal);
                                        cfMAp_2019.put(commodity, t);
                                    } else {
                                        cfMAp_2019.put(commodity, cfTotal);
                                    }
                                }
                                //endregion
                                //封装年份数据 map
                                commodityYearMAp = obtainYearData(datalist, commodityYearMAp, commodity, ageTotal, cfTotal);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    cfAllMAp.put(importName, cfMAp);
                    cfAllMAp_2007.put(importName, cfMAp_2007);
                    cfAllMAp_2019.put(importName, cfMAp_2019);
                    agbAllMAp.put(importName, agbMAp);
                    //导出到指定目录
                    ExportCalculationExcelUtil.exportData(list, importName);
                }
            }
            //处理统计表格  CF数据表格
            dealCfAllMap(cfAllMAp);
            dealCfAllMapOfYear(cfAllMAp_2007, "2007");
            dealCfAllMapOfYear(cfAllMAp_2019, "2019");
            //处理统计表格  AGB数据表格
            dealAgbAllMap(agbAllMAp);
            //处理年份相关数据
            dealCommodityYearMAp(commodityYearMAp);
            //新加计算  （四种文件、各个年份的各个国家的数据）
            eachCountryEachYear(files);
        }
        logger.info("导入结束--->{}");
        return LayuiReturnUtils.LayuiReturnLogin("上传Excel数据", 1);
    }

    /**
     * 处理统计表格  CF数据表格
     *
     * @param cfAllMAp
     */
    public void dealCfAllMap(Map<String, Map<String, BigDecimal>> cfAllMAp) {
        //处理统计表格  CF数据表格
        if (cfAllMAp != null) {
            Map<String, List<BigDecimal>> conCfMAp = new LinkedHashMap<>(10);
            List<String> nameList = new ArrayList<>();
            //转化 map存储顺序
            for (Map.Entry<String, Map<String, BigDecimal>> cfData : cfAllMAp.entrySet()) {
                String fileName = cfData.getKey();
                nameList.add(fileName);
                Map<String, BigDecimal> cfMap = cfData.getValue();

                for (Map.Entry<String, BigDecimal> map : cfMap.entrySet()) {
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
            List<List<String>> cfList = new ArrayList<>();
            nameList.add(0, "Commodity.Code");
            cfList.add(nameList);
            for (Map.Entry<String, List<BigDecimal>> cfData : conCfMAp.entrySet()) {
                List<String> l = new ArrayList<>();
                //品类
                String commodity = cfData.getKey();
                l.add(commodity);
                for (BigDecimal cf : cfData.getValue()) {
                    l.add(String.valueOf(cf));
                }
                cfList.add(l);
            }
            //导出到指定目录
            ExportCalculationExcelUtil.exportDataOfTotal(cfList, "WCF.xls");
        }
    }

    /**
     * 处理统计表格  CF数据表格  (单独年份)
     *
     * @param cfAllMAp
     * @param year
     */
    public void dealCfAllMapOfYear(Map<String, Map<String, BigDecimal>> cfAllMAp, String year) {
        //处理统计表格  CF数据表格
        if (cfAllMAp != null) {
            Map<String, List<BigDecimal>> conCfMAp = new LinkedHashMap<>(10);
            List<String> nameList = new ArrayList<>();
            //转化 map存储顺序
            for (Map.Entry<String, Map<String, BigDecimal>> cfData : cfAllMAp.entrySet()) {
                String fileName = cfData.getKey();
                nameList.add(fileName);
                Map<String, BigDecimal> cfMap = cfData.getValue();

                for (Map.Entry<String, BigDecimal> map : cfMap.entrySet()) {
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
            List<List<String>> cfList = new ArrayList<>();
            nameList.add(0, "Commodity.Code");
            cfList.add(nameList);
            for (Map.Entry<String, List<BigDecimal>> cfData : conCfMAp.entrySet()) {
                List<String> l = new ArrayList<>();
                //品类
                String commodity = cfData.getKey();
                l.add(commodity);
                for (BigDecimal cf : cfData.getValue()) {
                    l.add(String.valueOf(cf));
                }
                cfList.add(l);
            }
            //导出到指定目录
            if (year.equals("2007")) {
                ExportCalculationExcelUtil.exportDataOfTotal(cfList, "WCF_2007.xls");
            } else if (year.equals("2019")) {
                ExportCalculationExcelUtil.exportDataOfTotal(cfList, "WCF_2019.xls");
            }
        }
    }

    /**
     * 处理统计表格  AGB数据表格
     *
     * @param agbAllMAp
     */
    public void dealAgbAllMap(Map<String, Map<String, BigDecimal>> agbAllMAp) {
        //处理统计表格  CF数据表格
        if (agbAllMAp != null) {
            Map<String, List<BigDecimal>> conAgbMAp = new LinkedHashMap<>(10);
            List<String> nameList = new ArrayList<>();
            //转化 map存储顺序
            for (Map.Entry<String, Map<String, BigDecimal>> agbData : agbAllMAp.entrySet()) {
                String fileName = agbData.getKey();
                nameList.add(fileName);
                Map<String, BigDecimal> agbMap = agbData.getValue();

                for (Map.Entry<String, BigDecimal> map : agbMap.entrySet()) {
                    String commodity = map.getKey();
                    BigDecimal total = map.getValue();

                    if (conAgbMAp.get(commodity) != null) {
                        List<BigDecimal> fileList = conAgbMAp.get(commodity);
                        int index = nameList.indexOf(fileName);
                        if (index == fileList.size()) {
                            fileList.add(nameList.indexOf(fileName), total);
                        } else {
                            fileList.add(new BigDecimal(0));
                            fileList.add(total);
                        }
                        conAgbMAp.put(commodity, fileList);
                    } else {
                        List<BigDecimal> fileList = new ArrayList<>();
                        fileList.add(total);
                        conAgbMAp.put(commodity, fileList);
                    }
                }
            }
            //封装 excel数据
            List<List<String>> agbList = new ArrayList<>();
            nameList.add(0, "Commodity.Code");
            agbList.add(nameList);
            for (Map.Entry<String, List<BigDecimal>> agbData : conAgbMAp.entrySet()) {
                List<String> l = new ArrayList<>();
                //品类
                String commodity = agbData.getKey();
                l.add(commodity);
                for (BigDecimal agb : agbData.getValue()) {
                    l.add(String.valueOf(agb));
                }
                agbList.add(l);
            }
            //导出到指定目录
            ExportCalculationExcelUtil.exportDataOfTotal(agbList, "WAGB.xls");
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
     * @param ageTotal
     * @param cfTotal
     */
    private Map<String, Map<String, List<BigDecimal>>> obtainYearData(List<String> datalist, Map<String, Map<String,
            List<BigDecimal>>> commodityYearMAp, String commodity, BigDecimal ageTotal, BigDecimal cfTotal) {
        //处理年份
        String year = datalist.get(1);
        String netWeigh = datalist.get(9);
        netWeigh = netWeigh.replace("NA", "0");
        String trade = datalist.get(10);
        trade = trade.replace("NA", "0");
        //储存四种
        List<BigDecimal> yearList;
        Map<String, List<BigDecimal>> yearMap = new TreeMap<>();

        if (commodityYearMAp.get(commodity) != null) {
            yearMap = commodityYearMAp.get(commodity);
            if (yearMap != null) {
                yearList = yearMap.get(year);
                if (CollectionUtils.isNotEmpty(yearList)) {
                    BigDecimal one = yearList.get(0);
                    BigDecimal two = yearList.get(1);
                    BigDecimal three = yearList.get(2);
                    BigDecimal four = yearList.get(3);

                    one = one.add(new BigDecimal(netWeigh));
                    two = two.add(new BigDecimal(trade));
                    three = three.add(ageTotal);
                    four = four.add(cfTotal);

                    yearList = new ArrayList<>();
                    yearList.add(0, one);
                    yearList.add(1, two);
                    yearList.add(2, three);
                    yearList.add(3, four);
                    yearMap.put(year, yearList);
                    commodityYearMAp.put(commodity, yearMap);
                } else {
                    yearList = new ArrayList<>();
                    yearList.add(new BigDecimal(netWeigh));
                    yearList.add(new BigDecimal(trade));
                    yearList.add(ageTotal);
                    yearList.add(cfTotal);
                    yearMap.put(year, yearList);
                    commodityYearMAp.put(commodity, yearMap);
                }
            } else {
                yearList = new ArrayList<>();
                yearList.add(new BigDecimal(netWeigh));
                yearList.add(new BigDecimal(trade));
                yearList.add(ageTotal);
                yearList.add(cfTotal);
                yearMap.put(year, yearList);
                commodityYearMAp.put(commodity, yearMap);
            }
        } else {
            yearList = new ArrayList<>();
            yearList.add(new BigDecimal(netWeigh));
            yearList.add(new BigDecimal(trade));
            yearList.add(ageTotal);
            yearList.add(cfTotal);
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
            List<List<String>> weighList = new ArrayList<>();
            List<List<String>> tradeList = new ArrayList<>();
            List<List<String>> ageList = new ArrayList<>();
            List<List<String>> cfList = new ArrayList<>();
            weighList.add(nameList);
            tradeList.add(nameList);
            ageList.add(nameList);
            cfList.add(nameList);
            //转化 map存储顺序
            for (Map.Entry<String, Map<String, List<BigDecimal>>> yearData : commodityYearMAp.entrySet()) {
                String commodity = yearData.getKey();
                List<String> o = new ArrayList<>(15);
                List<String> t = new ArrayList<>(15);
                List<String> s = new ArrayList<>(15);
                List<String> f = new ArrayList<>(15);
                o.add(commodity);
                t.add(commodity);
                s.add(commodity);
                f.add(commodity);
                for (int i = 0; i < 15; i++) {
                    o.add("0");
                    t.add("0");
                    s.add("0");
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
                        BigDecimal one = list.get(0);
                        BigDecimal two = list.get(1);
                        BigDecimal three = list.get(2);
                        BigDecimal four = list.get(3);
                        int index = nameList.indexOf(year);
                        o.set(index, String.valueOf(one));
                        t.set(index, String.valueOf(two));
                        s.set(index, String.valueOf(three));
                        f.set(index, String.valueOf(four));
                    }
                }
                weighList.add(o);
                tradeList.add(t);
                ageList.add(s);
                cfList.add(f);
            }
            //导出到指定目录
            ExportCalculationExcelUtil.exportDataOfYear(weighList, "W_Netweight.xls");
            ExportCalculationExcelUtil.exportDataOfYear(tradeList, "W_Trade.xls");
            ExportCalculationExcelUtil.exportDataOfYear(ageList, "W_AGB.xls");
            ExportCalculationExcelUtil.exportDataOfYear(cfList, "W_CF.xls");
        }
    }


    /**
     * 新加计算  （四种文件、各个年份的各个国家的数据）
     *
     * @return
     */
    public String eachCountryEachYear(File[] files) {
        List<List<String>> list = null;
        //储存所有文件的 CF数据集合
        Map<String, Map<String, BigDecimal>> cfAllMAp = new LinkedHashMap<>(10);
        //储存所有文件的 AGB数据集合
        Map<String, Map<String, BigDecimal>> agbAllMAp = new LinkedHashMap<>(10);
        //储存所有文件的 netWeight数据集合
        Map<String, Map<String, BigDecimal>> netWeightAllMAp = new LinkedHashMap<>(10);
        //储存所有文件的 trade 数据集合
        Map<String, Map<String, BigDecimal>> tradeAllMAp = new LinkedHashMap<>(10);
        if (files.length != 0) {
            //循环所有的文件
            for (File file : files) {
                FileItem fileItem = createFileItem(file);
                MultipartFile multipartFile = new CommonsMultipartFile(fileItem);
                if (multipartFile != null && !multipartFile.isEmpty()) {

                    String importName = multipartFile.getOriginalFilename();
                    String[] arr = importName.split(".xls");
                    importName = arr[0];
                    //储存当前文件的 CF数据集合
                    Map<String, BigDecimal> cfMAp = new LinkedHashMap<>(10);
                    //储存当前文件的 AGB数据集合
                    Map<String, BigDecimal> agbMAp = new LinkedHashMap<>(10);
                    try {
                        list = ExcelMarketUploadUtils.readExcel(multipartFile);
                        if (list != null) {
                            for (List<String> datalist : list) {
                                BigDecimal ageTotal = new BigDecimal(0);
                                BigDecimal cfTotal = new BigDecimal(0);
                                String year = datalist.get(1);
                                String commodity = datalist.get(5);
                                String netWeight = datalist.get(9);
                                String trade = datalist.get(10);
                                trade = trade.replace("NA", "0");
                                BigDecimal tradeBig = new BigDecimal(trade);
                                if (commodity.equals("441210")) {
                                    netWeight = datalist.get(8);
                                }
                                String age = map_AGB.get(commodity);
                                String cf = map_CF.get(commodity);

                                netWeight = netWeight.replace("NA", "0");
                                BigDecimal weight = new BigDecimal(netWeight);
                                if (netWeight.matches("[0-9]+")) {
                                    //计算乘积
                                    ageTotal = weight.multiply(new BigDecimal(age));
                                    cfTotal = weight.multiply(new BigDecimal(cf));
                                }
                                if (cfTotal == null) {
                                    logger.info("--->{}", "该值为空");
                                }

                                Map<String, BigDecimal> cfMap = cfAllMAp.get(year);
                                if (cfMap != null) {
                                    BigDecimal value = cfMap.get(importName);
                                    if (value == null) {
                                        value = new BigDecimal(0);
                                    }
                                    value = value.add(cfTotal);
                                    cfMap.put(importName, value);
                                } else {
                                    cfMap = new HashMap<>();
                                    cfMap.put(importName, cfTotal);
                                }
                                cfAllMAp.put(year, cfMap);

                                Map<String, BigDecimal> agbMap = agbAllMAp.get(year);
                                if (agbMap != null) {
                                    BigDecimal value = agbMap.get(importName);
                                    if (value == null) {
                                        value = new BigDecimal(0);
                                    }
                                    value = value.add(ageTotal);
                                    agbMap.put(importName, value);
                                } else {
                                    agbMap = new HashMap<>();
                                    agbMap.put(importName, ageTotal);
                                }
                                agbAllMAp.put(year, agbMap);

                                Map<String, BigDecimal> weightMap = netWeightAllMAp.get(year);
                                if (weightMap != null) {
                                    BigDecimal value = weightMap.get(importName);
                                    if (value == null) {
                                        value = new BigDecimal(0);
                                    }
                                    value = value.add(weight);
                                    weightMap.put(importName, value);
                                } else {
                                    weightMap = new HashMap<>();
                                    weightMap.put(importName, weight);
                                }
                                netWeightAllMAp.put(year, weightMap);

                                Map<String, BigDecimal> tradeMap = tradeAllMAp.get(year);
                                if (tradeMap != null) {
                                    BigDecimal value = tradeMap.get(importName);
                                    if (value == null) {
                                        value = new BigDecimal(0);
                                    }
                                    value = value.add(tradeBig);
                                    tradeMap.put(importName, value);
                                } else {
                                    tradeMap = new HashMap<>();
                                    tradeMap.put(importName, tradeBig);
                                }
                                tradeAllMAp.put(year, tradeMap);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            //导出到文件
            dealEachCountryEachYearMap(cfAllMAp, agbAllMAp, netWeightAllMAp, tradeAllMAp);
        }
        logger.info("导入结束--->{}");
        return LayuiReturnUtils.LayuiReturnLogin("上传Excel数据", 1);
    }

    /**
     * 新加计算  （四种文件、各个年份的各个国家的数据）
     *
     * @param cfAllMAp
     * @param agbAllMAp
     * @param netWeightAllMAp
     * @param tradeAllMAp
     */
    public void dealEachCountryEachYearMap(
            Map<String, Map<String, BigDecimal>> cfAllMAp, Map<String, Map<String, BigDecimal>> agbAllMAp,
            Map<String, Map<String, BigDecimal>> netWeightAllMAp, Map<String, Map<String, BigDecimal>> tradeAllMAp) {


        //封装 excel数据
        List<List<String>> weighList = new ArrayList<>();
        List<List<String>> tradeList = new ArrayList<>();
        List<List<String>> ageList = new ArrayList<>();
        List<List<String>> cfList = new ArrayList<>();

        //相关数据
        if (cfAllMAp != null) {
            //初始化
            List<String> o = new ArrayList<>(15);
            o.add("YEAR");
            for (Map.Entry<String, Map<String, BigDecimal>> cfData : cfAllMAp.entrySet()) {
                Map<String, BigDecimal> yearMap = cfData.getValue();
                for (Map.Entry<String, BigDecimal> map : yearMap.entrySet()) {
                    String country = map.getKey();
                    if (!o.contains(country)) {
                        o.add(country);
                    }
                }
            }
            cfList.add(o);
            //转化 map
            for (Map.Entry<String, Map<String, BigDecimal>> cfData : cfAllMAp.entrySet()) {
                String year = cfData.getKey();
                List<String> l = new ArrayList<>(22);
                l.add(year);
                for (int i = 0; i < 22; i++) {
                    l.add("0");
                }
                Map<String, BigDecimal> yearMap = cfData.getValue();
                for (Map.Entry<String, BigDecimal> map : yearMap.entrySet()) {
                    String country = map.getKey();
                    int index = o.indexOf(country);
                    BigDecimal value = map.getValue();
                    l.set(index, String.valueOf(value));
                }
                cfList.add(l);
            }
            //导出到指定目录
            ExportCalculationExcelUtil.exportDataOfCountry(cfList, "X_COUNTRY_CF.xls");
        }
        //相关数据
        if (agbAllMAp != null) {
            //初始化
            List<String> o = new ArrayList<>(15);
            o.add("YEAR");
            for (Map.Entry<String, Map<String, BigDecimal>> cfData : agbAllMAp.entrySet()) {
                Map<String, BigDecimal> yearMap = cfData.getValue();
                for (Map.Entry<String, BigDecimal> map : yearMap.entrySet()) {
                    String country = map.getKey();
                    if (!o.contains(country)) {
                        o.add(country);
                    }
                }
            }
            ageList.add(o);
            //转化 map
            for (Map.Entry<String, Map<String, BigDecimal>> cfData : agbAllMAp.entrySet()) {
                String year = cfData.getKey();
                List<String> l = new ArrayList<>(22);
                l.add(year);
                for (int i = 0; i < 22; i++) {
                    l.add("0");
                }
                Map<String, BigDecimal> yearMap = cfData.getValue();
                for (Map.Entry<String, BigDecimal> map : yearMap.entrySet()) {
                    String country = map.getKey();
                    int index = o.indexOf(country);
                    BigDecimal value = map.getValue();
                    l.set(index, String.valueOf(value));
                }
                ageList.add(l);
            }
            //导出到指定目录
            ExportCalculationExcelUtil.exportDataOfCountry(ageList, "X_COUNTRY_AGB.xls");
        }
        //相关数据
        if (netWeightAllMAp != null) {
            //初始化
            List<String> o = new ArrayList<>(15);
            o.add("YEAR");
            for (Map.Entry<String, Map<String, BigDecimal>> cfData : netWeightAllMAp.entrySet()) {
                Map<String, BigDecimal> yearMap = cfData.getValue();
                for (Map.Entry<String, BigDecimal> map : yearMap.entrySet()) {
                    String country = map.getKey();
                    if (!o.contains(country)) {
                        o.add(country);
                    }
                }
            }
            weighList.add(o);
            //转化 map
            for (Map.Entry<String, Map<String, BigDecimal>> cfData : netWeightAllMAp.entrySet()) {
                String year = cfData.getKey();
                List<String> l = new ArrayList<>(22);
                l.add(year);
                for (int i = 0; i < 22; i++) {
                    l.add("0");
                }
                Map<String, BigDecimal> yearMap = cfData.getValue();
                for (Map.Entry<String, BigDecimal> map : yearMap.entrySet()) {
                    String country = map.getKey();
                    int index = o.indexOf(country);
                    BigDecimal value = map.getValue();
                    l.set(index, String.valueOf(value));
                }
                weighList.add(l);
            }
            //导出到指定目录
            ExportCalculationExcelUtil.exportDataOfCountry(weighList, "X_COUNTRY_WEIGH.xls");
        }
        //相关数据
        if (tradeAllMAp != null) {
            //初始化
            List<String> o = new ArrayList<>(15);
            o.add("YEAR");
            for (Map.Entry<String, Map<String, BigDecimal>> cfData : tradeAllMAp.entrySet()) {
                Map<String, BigDecimal> yearMap = cfData.getValue();
                for (Map.Entry<String, BigDecimal> map : yearMap.entrySet()) {
                    String country = map.getKey();
                    if (!o.contains(country)) {
                        o.add(country);
                    }
                }
            }
            tradeList.add(o);
            //转化 map
            for (Map.Entry<String, Map<String, BigDecimal>> cfData : tradeAllMAp.entrySet()) {
                String year = cfData.getKey();
                List<String> l = new ArrayList<>(22);
                l.add(year);
                for (int i = 0; i < 22; i++) {
                    l.add("0");
                }
                Map<String, BigDecimal> yearMap = cfData.getValue();
                for (Map.Entry<String, BigDecimal> map : yearMap.entrySet()) {
                    String country = map.getKey();
                    int index = o.indexOf(country);
                    BigDecimal value = map.getValue();
                    l.set(index, String.valueOf(value));
                }
                tradeList.add(l);
            }
            //导出到指定目录
            ExportCalculationExcelUtil.exportDataOfCountry(tradeList, "X_COUNTRY_TRADE.xls");
        }
    }

    /**
     * 二次计算  将原有CF计算计算结果重新计算CM
     * http://127.0.0.1:8080/cloud-layui/calculationExcel/secondaryCalculationCM
     *
     * @return
     */
    @RequestMapping(value = "/calculationExcel/secondaryCalculationCM", method = RequestMethod.GET)
    public String secondaryCalculationCM() {
        logger.info("初始化开始--->{}");
        initLocationId2();
        logger.info("导入开始--->{}");
        //默认路径
        String filePath = "/Users/dramatic/dramatic/excel/2007-2021_process/";
        if (CollectionUtils.isNotEmpty(list_TPE)) {
            List<List<String>> list = null;
            for (String s : list_TPE) {
                String newFilePath = filePath + s + ".xls";
                File file = new File(newFilePath);
                if (file != null) {
                    FileItem fileItem = createFileItem(file);
                    MultipartFile multipartFile = new CommonsMultipartFile(fileItem);
                    if (multipartFile != null && !multipartFile.isEmpty()) {
                        try {
                            list = ExcelMarketUploadUtils.readExcel(multipartFile);
                            if (list != null) {
                                //map_TPE
                                String tpe = map_TPE.get(s);
                                BigDecimal ce = new BigDecimal(0);
                                for (List<String> datalist : list) {
                                    String commodity = datalist.get(5);
                                    String netWeight = datalist.get(9);
                                    if (commodity.equals("441210")) {
                                        netWeight = datalist.get(8);
                                    }
                                    netWeight.replace("NA", "0");
                                    if (netWeight.matches("[0-9]+")) {
                                        BigDecimal weight = new BigDecimal(netWeight);
                                        //计算乘积
                                        ce = weight.multiply(new BigDecimal(tpe));
                                    }
                                    //插入新的数据
                                    datalist.add(String.valueOf(ce));
                                    String cf = datalist.get(14);
                                    ce = ce.add(new BigDecimal(cf));
                                    datalist.add(String.valueOf(ce));
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        //导出到指定目录
                        ExportCalculationExcelUtil.exportDataOfCM(list, s + ".xls");
                    }
                }
            }
        }
        logger.info("导入结束--->{}");
        return LayuiReturnUtils.LayuiReturnLogin("上传Excel数据", 1);
    }

}
