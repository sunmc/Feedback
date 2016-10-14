package com.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	/**
	 * 当前时间的年月日字符串
	 * 
	 * @return yyyy-MM-dd
	 */
	public static String nowYMD() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String s = sdf.format(new Date());
		return s;
	}

	/**
	 * 当前时间的格式 字符串
	 * 
	 * @return yyyy-MM-dd hh:mm:ss
	 */
	public static String nowYMDT() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String s = sdf.format(new Date());
		return s;
	}

	/**
	 * 当前时间字符串
	 * 
	 * @return sdfsdf
	 */
	public static String nowLong() {
		return new Date().getTime() + "";
	}

	/**
	 * 指定时间的年月日格式 字符串
	 * 
	 * @return yyyy-MM-dd
	 */
	public static String dateYMD(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String s = sdf.format(date);
		return s;
	}

	/**
	 * 当前时间的格式 字符串
	 * 
	 * @return yyyy-MM-dd hh:mm:ss
	 */
	public static String dateYMDT(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String s = sdf.format(date);
		return s;
	}

	/**
	 * 获取年龄
	 * @param birthDate
	 * @return
	 */
	public static int getAge(Date birthDate) {

		if (birthDate == null)
			return 0;

		int age = 0;

		Date now = new Date();

		SimpleDateFormat format_y = new SimpleDateFormat("yyyy");
		SimpleDateFormat format_M = new SimpleDateFormat("MM");

		String birth_year = format_y.format(birthDate);
		String this_year = format_y.format(now);

		String birth_month = format_M.format(birthDate);
		String this_month = format_M.format(now);

		// 初步，估算
		age = Integer.parseInt(this_year) - Integer.parseInt(birth_year);

		// 如果未到出生月份，则age - 1
		if (this_month.compareTo(birth_month) < 0)
			age -= 1;
		if (age < 0)
			age = 0;
		return age;
	}
}
