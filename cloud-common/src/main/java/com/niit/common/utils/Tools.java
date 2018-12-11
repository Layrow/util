package com.niit.common.utils;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Tools {
	
	/**
	 * 随机生成六位数验证码 
	 * @return
	 */
	public static int getRandomNum(){
		 Random r = new Random();
		 return r.nextInt(900000)+100000;//(Math.random()*(999999-100000)+100000)
	}
	
	/**
	 * 检测字符串是否不为空(null,"","null")
	 * @param s
	 * @return 不为空则返回true，否则返回false
	 */
	public static boolean notEmpty(String s){
		return s!=null && !"".equals(s) && !"null".equals(s);
	}
	
	/**
	 * 检测字符串是否为空(null,"","null")
	 * @param s
	 * @return 为空则返回true，不否则返回false
	 */
	public static boolean isEmpty(String s){
		return s==null || "".equals(s) || "null".equals(s);
	}
	
	/**
	 * 字符串转换为字符串数组
	 * @param str 字符串
	 * @param splitRegex 分隔符
	 * @return
	 */
	public static String[] str2StrArray(String str,String splitRegex){
		if(isEmpty(str)){
			return null;
		}
		return str.split(splitRegex);
	}
	
	/**
	 * 用默认的分隔符(,)将字符串转换为字符串数组
	 * @param str	字符串
	 * @return
	 */
	public static String[] str2StrArray(String str){
		return str2StrArray(str,",\\s*");
	}
	
	/**
	 * 按照yyyy-MM-dd HH:mm:ss的格式，日期转字符串
	 * @param date
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String date2Str(Date date){
		return date2Str(date,"yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * 按照yyyy-MM-dd HH:mm:ss的格式，字符串转日期
	 * @param date
	 * @return
	 */
	public static Date str2Date(String date){
		if(notEmpty(date)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				return sdf.parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return new Date();
		}else{
			return null;
		}
	}
	
	/**
	 * 按照参数format的格式，日期转字符串
	 * @param date
	 * @param format
	 * @return
	 */
	public static String date2Str(Date date,String format){
		if(date!=null){
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.format(date);
		}else{
			return "";
		}
	}
	
	/**
	 * 把时间根据时、分、秒转换为时间段
	 * @param StrDate
	 */
	public static String getTimes(String StrDate){
		String resultTimes = "";
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    Date now;
	    
	    try {
	    	now = new Date();
	    	Date date=df.parse(StrDate);
	    	long times = now.getTime()-date.getTime();
	    	long day  =  times/(24*60*60*1000);
	    	long hour = (times/(60*60*1000)-day*24);
	    	long min  = ((times/(60*1000))-day*24*60-hour*60);
	    	long sec  = (times/1000-day*24*60*60-hour*60*60-min*60);
	        
	    	StringBuffer sb = new StringBuffer();
	    	//sb.append("发表于：");
	    	if(hour>0 ){
	    		sb.append(hour+"小时前");
	    	} else if(min>0){
	    		sb.append(min+"分钟前");
	    	} else{
	    		sb.append(sec+"秒前");
	    	}
	    		
	    	resultTimes = sb.toString();
	    } catch (ParseException e) {
	    	e.printStackTrace();
	    }
	    
	    return resultTimes;
	}

	
	/*
	 * 字符串转整形
	 */
	public static int parseInt(String value) {
		if (Tools.isEmpty(value.trim())) {
			return 0;
		}else{
			try {
				return Integer.parseInt(value.trim());
			} catch (Exception e) {
				return 0;
			}
		}
			
	}
	/**
	 * mango db 中 时间类型格式化
	 * @param sdate
	 * @return
	 */
	public static String parseTime(String sdate){
		if(sdate==null||sdate.equals("")){
			return "";
		}
		DateFormat dateFormat = new SimpleDateFormat("EEE MMM d HH:mm:ss z yyyy",Locale.UK);
		Date date = null;
		try {
			date = dateFormat.parse(sdate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");  
        String sDate=sdf.format(date); 
		return sDate;
	}

	/**
	 * 返回选中的ID
	 * @param request
	 * @return
	 */
	public static List<String> getIDList(HttpServletRequest request) {
		List<String>list=new ArrayList();
		String[] str =  Tools.str2StrArray(request.getParameter("id"));
		for (int i = 0; i < str.length; i++) {
			list.add(str[i]);
		}
		return list;
	}

	/**
	 * 将字符串数组转换成,list
	 */
	public static List<String> getList(String ids){
		List<String> list = new ArrayList<String>();
		String[] str = ids.split(",");
		for (int i = 0; i < str.length; i++) {
			list.add(str[i]);
		}
		return list;
	}

	public static Object getMapper(String locale,Object zhObj, Object enObj) {
		Map<String, Object> mapperMap = new LinkedHashMap<>();
		mapperMap.put("zh", zhObj);
		mapperMap.put("en", enObj);
		Object mapper = mapperMap.get(locale);
		return (mapper != null) ? mapper : mapperMap.get("zh");
	}

}
