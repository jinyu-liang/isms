package com.is.utils.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 关于日期的一些常用操作
 *
 * @author 
 *
 */

public class DateUtil
{
    private static final String PATTERN_MIL      = "yyyyMMddHHmmssSSS";

    private static final String PATTERN          = "yyyyMMddHHmmss";

    private static final String PATTERN_YYYYMMDD = "yyyyMMdd";

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
     * 获取服务器当前时间，yyyyMMddHHmmss
     *
     * @return
     */
    public static String getCurDateTime()
    {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(PATTERN);
        return sdf.format(date);
    }

    /**
     * 获取服务器当前时间，yyyyMMddHHmmss
     *
     * @return
     * @throws ParseException 
     */
    public static Date getCurDate() throws ParseException
    {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(PATTERN);
        return sdf.parse(sdf.format(date));
    }
    /**
     * 获取服务器当前时间，yyyyMMddHHmmss
     *
     * @return
     */
    public static String getCurDateByyyyymmdd()
    {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_YYYYMMDD);
        return sdf.format(date);
    }

    /**
     * 获取一个时间的yyyyMMddHHmmss形式的字符串
     *
     * @param date
     * @return
     */
    public static String getDateString(Date date)
    {
        if (date == null)
        {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_YYYYMMDD);
        return sdf.format(date);
    }

    /**
     * 计算两个日期相差的时间间隔秒数，无差别或结束时间小于开始时间返回空字符串， 参数任何一个为空，返回空字符串
     *
     * @param dateAfter
     * @param dateBefore
     * @return
     * @author 
     */
    public static String diffFromTwoDate(Date dateAfter, Date dateBefore)
    {
        if (dateAfter == null || dateBefore == null)
        {
            return "";
        }
        if (dateAfter.before(dateBefore))
        {
            return "";
        }
        long diff = (dateAfter.getTime() - dateBefore.getTime()) / 1000;
        return String.valueOf(diff);
    }

    /**
     * 根据秒得到 *天*时*分*秒格式的字符串
     *
     * @param secondsStr
     * @return
     */
    public static String formatSecondsFromString(String secondsStr)
    {
        String returnStr = "";
        long diff;
        try
        {
            diff = Long.parseLong(secondsStr);
        }
        catch (Exception e)
        {
            return "";
        }
        long yu = diff % 60; // 求秒余
        long shang = diff / 60; // 求秒商
        returnStr = String.valueOf(yu) + "秒";
        if (shang >= 60)
        {
            yu = shang % 60; // 求分余
            shang = shang % 60; // 求分商
            returnStr = String.valueOf(yu) + "分" + returnStr;
            if (shang >= 60)
            {
                yu = shang % 60; // 求小时余
                shang = shang % 60; // 求小时商
                returnStr = String.valueOf(yu) + "小时" + returnStr;
                if (shang >= 24)
                {
                    shang = shang % 24; // 求天商
                    if (shang != 0)
                    {
                        returnStr = String.valueOf(shang) + "天" + returnStr;
                    }
                }
            }
            else
            {
                if (shang != 0)
                {
                    returnStr = String.valueOf(shang) + "小时" + returnStr;
                }
            }
        }
        else
        {
            if (shang != 0)
            {
                returnStr = String.valueOf(shang) + "分" + returnStr;
            }
        }
        return returnStr;
    }

    /**
     * 获取给定日期的一天开始时间（00:00:00.000）
     * @return
     */
    public static Date getDayBegin(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取给定日期的一天结束时间（00:00:00.000）
     * @return
     */
    public static Date getDayEnd(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.DATE, 1);
        return calendar.getTime();
    }
}
