package com.is.utils;

import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * <p>文件名称: ChineseFirstLetterUtil.java</p>
 * <p>文件描述: 汉字首字母</p>
 * <p>版权所有: 版权所有(C)2013-2020</p>
 * <p>公   司: IS软件事业部</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期：2014年10月30日</p>
 * <p>修改记录0：无</p>
 * @version 1.0
 * @author  
 */
public class ChineseFirstLetterUtil
{

    private static final Logger LOGGER = LoggerFactory.getLogger(ChineseFirstLetterUtil.class);

    private ChineseFirstLetterUtil()
    {
    }

    private final static int[]    li_SecPosValue = {1601, 1637, 1833, 2078, 2274, 2302, 2433, 2594, 2787, 3106, 3212, 3472, 3635, 3722, 3730, 3858,
            4027, 4086, 4390, 4558, 4684, 4925, 5249, 5590};

    private final static String[] lc_FirstLetter = {"a", "b", "c", "d", "e", "f", "g", "h", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
            "w", "x", "y", "z"                   };

    /** 
     * 取得给定汉字串的首字母串,即声母串 
     * @param str 给定汉字串 
     * @return 声母串 
     */
    public static String getAllFirstLetter(String str)
    {
        if (str == null || str.trim().length() == 0)
        {
            return "";
        }

        String _str = "";
        for (int i = 0; i < str.length(); i++)
        {
            _str = _str + getFirstLetter(str.substring(i, i + 1));
        }

        return _str;
    }

    /** 
     * 取得给定汉字的首字母,即声母 
     * @param chinese 给定的汉字 
     * @return 给定汉字的声母 
     */
    public static String getFirstLetter(String chinese)
    {
        if (chinese == null || chinese.trim().length() == 0)
        {
            return "";
        }
        String chineseIso = conversionStr(chinese, "GB2312", "ISO8859-1");

        if (chineseIso.length() > 1) // 判断是不是汉字  
        {
            int li_SectorCode = (int) chineseIso.charAt(0); // 汉字区码  
            int li_PositionCode = (int) chineseIso.charAt(1); // 汉字位码  
            li_SectorCode = li_SectorCode - 160;
            li_PositionCode = li_PositionCode - 160;
            int li_SecPosCode = li_SectorCode * 100 + li_PositionCode; // 汉字区位码  
            if (li_SecPosCode > 1600 && li_SecPosCode < 5590)
            {
                for (int i = 0; i < 23; i++)
                {
                    if (li_SecPosCode >= li_SecPosValue[i] && li_SecPosCode < li_SecPosValue[i + 1])
                    {
                        chinese = lc_FirstLetter[i];
                        break;
                    }
                }
            }
            else
            // 非汉字字符,如图形符号或ASCII码  
            {
                chinese = chinese.substring(0, 1);
            }
        }

        return chinese;
    }

    /** 
     * 字符串编码转换 
     * @param str 要转换编码的字符串 
     * @param charsetName 原来的编码 
     * @param toCharsetName 转换后的编码 
     * @return 经过编码转换后的字符串 
     */
    private static String conversionStr(String str, String charsetName, String toCharsetName)
    {
        try
        {
            str = new String(str.getBytes(charsetName), toCharsetName);
        }
        catch (UnsupportedEncodingException ex)
        {
        }
        return str;
    }

}
