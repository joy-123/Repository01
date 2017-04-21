package com.joy.utils.string;

/**
 * 对于字符串的一些处理和判断  对字符串的一些规则
 *（uiil工具包 经常补充内容，保存好，需要用就拿出来）
 * @author Administrator
 *2017年1月3日下午7:09:58
 */
public class StringUtils {
 
	/**
	 * 判断字符串是否为空
	 * @param str
	 * @return
	 */
	public static boolean isNull(String str) {
		return null == str || str.length() == 0 || "".equals(str)
				|| str.matches("\\s*");// str.matches("\\s*")正则表达式，空格
	}
	
	/**
	 * 非空判断
	 */
	public static boolean isNotNull(String str) {
		return !isNull(str);
	}
	
}

