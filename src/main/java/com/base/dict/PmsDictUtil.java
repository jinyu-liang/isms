package com.base.dict;

import java.util.ArrayList;
import java.util.List;

import org.springside.modules.utils.spring.SpringContextHolder;

import com.is.sys.entity.SysDict;
import com.is.utils.StringUtils;

/**
 * 字典工具类
 * 
 * @author life
 * 
 */
public class PmsDictUtil
{

    /**
     * 根据字典类型和字典值，获取字典名称
     * 
     * @param dictType
     * @param dictCode
     * @return 查找不到返回null，参数值任意为空返回null
     */
    public static String getDictNameByTypeAndCode(String dictType, String dictCode)
    {
        if (StringUtils.isEmpty(dictType) || StringUtils.isEmpty(dictCode))
        {
            return null;
        }
        PmsDictInterfaceImpl pmsDict = (PmsDictInterfaceImpl) SpringContextHolder.getBean("pmsDictInterfaceImpl");
        return pmsDict.getDictNameByTypeAndCode(dictType, dictCode);
    }

    /**
     * 根据机构id和字典类型获取字典列表
     * 
     * @param orgId
     * @param dictType
     * @return
     */
    public static List<SysDict> getDictByType(String dictType)
    {
        List<SysDict> dictList = new ArrayList<SysDict>();
        if (StringUtils.isEmpty(dictType))
        {
            return dictList;
        }
        PmsDictInterfaceImpl pmsDict = (PmsDictInterfaceImpl) SpringContextHolder.getBean("pmsDictInterfaceImpl");
        return pmsDict.getDictByType(dictType);
    }
    
    /**
     * 根据机构id和字典类型获取字典列表
     * 
     * @param orgId
     * @param dictType
     * @return
     */
    public static List<SysDict> getDictByTypeanden(String dictType,String de)
    {
        List<SysDict> dictList = new ArrayList<SysDict>();
        if (StringUtils.isEmpty(dictType))
        {
            return dictList;
        }
        PmsDictInterfaceImpl pmsDict = (PmsDictInterfaceImpl) SpringContextHolder.getBean("pmsDictInterfaceImpl");
        return pmsDict.getDictByTypeanden(dictType,de);
    }
}
