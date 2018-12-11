package com.niit.common.utils;

public class NumberUtil {

	public static final int ONE_TOP=10;
	public static final int TWO_TOP=100;

	public static String numberToString(int number){
		String[] descArray = new String[]{"十", "百", "千", "万", "十万", "百万", "千万"};
	    int length = String.valueOf(number).length();
		return descArray[length];
	}
	/**
	 * 生成三个数字
	 * @param number
	 * @return
	 */
	public static String  numFormat(Integer number){
		if (number<ONE_TOP){
			return "00"+String.valueOf(number);
		}else if (number<TWO_TOP){
			return "0"+String.valueOf(number);
		}else {
			return String.valueOf(number);
		}

	}
}
