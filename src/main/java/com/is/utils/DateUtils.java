package com.is.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 
 * @ClassName: DateUtil
 * @Description: 日期处理工具类
 * @author 
 * @date 2010-11-9 下午03:03:54
 * 
 */
public class DateUtils
{

    /**
     * 默认的日期格式
     */
    private final static String defaultDateFormat     = "yyyy-MM-dd";

    /**
     * 默认的时间格式
     */
    private final static String defaultDateTimeFormat = "yyyy-MM-dd HH:mm:ss";

    /** 带毫秒的日期字符串 */
    private final static String PATTERN_MIL           = "yyyyMMddHHmmssSSS";

    /** 带毫秒的日期字符串 */
    private final static String PATTERN_DAY           = "yyyyMMdd";

    /**
     * 获取服务器当前时间，包含毫秒(3位) 如：201104141446451875
     * 
     * @return
     */
    public static String getCurDateTimeMil()
    {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_MIL);
        return sdf.format(date);
    }

    /**
     * 获取服务器当前时间，包含毫秒(3位) 如：201104141446451875
     * 
     * @return
     */
    public static String getCurDateDayString()
    {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_DAY);
        return sdf.format(date);
    }

    /**
     * 日期单位:年
     */
    public final static int DATE_UNIT_YEAR  = 0;

    /**
     * 日期单位:月
     */
    public final static int DATE_UNIT_MONTH = 1;

    /**
     * 日期单位:日
     */
    public final static int DATE_UNIT_DAY   = 2;

    /**
     * 获取日期类的字符串形式(yyyy-mm-dd)
     * 
     * @param date
     * @return
     */
    public final static String dateToString(Date date)
    {
        if (date == null)
        {
            return null;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(defaultDateFormat);
        return dateFormat.format(date);
    }

    /**
     * 将字符串日期（格式：yyyy-mm-dd）转换为Date类型
     * 
     * @param dateStr
     * @return
     */
    public final static Date stringToDate(String dateStr)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat(defaultDateFormat);
        Date date = null;
        try
        {
            date = dateFormat.parse(formatDate(dateStr));
        }
        catch (ParseException e)
        {
            throw new IllegalArgumentException("日期[" + dateStr + "]转换失败!", e);
        }
        return date;
    }

    /**
     * 将字符串日期（格式：自定义格式如yyyy年mm月dd HH:mm:ss）转换为Date类型
     * @param dateStr
     * @param format 自定义格式如yyyy年mm月dd HH:mm:ss
     * @return
     */
    public final static Date stringToDate(String dateStr, String format)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        Date date = null;
        try
        {
            date = dateFormat.parse(dateStr);
        }
        catch (ParseException e)
        {
            throw new IllegalArgumentException("日期[" + dateStr + "]转换失败!", e);
        }
        return date;
    }

    /**
     * 获取日期类的字符串形式(yyyy-MM-dd )
     * 
     * @param date
     * @return 
     */
    public final static String dateTimeToString(Date date)
    {
        if (date == null)
        {
            return null;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(defaultDateTimeFormat);
        return dateFormat.format(date);
    }

    /**
     * 获取日期类的字符串形式(格式：自定义格式如yyyy年mm月dd HH:mm:ss)
     * @param date
     * @param format 格式：自定义格式如yyyy年mm月dd HH:mm:ss
     * @return
     */
    public final static String dateTimeToString(Date date, String format)
    {
        if (date == null)
        {
            return null;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }

    /**
     * 计算两日期间隔(endDate-startDate)
     * 
     * @param unit
     *            日期间隔单位:年,月,日 参考常量定义 @see {@link DateUtils#DATE_UNIT_YEAR};
     *            {@link DateUtils#DATE_UNIT_YEAR};
     *            {@link DateUtils#DATE_UNIT_YEAR}
     * @param startDate
     *            起始时间
     * @param endDate
     *            结束时间
     * @return
     */
    public final static int datediff(int unit, String startDate, String endDate)
    {
        if (unit == DateUtils.DATE_UNIT_YEAR)
        {

        }
        else if (unit == DateUtils.DATE_UNIT_MONTH)
        {

        }
        else if (unit == DateUtils.DATE_UNIT_DAY)
        {

        }
        return 0;
    }

    /**
     * 对给定的日期格式化,格式化后的日期格式为:yyyy-mm-dd
     * 
     * @param dateStr
     *            格式如2007.01.01、2007.1.1或2007/01/01、2007/1/1
     * @return
     */
    public final static String formatDate(String dateStr)
    {
        dateStr = dateStr.replace('.', '-').replace('/', '-');
        int firstIndex , lastIndex;
        firstIndex = dateStr.indexOf("-");
        lastIndex = dateStr.lastIndexOf("-");
        if (firstIndex != 4 || lastIndex < firstIndex)
        {
            throw new IllegalArgumentException("不支持此日期值[" + dateStr + "],日期格式化失败，!");
        }
        String year = dateStr.substring(0, firstIndex);
        String month = dateStr.substring(firstIndex + 1, lastIndex);
        String day = dateStr.substring(lastIndex + 1);
        return year + "-" + (month.length() < 2 ? ("0" + month) : month) + "-" + (day.length() < 2 ? ("0" + day) : day);
    }

    /**
     * 获取指定日期n天后的日期
     * 
     * @param dateStr
     * @param n
     * @return
     */
    public final static String nDaysAfter(String dateStr, int n)
    {
        long addTime = 24 * 60 * 60 * 1000 * (long) n;
        Date date = stringToDate(dateStr);
        return dateToString(new Date(date.getTime() + addTime));
    }

    /**
     * 获取指定日期n天前的日期
     * 
     * @param dateStr
     * @param n
     * @return
     */
    public final static String nDaysBefore(String dateStr, int n)
    {
        long minusTime = 24 * 60 * 60 * 1000 * (long) n;
        Date date = stringToDate(dateStr);
        return dateToString(new Date(date.getTime() - minusTime));
    }

    /**
     * 判断给定的日期是否为周末(周六或周日)
     * 
     * @param dateStr
     *            String
     * @return boolean
     * @throws DataFormateException
     */
    public final static boolean isWeekend(String str)
    {
        boolean flag = false;
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(stringToDate(str));
        int dayOfWeek = calendar.get(GregorianCalendar.DAY_OF_WEEK);
        if (dayOfWeek == 1 || dayOfWeek == 7)
        {
            flag = true;
        }
        return flag;
    }

    /**
     * 获得给定日期为一周的第几天，例如星期一、星期二等
     * 
     * @param str
     * @return
     * @throws DataFormateException
     */
    public final static String getDayOfWeekName(String str)
    {
        String dayNames[] = {"星期日", "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(stringToDate(str));
        int dayOfWeek = calendar.get(GregorianCalendar.DAY_OF_WEEK);
        return dayNames[dayOfWeek];
    }
    /**
     * 把月日的时间字符串转成当前年份的月日 时间
     * @param mmdd  "06月21日"
     * @return date
     */
    public final static Date newFullDate(String mmdd){
        SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日");
        Calendar calendar=Calendar.getInstance();
        try
        {
            Date date = sdf.parse(mmdd);
            Date now=new Date();
            Calendar calendarnow=Calendar.getInstance();
            calendarnow.setTime(now);
            calendar.setTime(date);
            calendar.set(Calendar.YEAR, calendarnow.get(Calendar.YEAR));

        }
        catch (ParseException e)
        {
        }
        return calendar.getTime();
    }
    /**
     * 把当前时间转换成yyyy-MM-dd 格式的时间
     * @return date
     */
    public final static Date newymdDate(){
        String strDate = DateUtils.dateTimeToString(new Date(),"yyyy-MM-dd");
        return  DateUtils.stringToDate(strDate);
    }
}
