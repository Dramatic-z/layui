package com.dnt.cloud.core.common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.joda.time.DateTime;

import com.dnt.cloud.common.lang.StringUtil;

/**
 * <p>
 * 时间工具类
 * </p>
 * 
 * @author hazyhao
 *
 */
public final class DateUtil extends CommonDateUtil {

	private static ThreadLocal<Map<String, DateFormat>> patternMapTL = new ThreadLocal<Map<String, DateFormat>>();

	private static DateFormat getDateFormat(String pattern) {
		Map<String, DateFormat> mapTL = patternMapTL.get();
		if (mapTL == null) {
			mapTL = new HashMap<String, DateFormat>();
			patternMapTL.set(mapTL);
		}
		DateFormat df = mapTL.get(pattern);
		if (df == null) {
			df = new SimpleDateFormat(pattern);
			mapTL.put(pattern, df);
		}
		return df;
	}

	/**
	 * 解析时间
	 * 
	 * @param date
	 *            时间字符串
	 * @param pattern
	 *            时间格式
	 * @return
	 * @throws ParseException
	 */
	public static Date parse(String date, String pattern) throws ParseException {
		return getDateFormat(pattern).parse(date);
	}

	public static Date parseDateWebFormat(String sDate) {
		DateFormat dateFormat = new SimpleDateFormat(webFormat);
		Date d = null;
		if ((sDate != null) && (sDate.length() == webFormat.length())) {
			try {
				d = dateFormat.parse(sDate);
			} catch (ParseException ex) {
				return null;
			}
		}
		return d;
	}

	public static Date parseDateShortFormat(String sDate) {
		DateFormat dateFormat = new SimpleDateFormat(shortFormat);
		Date d = null;

		if ((sDate != null) && (sDate.length() == shortFormat.length())) {
			try {
				d = dateFormat.parse(sDate);
			} catch (ParseException ex) {
				return null;
			}
		}

		return d;
	}

	/**
	 * 解析时间
	 * 
	 * @param date
	 *            时间字符串
	 * @param pattern
	 *            时间格式
	 * @return
	 * @throws ParseException
	 */
	public static DateTime parseJoda(String date, String pattern)
			throws ParseException {
		return new DateTime(getDateFormat(pattern).parse(date));
	}

	/**
	 * 返回yyyy-MM-dd HH:mm:ss的时间字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDateTime(Date date) {
		if (date == null) {
			return null;
		}
		return DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 返回yyyy-MM-dd的时间字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDate(Date date) {
		if (date == null) {
			return null;
		}
		return DateFormatUtils.format(date, "yyyy-MM-dd");
	}

	/**
	 * 检查是否符合时间格式
	 * 
	 * @param date
	 *            时间字符串
	 * @param pattern
	 *            时间格式
	 * @return 是否符合
	 */
	public static boolean checkFormat(String date, String pattern) {
		try {
			getDateFormat(pattern).parse(date);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}

	/**
	 * 检查时间格式是否为yyyy-mm-dd
	 */
	public static boolean checkDate(String date) {
		if (StringUtil.isBlank(date)) {
			return false;
		}
		try {
			getDateFormat("yyyy-MM-dd").parse(date);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}

	/**
	 * 检查时间格式是否为yyyy-mm-dd HH:mm:ss
	 */
	public static boolean checkDateTime(String datetime) {
		try {
			getDateFormat("yyyy-MM-dd HH:mm:ss").parse(datetime);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}

	public static String changeFormat(String date, String oldPattern,
			String newPattern) {
		try {
			return DateFormatUtils.format(
					getDateFormat(oldPattern).parse(date), newPattern);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 返回本月，格式：yyyy-MM
	 * 
	 * @return
	 */
	public static String getTomonth() {
		return DateFormatUtils.format(new DateTime().toDate(), "yyyy-MM");
	}

	/**
	 * 返回今日日期，格式：yyyy-MM-dd
	 * 
	 * @return
	 */
	public static String getToday() {
		return getDate(0);
	}

	/**
	 * 返回明日日期，格式：yyyy-MM-dd
	 * 
	 * @return
	 */
	public static String getNextDay() {
		return getDate(1);
	}

	/**
	 * 返回今日开始时间，格式：yyyy-MM-dd HH:mm:ss
	 * 
	 * @return
	 */
	public static String getTodayTime() {
		return getDate(0) + " 00:00:00";
	}

	/**
	 * 返回明日日期，格式：yyyy-MM-dd
	 * 
	 * @return
	 */
	public static String getNextDayTime() {
		return getDate(1) + " 00:00:00";
	}

	/**
	 * 返回日期，格式：yyyy-MM-dd
	 * 
	 * @return
	 */
	public static String getDate(int plusDays) {
		return DateFormatUtils.format(new DateTime().plusDays(plusDays)
				.toDate(), "yyyy-MM-dd");
	}

	public static Date getNow() {
		return new Date();
	}

	public static boolean afterNow(Date date) {
		return date.after(new Date());
	}

	public static boolean afterNow(DateTime date) {
		return date.toDate().after(new Date());
	}

	public static boolean beforeNow(Date date) {
		return date.before(new Date());
	}

	public static boolean beforeNow(DateTime date) {
		return date.toDate().before(new Date());
	}

	/**
	 * 获得指定时间当月起点时间
	 * 
	 * @param date
	 * @return
	 */
	public static Date getMonthBegin(Date date) {
		DateFormat df = new SimpleDateFormat(monthFormat);
		df.setLenient(false);

		String dateString = df.format(date);

		try {
			return df.parse(dateString);
		} catch (ParseException e) {
			return date;
		}
	}

	/**
	 * 取得当前时间的下几个月或者上几个月的月末时间
	 * 
	 * @param date
	 * @param cycle
	 *            值为负数时是往后推，正数时候往前推
	 * @return
	 */
	public static Date getLastDayOfMonth(Date date, int cycle) {
		DateTime datetime = new DateTime(date).minusMonths(cycle);
		int lastDay = datetime.dayOfMonth().getMaximumValue();
		return datetime.withDayOfMonth(lastDay).toDate();
	}

	/**
	 * 取得当前时间的下几个月或者上几个月的月初时间
	 * 
	 * @param date
	 * @param cycle
	 *            值为负数时是往后推，正数时候往前推
	 * @return
	 */
	public static Date getFirstDayOfMonth(Date date, int cycle) {
		DateTime datetime = new DateTime(date).minusMonths(cycle);
		return datetime.withDayOfMonth(1).toDate();
	}

	/**
	 * 取得当前时间的下几年或者上几年的年末时间
	 * 
	 * @param date
	 * @param cycle
	 *            值为负数时是往后推，正数时候往前推
	 * @return
	 */
	public static Date getLastDayOfYear(Date date, int cycle) {
		DateTime datetime = new DateTime(date).minusYears(cycle);
		return datetime.withMonthOfYear(12).withDayOfMonth(31).toDate();
	}

	/**
	 * 取得当前时间的下几年或者上几年的年初时间
	 * 
	 * @param date
	 * @param cycle
	 *            值为负数时是往后推，正数时候往前推
	 * @return
	 */
	public static Date getFirstDayOfYear(Date date, int cycle) {
		DateTime datetime = new DateTime(date).minusYears(cycle);
		return datetime.withMonthOfYear(1).withDayOfMonth(1).toDate();
	}

	/**
	 * 取得季度的开始日期
	 * 
	 * @param date
	 * @return
	 */
	public static Date getFirstDayOfQuarter(Date date) {
		DateTime datetime = new DateTime(date);
		int month = datetime.getMonthOfYear();
		if (month >= 1 && month <= 3) {
			return datetime.withMonthOfYear(1).withDayOfMonth(1).toDate();
		} else if (month >= 4 && month <= 6) {
			return datetime.withMonthOfYear(4).withDayOfMonth(1).toDate();
		} else if (month >= 7 && month <= 9) {
			return datetime.withMonthOfYear(7).withDayOfMonth(1).toDate();
		} else {
			return datetime.withMonthOfYear(10).withDayOfMonth(1).toDate();
		}
	}

	/**
	 * 取得季度的结束日期
	 * 
	 * @param date
	 * @return
	 */
	public static Date getLastDayOfQuarter(Date date) {
		DateTime datetime = new DateTime(date);
		int month = datetime.getMonthOfYear();
		if (month >= 1 && month <= 3) {
			return datetime.withMonthOfYear(3).withDayOfMonth(31).toDate();
		} else if (month >= 4 && month <= 6) {
			return datetime.withMonthOfYear(6).withDayOfMonth(30).toDate();
		} else if (month >= 7 && month <= 9) {
			return datetime.withMonthOfYear(9).withDayOfMonth(30).toDate();
		} else {
			return datetime.withMonthOfYear(12).withDayOfMonth(31).toDate();
		}
	}

	/**
	 * 取得下一季度的开始日期
	 * 
	 * @param date
	 * @return
	 */
	public static Date getFirstDayOfNextQuarter(Date date) {
		DateTime datetime = new DateTime(date);
		int month = datetime.getMonthOfYear();
		if (month >= 1 && month <= 3) {
			return datetime.withMonthOfYear(4).withDayOfMonth(1).toDate();
		} else if (month >= 4 && month <= 6) {
			return datetime.withMonthOfYear(7).withDayOfMonth(1).toDate();
		} else if (month >= 7 && month <= 9) {
			return datetime.withMonthOfYear(10).withDayOfMonth(1).toDate();
		} else {
			return datetime.withMonthOfYear(1).withDayOfMonth(1).toDate();
		}
	}

	/**
	 * 取得下一季度的结束日期
	 * 
	 * @param date
	 * @return
	 */
	public static Date getLastDayOfNextQuarter(Date date) {
		DateTime datetime = new DateTime(date);
		int month = datetime.getMonthOfYear();
		if (month >= 1 && month <= 3) {
			return datetime.withMonthOfYear(6).withDayOfMonth(30).toDate();
		} else if (month >= 4 && month <= 6) {
			return datetime.withMonthOfYear(9).withDayOfMonth(30).toDate();
		} else if (month >= 7 && month <= 9) {
			return datetime.withMonthOfYear(12).withDayOfMonth(31).toDate();
		} else {
			return datetime.withMonthOfYear(3).withDayOfMonth(31).toDate();
		}
	}

	/**
	 * 获取某个日期之后的首个周末（包含当前日期）
	 * 
	 * @param date
	 * @return
	 */
	public static Date getHolidayOfDate(Date date) {
		Calendar calDate = new GregorianCalendar();
		calDate.setTime(date);
		while (!(calDate.get(Calendar.DAY_OF_WEEK) == 7 || calDate
				.get(Calendar.DAY_OF_WEEK) == 1)) {
			calDate.set(Calendar.DAY_OF_YEAR,
					calDate.get(Calendar.DAY_OF_YEAR) + 1);
		}
		return parseDateWebFormat(DateFormatUtils.format(calDate.getTime(),
				webFormat));
	}

	/**
	 * 判断是否周末
	 * 
	 * @param date
	 * @return
	 */
	public static boolean checkIsHoliday(Date date) {
		boolean rs = false;
		Calendar calDate = new GregorianCalendar();
		calDate.setTime(date);
		if (calDate.get(Calendar.DAY_OF_WEEK) == 7
				|| calDate.get(Calendar.DAY_OF_WEEK) == 1) {
			rs = true;
		}

		return rs;
	}

	/**
	 * 获取下周某一天日期
	 * 
	 * @param date
	 * @param cycle
	 * @return
	 */
	public static Date getNextWeekDay(Date date, int cycle) {
		Calendar calDate = DateUtils.toCalendar(date);
		calDate.add(Calendar.WEEK_OF_MONTH, 1);
		calDate.set(Calendar.DAY_OF_WEEK, cycle);
		return calDate.getTime();
	}

	/**
	 * 获取下月某一天日期
	 * 
	 * @param date
	 * @param cycle
	 * @return
	 */
	public static Date getMonthDay(Date date, int cycle) {
		Calendar calDate = DateUtils.toCalendar(date);
		calDate.add(Calendar.MONTH, 1);
		calDate.set(Calendar.DAY_OF_MONTH, cycle);
		return calDate.getTime();
	}

	public static void main(String[] args) {
		Date date = DateUtil.getFirstDayOfYear(new Date(), -1);
		System.out.println(DateUtil.formatDateTime(date));
		date = DateUtil.getLastDayOfYear(new Date(), -10);
		System.out.println(DateUtil.formatDateTime(date));
	}
}
