package com.is.sys.properties;

import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

/**
 * 
 * <p>文件名称: SystemPropertiesUtil.java</p>
 * <p>文件描述: 系统资源文件</p>
 * <p>版权所有: 版权所有(C)2013-2020</p>
 * <p>公   司: IS软件事业部</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期：2014年11月17日</p>
 * <p>修改记录0：无</p>
 * @version 1.0
 * @author  
 */
public class SystemPropertiesUtil
{
    private static final Logger LOGGER = LoggerFactory.getLogger(SystemPropertiesUtil.class);

    private SystemPropertiesUtil()
    {

    }

    /**
     * 系统资源 
     * @return Properties
     */
    public static Properties getProperties()
    {
        Resource resource = new ClassPathResource("/application.properties");
        Properties props = null;
        try
        {
            props = PropertiesLoaderUtils.loadProperties(resource);
        }
        catch (IOException e)
        {
            if (LOGGER.isErrorEnabled())
            {
                LOGGER.error("getProperties异常：{}", e);
            }
        }
        return props;
    }

    /**
     * 获取String propertity
     * @param key
     * @param defaultval 默认值
     * @return
     */
    public static String getStringPropertie(String key, String defaultval)
    {
        return getProperties().getProperty(key, defaultval);
    }

    /**
     * 获取String propertity
     * @param key
     * @return 
     */
    public static String getStringPropertie(String key)
    {
        return getProperties().getProperty(key, "");
    }

    /**
     * 获取int propertity
     * @param key
     * @param defaultval 默认值
     * @return
     */
    public static int getIntPropertie(String key, int defaultval)
    {
        int rst = defaultval;
        String rststr = getProperties().getProperty(key);
        if (rststr != null)
        {
            try
            {
                rst = Integer.parseInt(rststr, 10);
            }
            catch (NumberFormatException e)
            {
                if (LOGGER.isErrorEnabled())
                {
                    LOGGER.error("getIntPropertie异常：{}", e);
                }
            }
        }
        return rst;
    }

    /**
     * 获取int propertity
     * @param key
     * @return 
     */
    public static int getIntPropertie(String key)
    {
        int rst = Integer.MIN_VALUE;
        String rststr = getProperties().getProperty(key);
        if (rststr != null)
        {
            try
            {
                rst = Integer.parseInt(rststr, 10);
            }
            catch (NumberFormatException e)
            {
                if (LOGGER.isErrorEnabled())
                {
                    LOGGER.error("getIntPropertie异常：{}", e);
                }
            }
        }
        return rst;
    }
}
