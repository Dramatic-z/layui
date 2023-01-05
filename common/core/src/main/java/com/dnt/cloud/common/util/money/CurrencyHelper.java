package com.dnt.cloud.common.util.money;

import com.dnt.cloud.common.lang.StringUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Currency;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *     用于JDK1.6，根据USD等币种Code获得阿拉伯数字编号
 *     1.7中直接使用Currency.getInstance().getNumbericCode()方法。
 * </p>
 *
 * @author zhangjiewen
 * @version $Id: CurrencyHelper.java, v 1.0 2015/11/19 15:27 zhangjiewen Exp $
 */
public class CurrencyHelper {
    private static Logger logger = LoggerFactory.getLogger(CurrencyHelper.class);
    private static Map<String,String> numericCodeMapping = new HashMap<String, String>(218);
    private static boolean isJDKSupportNumericCode = false;
    private static Method getNumericCodeMethod = null;
    static{
        Class<Currency> currencyClass = Currency.class;
        try {
            getNumericCodeMethod = currencyClass.getMethod("getNumericCode", null);
            isJDKSupportNumericCode =true;
        }catch (NoSuchMethodException e){
            logger.info("该版本JDK的java.util.Currency类不支持getNumericCode方法.使用硬加载方式.");
//            System.out.println("该版本JDK的java.util.Currency类不支持getNumericCode方法.使用硬加载方式.");
        }
        if(!isJDKSupportNumericCode) {
            numericCodeMapping.put("INR", "356");

            numericCodeMapping.put("MGA", "969");

            numericCodeMapping.put("AUD", "036");

            numericCodeMapping.put("XBA", "955");

            numericCodeMapping.put("TRL", "792");

            numericCodeMapping.put("MYR", "458");

            numericCodeMapping.put("UYU", "858");

            numericCodeMapping.put("IDR", "360");

            numericCodeMapping.put("GMD", "270");

            numericCodeMapping.put("BHD", "048");

            numericCodeMapping.put("AMD", "051");

            numericCodeMapping.put("RUR", "810");

            numericCodeMapping.put("MKD", "807");

            numericCodeMapping.put("BAM", "977");

            numericCodeMapping.put("RWF", "646");

            numericCodeMapping.put("FJD", "242");

            numericCodeMapping.put("JMD", "388");

            numericCodeMapping.put("NAD", "516");

            numericCodeMapping.put("XFO", "000");

            numericCodeMapping.put("HUF", "348");

            numericCodeMapping.put("SIT", "705");

            numericCodeMapping.put("BRL", "986");

            numericCodeMapping.put("MRO", "478");

            numericCodeMapping.put("VEF", "937");

            numericCodeMapping.put("WST", "882");

            numericCodeMapping.put("KHR", "116");

            numericCodeMapping.put("SBD", "090");

            numericCodeMapping.put("JOD", "400");

            numericCodeMapping.put("BWP", "072");

            numericCodeMapping.put("ZWL", "932");

            numericCodeMapping.put("XAU", "959");

            numericCodeMapping.put("LUF", "442");

            numericCodeMapping.put("MZM", "508");

            numericCodeMapping.put("LBP", "422");

            numericCodeMapping.put("GRD", "300");

            numericCodeMapping.put("THB", "764");

            numericCodeMapping.put("MNT", "496");

            numericCodeMapping.put("IRR", "364");

            numericCodeMapping.put("CAD", "124");

            numericCodeMapping.put("GWP", "624");

            numericCodeMapping.put("NOK", "578");

            numericCodeMapping.put("XPD", "964");

            numericCodeMapping.put("CUC", "931");

            numericCodeMapping.put("OMR", "512");

            numericCodeMapping.put("TWD", "901");

            numericCodeMapping.put("CRC", "188");

            numericCodeMapping.put("YUM", "891");

            numericCodeMapping.put("UZS", "860");

            numericCodeMapping.put("VND", "704");

            numericCodeMapping.put("ITL", "380");

            numericCodeMapping.put("QAR", "634");

            numericCodeMapping.put("XAG", "961");

            numericCodeMapping.put("GBP", "826");

            numericCodeMapping.put("PKR", "586");

            numericCodeMapping.put("LAK", "418");

            numericCodeMapping.put("DOP", "214");

            numericCodeMapping.put("RON", "946");

            numericCodeMapping.put("BGL", "100");

            numericCodeMapping.put("SCR", "690");

            numericCodeMapping.put("KES", "404");

            numericCodeMapping.put("CLP", "152");

            numericCodeMapping.put("BIF", "108");

            numericCodeMapping.put("CUP", "192");

            numericCodeMapping.put("BBD", "052");

            numericCodeMapping.put("STD", "678");

            numericCodeMapping.put("TPE", "626");

            numericCodeMapping.put("XTS", "963");

            numericCodeMapping.put("XAF", "950");

            numericCodeMapping.put("BTN", "064");

            numericCodeMapping.put("CHF", "756");

            numericCodeMapping.put("ZWN", "942");

            numericCodeMapping.put("KWD", "414");

            numericCodeMapping.put("AZN", "944");

            numericCodeMapping.put("KYD", "136");

            numericCodeMapping.put("USS", "998");

            numericCodeMapping.put("SYP", "760");

            numericCodeMapping.put("GIP", "292");

            numericCodeMapping.put("NPR", "524");

            numericCodeMapping.put("LRD", "430");

            numericCodeMapping.put("CLF", "990");

            numericCodeMapping.put("AOA", "973");

            numericCodeMapping.put("BOV", "984");

            numericCodeMapping.put("GEL", "981");

            numericCodeMapping.put("XBB", "956");

            numericCodeMapping.put("MUR", "480");

            numericCodeMapping.put("BOB", "068");

            numericCodeMapping.put("CNY", "156");

            numericCodeMapping.put("AED", "784");

            numericCodeMapping.put("XUA", "965");

            numericCodeMapping.put("RSD", "941");

            numericCodeMapping.put("USN", "997");

            numericCodeMapping.put("TND", "788");

            numericCodeMapping.put("HTG", "332");

            numericCodeMapping.put("ETB", "230");

            numericCodeMapping.put("TZS", "834");

            numericCodeMapping.put("COP", "170");

            numericCodeMapping.put("CZK", "203");

            numericCodeMapping.put("XFU", "000");

            numericCodeMapping.put("KRW", "410");

            numericCodeMapping.put("IQD", "368");

            numericCodeMapping.put("KZT", "398");

            numericCodeMapping.put("KMF", "174");

            numericCodeMapping.put("HNL", "340");

            numericCodeMapping.put("MMK", "104");

            numericCodeMapping.put("HKD", "344");

            numericCodeMapping.put("MZN", "943");

            numericCodeMapping.put("UAH", "980");

            numericCodeMapping.put("BGN", "975");

            numericCodeMapping.put("MOP", "446");

            numericCodeMapping.put("FKP", "238");

            numericCodeMapping.put("DEM", "276");

            numericCodeMapping.put("CVE", "132");

            numericCodeMapping.put("XCD", "951");

            numericCodeMapping.put("ILS", "376");

            numericCodeMapping.put("TTD", "780");

            numericCodeMapping.put("ISK", "352");

            numericCodeMapping.put("IEP", "372");

            numericCodeMapping.put("LKR", "144");

            numericCodeMapping.put("GHC", "288");

            numericCodeMapping.put("TRY", "949");

            numericCodeMapping.put("AFA", "004");

            numericCodeMapping.put("GHS", "936");

            numericCodeMapping.put("GTQ", "320");

            numericCodeMapping.put("MXV", "979");

            numericCodeMapping.put("EEK", "233");

            numericCodeMapping.put("CYP", "196");

            numericCodeMapping.put("DZD", "012");

            numericCodeMapping.put("ROL", "946");

            numericCodeMapping.put("PYG", "600");

            numericCodeMapping.put("NIO", "558");

            numericCodeMapping.put("MVR", "462");

            numericCodeMapping.put("LTL", "440");

            numericCodeMapping.put("SDD", "736");

            numericCodeMapping.put("VEB", "862");

            numericCodeMapping.put("NZD", "554");

            numericCodeMapping.put("PHP", "608");

            numericCodeMapping.put("PGK", "598");

            numericCodeMapping.put("XSU", "994");

            numericCodeMapping.put("LVL", "428");

            numericCodeMapping.put("ERN", "232");

            numericCodeMapping.put("CDF", "976");

            numericCodeMapping.put("TJS", "972");

            numericCodeMapping.put("LYD", "434");

            numericCodeMapping.put("BDT", "050");

            numericCodeMapping.put("ZWD", "716");

            numericCodeMapping.put("TOP", "776");

            numericCodeMapping.put("SRG", "740");

            numericCodeMapping.put("SHP", "654");

            numericCodeMapping.put("SGD", "702");

            numericCodeMapping.put("EUR", "978");

            numericCodeMapping.put("VUV", "548");

            numericCodeMapping.put("MGF", "450");

            numericCodeMapping.put("KPW", "408");

            numericCodeMapping.put("JPY", "392");

            numericCodeMapping.put("BSD", "044");

            numericCodeMapping.put("SOS", "706");

            numericCodeMapping.put("YER", "886");

            numericCodeMapping.put("ZAR", "710");

            numericCodeMapping.put("TMM", "795");

            numericCodeMapping.put("GYD", "328");

            numericCodeMapping.put("ZWD", "935");

            numericCodeMapping.put("UGX", "800");

            numericCodeMapping.put("USD", "840");

            numericCodeMapping.put("BYB", "112");

            numericCodeMapping.put("KGS", "417");

            numericCodeMapping.put("BND", "096");

            numericCodeMapping.put("XBD", "958");

            numericCodeMapping.put("LSL", "426");

            numericCodeMapping.put("NGN", "566");

            numericCodeMapping.put("XOF", "952");

            numericCodeMapping.put("AWG", "533");

            numericCodeMapping.put("SLL", "694");

            numericCodeMapping.put("BEF", "056");

            numericCodeMapping.put("BMD", "060");

            numericCodeMapping.put("HRK", "191");

            numericCodeMapping.put("SZL", "748");

            numericCodeMapping.put("AYM", "945");

            numericCodeMapping.put("ADP", "020");

            numericCodeMapping.put("PEN", "604");

            numericCodeMapping.put("CSD", "891");

            numericCodeMapping.put("EGP", "818");

            numericCodeMapping.put("MWK", "454");

            numericCodeMapping.put("NLG", "528");

            numericCodeMapping.put("ANG", "532");

            numericCodeMapping.put("FRF", "250");

            numericCodeMapping.put("XDR", "960");

            numericCodeMapping.put("SAR", "682");

            numericCodeMapping.put("ZMK", "894");

            numericCodeMapping.put("TMT", "934");

            numericCodeMapping.put("MDL", "498");

            numericCodeMapping.put("ESP", "724");

            numericCodeMapping.put("BYR", "974");

            numericCodeMapping.put("XPT", "962");

            numericCodeMapping.put("MTL", "470");

            numericCodeMapping.put("BZD", "084");

            numericCodeMapping.put("MAD", "504");

            numericCodeMapping.put("DKK", "208");

            numericCodeMapping.put("XPF", "953");

            numericCodeMapping.put("PLN", "985");

            numericCodeMapping.put("AZM", "031");

            numericCodeMapping.put("MXN", "484");

            numericCodeMapping.put("GNF", "324");

            numericCodeMapping.put("PTE", "620");

            numericCodeMapping.put("SRD", "968");

            numericCodeMapping.put("ATS", "040");

            numericCodeMapping.put("XXX", "999");

            numericCodeMapping.put("PAB", "590");

            numericCodeMapping.put("FIM", "246");

            numericCodeMapping.put("AFN", "971");

            numericCodeMapping.put("SVC", "222");

            numericCodeMapping.put("SDG", "938");

            numericCodeMapping.put("XBC", "957");

            numericCodeMapping.put("ARS", "032");

            numericCodeMapping.put("DJF", "262");

            numericCodeMapping.put("SKK", "703");

            numericCodeMapping.put("SEK", "752");

            numericCodeMapping.put("RUB", "643");

            numericCodeMapping.put("ALL", "008");
        }
    }
    public static String getByCurrencyCode(String currencyCode){
        if(isJDKSupportNumericCode){
            try {
                return getNumericCodeMethod.invoke(Currency.getInstance(currencyCode),null).toString();
            } catch (Exception e) {
                logger.error("执行getNumericCode方法报错.",e);
                throw new IllegalArgumentException("编号为"+currencyCode + "的币种不存在.",e);
            }
        }else {
            String numericCode = numericCodeMapping.get(currencyCode);
            if(StringUtil.isBlank(numericCode)){
                throw new IllegalArgumentException("编号为"+currencyCode + "的币种不存在.");
            }
            return numericCode;
        }
    }
}
