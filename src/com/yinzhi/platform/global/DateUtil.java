package com.yinzhi.platform.global;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	/**
	 * 获取当前日日期返回
	 */
	public static String getDay() {
		SimpleDateFormat formatter;
		formatter = new SimpleDateFormat("d");
		String day = formatter.format(new Date());
		return day;
	}

	/**
	 * 获取月份
	 */
	public static String getMonth() {
		SimpleDateFormat formatter;
		formatter = new SimpleDateFormat("M");
		String month = formatter.format(new Date());
		return month;
	}

	/**
	 * 获取年
	 */
	public static String getYear() {
		SimpleDateFormat formatter;
		formatter = new SimpleDateFormat("yyyy");
		String year = formatter.format(new Date());
		return year;
	}

	/**
	 * 将java.util.Date 格式转换为字符串格式'yyyy-MM-dd HH:mm:ss a'(12小时制) 如Sat May 11
	 * 17:23:22 CST 2002 to '2002-05-11 05:23:22 下午'
	 * 
	 * @param time
	 *            Date 日期
	 * @return String 字符串 默认yyyy-MM-dd HH:mm:ss
	 */
	public static String dateToString(Date time, String format) {
		String f = "yyyy-MM-dd HH:mm:ss";
		if (format != null) {
			f = format;
		}
		SimpleDateFormat formatter;
		formatter = new SimpleDateFormat(f);
		String Time = formatter.format(time);
		return Time;
	}

	/**
	 * 取系统当前时间:返回只值为如下形式 2002-10-30 20:24:39
	 * 
	 * @return String
	 */
	public static String getTime() {
		return dateToString(new Date(), null);
	}

	/**
	 * 取系统当前日期:返回只值为如下形式 2002-10-30
	 * 
	 * @return String
	 */
	public static String getDate() {
		return dateToString(new Date(), "yyyy-MM-dd");
	}
	
	/**
	 * 字符串转为日期
	 * @param date
	 * @param format 默认yyyy-MM-dd
	 * @return
	 */
	public static Date stringToDate(String date, String format){
		if(format == null){
			format = "yyyy-MM-dd";
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			return null;
		}
	}
	
	/**
	 * 传入日期值加减去天数后的日期
	 * @param date
	 * @param day
	 * @return
	 */
	public static Date getDate(Date date, Integer day){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, day);
		return calendar.getTime();
	}

	public static void main(String[] args) {
		System.out.println(getDate());
	}
}