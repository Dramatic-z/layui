package com.dnt.cloud.core.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.dnt.cloud.CommonConstants;
import com.dnt.cloud.common.lang.StringUtil;
import com.dnt.cloud.common.util.money.Money;
import com.dnt.cloud.core.common.exceptions.ReturnCode;
import com.dnt.cloud.core.common.exceptions.ValidateException;

public final class GOPCheckUtil implements CommonConstants {

    // 支持0-2位小数数字,大于0
    public static boolean isNumberNoZero(String str) {
        String regex = "^(?!0(\\d|\\.0+$|$))\\d+(\\.\\d{1,2})?$"; // 大于0
        Pattern pattern = Pattern.compile(regex);
        Matcher match = pattern.matcher(str);
        // match.find()
        return match.matches();
    }

    // 支持0-2位小数数字
    public static boolean isNumber(String str) {
        String regex = "^([0-9]+(.[0-9]{1,2})?)|([0-9]+(.[0-9]{1,2})?)$"; // 包括0，0.00
        Pattern pattern = Pattern.compile(regex);
        Matcher match = pattern.matcher(str);
        // match.find()
        return match.matches();
    }

    // 正整数，不包括0
    public static boolean isInt(String str) {
        String regex = "^[0-9]*[1-9][0-9]*$"; //
        Pattern pattern = Pattern.compile(regex);
        Matcher match = pattern.matcher(str);
        // match.find()
        return match.matches();
    }
    
    /**精确到小数点后两位*/
	public static boolean checkNumberWithDecimal(String str) {
        if (StringUtil.isBlank(str)) {
            return false;
        }
        //String check = "^\\d+(\\.\\d{1,2})$";
        String check = "^(([0-9]+\\d*)|([0-9]+\\d*\\.\\d{1,2}))$";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(str.trim());
        return matcher.matches();
    }
	
	/**校验负数*/
	public static boolean checkIsNegtive(String str) {
        if (StringUtil.isBlank(str)) {
            return false;
        }
        String check = "^-[0-9]*[1-9][0-9]*$";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(str.trim());
        return matcher.matches();
    }
	
	/**校验数字(不带小数点)*/
	public static boolean checkIsNumber(String str) {
        if (StringUtil.isBlank(str)) {
            return false;
        }
        String check = "[0-9]*";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(str.trim());
        return matcher.matches();
    }
	
	/**校验手机号*/
	public static boolean checkIsMobile(String str) {
        if (StringUtil.isBlank(str)) {
            return false;
        }
       /* Pattern regex = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");  
        Matcher matcher = regex.matcher(str.trim()); 
        return matcher.matches();*/
        return checkIsNumber(str);
    }
	
	 public static void main(String[] args) {
        /*String str = "0.243";
        System.out.println(GOPCheckUtil.checkNumber(str));*/
	 
	 	/*String str = "-q";
        System.out.println(GOPCheckUtil.checkIsNegtive(str));*/
		 
		/*String str = "2.7";
	    System.out.println(GOPCheckUtil.checkIsNumber(str));*/
	    
	    /*String str = "0";
	    System.out.println(GOPCheckUtil.checkNumberWithDecimal(str));*/
		 
		String str = "12016155153";
		System.out.println(GOPCheckUtil.checkIsMobile(str)); 
    }

    public static Money convertMoney(String moneyStr) {
        Money result = new Money();
        if (StringUtil.isEmpty(moneyStr)) {
            return result;
        } else {
            try {
                result = new Money(moneyStr);
            } catch (NumberFormatException e) {
                // 金额格式错误，有字母等
                throw new ValidateException(ReturnCode.ILLEGAL_AMOUNT_FORMAT);
            }
            return result;
        }
    }

}
