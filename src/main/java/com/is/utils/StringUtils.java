package com.is.utils;

import java.io.UnsupportedEncodingException;

/**
 * @ClassName: StringUtil
 * @Description: 字符串处理工具类
 * @author 
 * @date 2010-11-05
 * 
 */
public class StringUtils {

	/**
	 * 判断字符串是否为空,Empty定义：<br>
	 * 1、null<br>
	 * 2、"",长度为零的字符串<br>
	 * 3、全部由空格组成的字符串<br>
	 * 
	 * @param str
	 * @return 如果字符串为Empty则返回true，否则返回false
	 */
	public static boolean isEmpty(String str) {
		if (str == null || "".equals(str.trim())) {
			return true;
		}
		return false;
	}

	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	/**
	 * StringUtils.leftPad(null, *, *) = null StringUtils.leftPad("", 3, 'z') =
	 * "zzz" StringUtils.leftPad("bat", 3, 'z') = "bat"
	 * StringUtils.leftPad("bat", 5, 'z') = "zzbat" StringUtils.leftPad("bat",
	 * 1, 'z') = "bat" StringUtils.leftPad("bat", -1, 'z') = "bat"
	 * 
	 * @param str
	 *            the String to pad out, may be null
	 * @param size
	 *            the size to pad to
	 * @param padChar
	 *            the character to pad with
	 * @return left padded String or original String if no padding is necessary,
	 */
	public static String leftPad(String str, int size, char padChar) {
		return org.apache.commons.lang.StringUtils.leftPad(str, size, padChar);
	}

	/**
	 * 该方法计算字符串(包括中文)的实际长度.
	 * 
	 * @param str
	 *            需要计算长度的字符串
	 * @return int 字符串的实际长度
	 */
	public static int length(String str) {
		if (str == null) {
			return 0;
		}
		try {
			return new String(str.getBytes("GBK"), "8859_1").length();
		} catch (UnsupportedEncodingException e) {
			return -1;
		}
	}
	/**
	 * 判断字符串是否为数字。
	 * 
	 * @param str
	 *             
	 * @return boolean  数字返回true
	 */
	public static boolean isNum(String str) {
		return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
	}
}
