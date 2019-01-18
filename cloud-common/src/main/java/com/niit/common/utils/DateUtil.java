package com.niit.common.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;


public class DateUtil {
    private final static SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");

    private final static SimpleDateFormat sdfDay = new SimpleDateFormat(
            "yyyy-MM-dd");

    private final static SimpleDateFormat sdfDays = new SimpleDateFormat(
            "yyyyMMdd");

    private final static SimpleDateFormat sdfTime = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");

    /**
     * 获取YYYY格式
     *
     * @return
     */
    public static String getYear() {
        return sdfYear.format(new Date());
    }

    /**
     * 获取YYYY-MM-DD格式
     *
     * @return
     */
    public static String getDay() {
        return sdfDay.format(new Date());
    }

    /**
     * 获取YYYYMMDD格式
     *
     * @return
     */
    public static String getDays() {
        return sdfDays.format(new Date());
    }

    /**
     * 获取YYYY-MM-DD HH:mm:ss格式
     *
     * @return
     */
    public static String getTime() {
        return sdfTime.format(new Date());
    }

    /**
     * @param s
     * @param e
     * @return boolean
     * @throws
     * @Title: compareDate
     * @Description: TODO(日期比较 ， 如果s > = e 返回true 否则返回false)
     * @author luguosui
     */
    public static boolean compareDate(String s, String e) {
        if (fomatDate(s) == null || fomatDate(e) == null) {
            return false;
        }
        return fomatDate(s).getTime() >= fomatDate(e).getTime();
    }

    /**
     * 格式化日期
     *
     * @return
     */
    public static Date fomatDate(String date) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return fmt.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 校验日期是否合法
     *
     * @return
     */
    public static boolean isValidDate(String s) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            fmt.parse(s);
            return true;
        } catch (Exception e) {
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            return false;
        }
    }

    public static int getDiffYear(String startTime, String endTime) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            long aa = 0;
            int years = (int) (((fmt.parse(endTime).getTime() - fmt.parse(startTime).getTime()) / (1000 * 60 * 60 * 24)) / 365);
            return years;
        } catch (Exception e) {
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            return 0;
        }
    }

    /**
     * <li>功能描述：时间相减得到天数
     *
     * @param beginDateStr
     * @param endDateStr
     * @return long
     * @author Administrator
     */
    public static long getDaySub(String beginDateStr, String endDateStr) {
        long day = 0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate = null;
        Date endDate = null;

        try {
            beginDate = format.parse(beginDateStr);
            endDate = format.parse(endDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        day = (endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000);
        //System.out.println("相隔的天数="+day);

        return day;
    }

    /**
     * 得到n天之后的日期
     *
     * @param days
     * @return
     */
    public static String getAfterDayDate(String days) {
        int daysInt = Integer.parseInt(days);

        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();

        SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdfd.format(date);

        return dateStr;
    }

    /**
     * 得到n天之后是周几
     *
     * @param days
     * @return
     */
    public static String getAfterDayWeek(String days) {
        int daysInt = Integer.parseInt(days);

        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();

        SimpleDateFormat sdf = new SimpleDateFormat("E");
        String dateStr = sdf.format(date);

        return dateStr;
    }

    /**
     * 指定日期架上指定时间
     *
     * @param date day
     * @return
     */
    public static Date addDate(Date date, long day) throws ParseException {
        long time = date.getTime(); // 得到指定日期的毫秒数
        day = day * 24 * 60 * 60 * 1000; // 要加上的天数转换成毫秒数
        time += day; // 相加得到新的毫秒数
        return new Date(time); // 将毫秒数转换成日期
    }


    public static final String PATTERN_STANDARD = "yyyy-MM-dd HH:mm:ss";

    public static final String PATTERN_DATE = "yyyy-MM-dd";

    public static String timestamp2String(Timestamp timestamp, String pattern) {
        if (timestamp == null) {
            throw new IllegalArgumentException("timestamp null illegal");
        }
        if (pattern == null || pattern.equals("")) {
            pattern = PATTERN_STANDARD;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(new Date(timestamp.getTime()));
    }

    public static String date2String(Date date, String pattern) {
        if (date == null) {
            throw new IllegalArgumentException("timestamp null illegal");
        }
        if (pattern == null || pattern.equals("")) {
            pattern = PATTERN_STANDARD;
            ;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    public static Timestamp currentTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    public static Long currentTime() {
        return System.currentTimeMillis();
    }

    public static String currentTimestamp2String(String pattern) {
        return timestamp2String(currentTimestamp(), pattern);
    }

    public static Timestamp string2Timestamp(String strDateTime, String pattern) {
        if (strDateTime == null || strDateTime.equals("")) {
            throw new IllegalArgumentException("Date Time Null Illegal");
        }
        if (pattern == null || pattern.equals("")) {
            pattern = PATTERN_STANDARD;
        }

        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = sdf.parse(strDateTime);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return new Timestamp(date.getTime());
    }

    public static Date string2Date(String strDate, String pattern) {
        if (strDate == null || strDate.equals("")) {
            throw new RuntimeException("str date null");
        }
        if (pattern == null || pattern.equals("")) {
            pattern = DateUtil.PATTERN_DATE;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date date = null;

        try {
            date = sdf.parse(strDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return date;
    }

    public static String stringToYear(String strDest) {
        if (strDest == null || strDest.equals("")) {
            throw new IllegalArgumentException("str dest null");
        }

        Date date = string2Date(strDest, DateUtil.PATTERN_DATE);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return String.valueOf(c.get(Calendar.YEAR));
    }

    public static String stringToMonth(String strDest) {
        if (strDest == null || strDest.equals("")) {
            throw new IllegalArgumentException("str dest null");
        }

        Date date = string2Date(strDest, DateUtil.PATTERN_DATE);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        // return String.valueOf(c.get(Calendar.MONTH));
        int month = c.get(Calendar.MONTH);
        month = month + 1;
        if (month < 10) {
            return "0" + month;
        }
        return String.valueOf(month);
    }

    public static String stringToDay(String strDest) {
        if (strDest == null || strDest.equals("")) {
            throw new IllegalArgumentException("str dest null");
        }

        Date date = string2Date(strDest, DateUtil.PATTERN_DATE);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        // return String.valueOf(c.get(Calendar.DAY_OF_MONTH));
        int day = c.get(Calendar.DAY_OF_MONTH);
        if (day < 10) {
            return "0" + day;
        }
        return "" + day;
    }

    public static Date getFirstDayOfMonth(Calendar c) {
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = 1;
        c.set(year, month, day, 0, 0, 0);
        return c.getTime();
    }

    public static Date getLastDayOfMonth(Calendar c) {
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int day = 1;
        if (month > 11) {
            month = 0;
            year = year + 1;
        }
        c.set(year, month, day - 1, 0, 0, 0);
        return c.getTime();
    }

    public static String date2GregorianCalendarString(Date date) {
        if (date == null) {
            throw new IllegalArgumentException("Date is null");
        }
        long tmp = date.getTime();
        GregorianCalendar ca = new GregorianCalendar();
        ca.setTimeInMillis(tmp);
        try {
            XMLGregorianCalendar t_XMLGregorianCalendar = DatatypeFactory
                    .newInstance().newXMLGregorianCalendar(ca);
            return t_XMLGregorianCalendar.normalize().toString();
        } catch (DatatypeConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new IllegalArgumentException("Date is null");
        }

    }

    public static boolean compareDate(Date firstDate, Date secondDate) {
        if (firstDate == null || secondDate == null) {
            throw new RuntimeException();
        }

        String strFirstDate = date2String(firstDate, "yyyy-MM-dd");
        String strSecondDate = date2String(secondDate, "yyyy-MM-dd");
        if (strFirstDate.equals(strSecondDate)) {
            return true;
        }
        return false;
    }

    public static Date getTruncDate(Date date) {
        String strDateTime = date2String(date, "yyyy-MM-dd");
        return string2Date(strDateTime, "yyyy-MM-dd");
    }

    public static Date getStartTimeOfDate(Date currentDate) {
        String strDateTime = date2String(currentDate, "yyyy-MM-dd")
                + " 00:00:00";
        return string2Date(strDateTime, "yyyy-MM-dd hh:mm:ss");
    }

    public static Date getEndTimeOfDate(Date currentDate) {
        String strDateTime = date2String(currentDate, "yyyy-MM-dd")
                + " 59:59:59";
        return string2Date(strDateTime, "yyyy-MM-dd hh:mm:ss");
    }

    /**
     * 判断某个时间是不是股票交易时间 9:30-11:30 13:00-15：00
     *
     * @param paramDate
     * @return
     */
    public static Boolean isBusinessTime(Date paramDate) {
        Calendar cl1 = Calendar.getInstance();
        cl1.setTime(paramDate);
        cl1.set(Calendar.SECOND, 00);
        cl1.set(Calendar.MINUTE, 30);
        cl1.set(Calendar.HOUR_OF_DAY, 9);

        Calendar cl2 = Calendar.getInstance();
        cl2.setTime(paramDate);
        cl2.set(Calendar.SECOND, 00);
        cl2.set(Calendar.MINUTE, 30);
        cl2.set(Calendar.HOUR_OF_DAY, 11);

        Calendar cl3 = Calendar.getInstance();
        cl3.setTime(paramDate);
        cl3.set(Calendar.SECOND, 00);
        cl3.set(Calendar.MINUTE, 00);
        cl3.set(Calendar.HOUR_OF_DAY, 13);

        Calendar cl4 = Calendar.getInstance();
        cl4.setTime(paramDate);
        cl4.set(Calendar.SECOND, 00);
        cl4.set(Calendar.MINUTE, 00);
        cl4.set(Calendar.HOUR_OF_DAY, 15);

        if (paramDate.getTime() < cl1.getTimeInMillis()) {
            return false;
        }
        if (paramDate.getTime() > cl1.getTimeInMillis()
                && paramDate.getTime() < cl2.getTimeInMillis()) {
            return true;
        }
        if (paramDate.getTime() > cl2.getTimeInMillis()
                && paramDate.getTime() < cl3.getTimeInMillis()) {
            return false;
        }
        if (paramDate.getTime() > cl3.getTimeInMillis()
                && paramDate.getTime() < cl4.getTimeInMillis()) {
            return true;
        }
        if (paramDate.getTime() > cl4.getTimeInMillis()) {
            return false;
        }
        return false;
    }

    /**
     * 判断是不是今天
     *
     * @param date
     * @return
     */
    public static Boolean isToDay(Date date) {
        date = getStartTimeOfDate(date);
        Date toDay = new Date();
        toDay = getStartTimeOfDate(toDay);
        if (!date.equals(toDay)) {
            return false;
        }
        return true;
    }

    /**
     * 判断是不是本月
     *
     * @param date
     * @return
     */
    public static Boolean isMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        Calendar c1 = Calendar.getInstance();
        c1.setTime(new Date());
        int year1 = c1.get(Calendar.YEAR);
        int month1 = c1.get(Calendar.MONTH) + 1;
        if (year == year1 && month == month1) {
            return true;
        }
        return false;
    }

    /**
     * 判断是不是本周
     *
     * @param date
     * @return
     */
    public static Boolean isWeek(Date date) {
        Map<String, String> map = new HashMap<String, String>();
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        String monDay = date2String(cal.getTime(), "yyyy-MM-dd") + " 00:00:00";
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        cal.add(Calendar.WEEK_OF_YEAR, 1);
        String sunday = date2String(cal.getTime(), "yyyy-MM-dd") + " 00:00:00";
        if (DateUtil.string2Date(monDay, PATTERN_STANDARD).getTime() <= date
                .getTime()
                && DateUtil.string2Date(sunday + " 00.00.00", PATTERN_STANDARD)
                .getTime() >= date.getTime()) {
            return true;
        }
        return false;
    }

    /**
     * 获取明天日期
     *
     * @param date
     * @return
     */
    public static Date tomorrow(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE, 1);
        Date dateTime = calendar.getTime();
        String startTimes = DateUtil.date2String(dateTime,
                DateUtil.PATTERN_DATE);
        return DateUtil.string2Date(startTimes, DateUtil.PATTERN_DATE);
    }

    public static Boolean isConduct(Date attendStart, Date attendOver,
                                    Date gameStart, Date gameOver) {
        attendStart = DateUtil.getStartTimeOfDate(attendStart);
        attendOver = DateUtil.getStartTimeOfDate(attendOver);
        gameStart = DateUtil.getStartTimeOfDate(gameStart);
        gameOver = DateUtil.getStartTimeOfDate(gameOver);
        if (gameStart.getTime() > gameOver.getTime()) {
            return false;
        }
        if (attendStart.getTime() > attendOver.getTime()) {
            return false;
        }
        if (attendStart.getTime() > gameStart.getTime()) {
            return false;
        }
        return true;
    }

    public static String formatDateBySimple(Date myDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssS");
        String strDate = formatter.format(myDate);
        return strDate;
    }

    // 二个时间相隔的天数+小时+分钟
    public static String getDatePoor(Date endDate, Date nowDate) {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        // long nm = 1000 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        // long min = diff % nd % nh / nm;
        // long sec = diff % nd % nh % nm / ns;
        // return day + "天" + hour + "小时" + min + "分钟";
        return day + "天" + hour + "小时";
    }


    public static String formatDateBySimpleNoHour(Date myDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String strDate = formatter.format(myDate);
        return strDate;
    }


    public static int daysBetween(Date smdate, Date bdate)
            throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        smdate = sdf.parse(sdf.format(smdate));
        bdate = sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);
        return Integer.parseInt(String.valueOf(between_days));

    }


}
