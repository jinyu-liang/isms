package com.base.dict;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springside.modules.utils.spring.SpringContextHolder;

import com.base.mybatis.Page;
import com.googlecode.ehcache.annotations.Cacheable;
import com.is.ggkz.dao.GgkzDepartInfoDaoImpl;
import com.is.ggkz.dao.GgkzPostInfoDaoImpl;
import com.is.ggkz.entity.GgkzDepartInfo;
import com.is.ggkz.entity.GgkzPostInfo;
import com.is.ggkz.entity.GgkzUserInfo;
import com.is.ggkz.entity.query.GgkzUserInfoQuery;
import com.is.ggkz.service.GgkzUserInfoServiceImpl;
import com.is.pretrst.entity.ExWorkType;
import com.is.pretrst.entity.MWorkshop;
import com.is.pretrst.entity.query.ExWorkTypeQuery;
import com.is.pretrst.service.ExWorkTypeServiceImpl;
import com.is.pretrst.service.MWorkshopServiceImpl;
import com.is.sys.dao.SysDictDaoImpl;
import com.is.sys.entity.SysDict;
import com.is.utils.StringUtils;

/**
 * 字典工具类
 * 
 * @author life
 * 
 */
@Component
@Service
public class PmsDictInterfaceImpl
{

    /**
     * 根据字典类型和字典值，获取字典名称
     * 
     * @param dictType
     * @param dictCode
     * @return 查找不到返回null，参数值任意为空返回null
     */
    @Cacheable(cacheName = "DEFAULT_CACHE")
    public String getDictNameByTypeAndCode(String dictType, String dictCode)
    {
        if (StringUtils.isEmpty(dictType) || StringUtils.isEmpty(dictCode))
        {
            return null;
        }
        SysDictDaoImpl sysDict = (SysDictDaoImpl) SpringContextHolder.getBean("sysDictDaoImpl");
        SysDict dict = new SysDict();
        dict.setDictTypeCode(dictType);
        dict.setDictCode(dictCode);
        dict = sysDict.selectOneByEntity(dict);
        if (dict != null)
        {
            return dict.getDictName();
        }
        return "";
    }

    /**
     * 根据机构id和字典类型获取字典列表
     * 
     * @param orgId
     * @param dictType
     * @return
     */
    @SuppressWarnings("unchecked")
    @Cacheable(cacheName = "DEFAULT_CACHE")
    public List<SysDict> getDictByType(String dictType)
    {
        List<SysDict> dictList = new ArrayList<SysDict>();
        if (StringUtils.isEmpty(dictType))
        {
            return dictList;
        }
        if ("post".equals(dictType))
        {
            GgkzPostInfoDaoImpl ggkzPostInfoDaoImpl = (GgkzPostInfoDaoImpl) SpringContextHolder.getBean("ggkzPostInfoDaoImpl");
            List<GgkzPostInfo> rstList = ggkzPostInfoDaoImpl.selectAll();
            for (GgkzPostInfo item : rstList)
            {
                SysDict sysDict = new SysDict();
                sysDict.setDictTypeCode("post");
                sysDict.setDictCode(item.getPostId());
                sysDict.setDictName(item.getPostName());

                dictList.add(sysDict);
            }
        }
        else if ("depart".equals(dictType))
        {
            GgkzDepartInfoDaoImpl ggkzDepartInfoDaoImpl = (GgkzDepartInfoDaoImpl) SpringContextHolder.getBean("ggkzDepartInfoDaoImpl");
            List<GgkzDepartInfo> rstList = ggkzDepartInfoDaoImpl.selectAll();
            for (GgkzDepartInfo item : rstList)
            {
                SysDict sysDict = new SysDict();
                sysDict.setDictTypeCode("post");
                sysDict.setDictCode(item.getDepdns());
                sysDict.setDictName(item.getDepartName());

                dictList.add(sysDict);
            }
        }
        else if ("headUser".equals(dictType))
        {
            GgkzUserInfoQuery ggkzUserInfoQuery = new GgkzUserInfoQuery();
            ggkzUserInfoQuery.setUserState("Y");
            ggkzUserInfoQuery.setPostName("区域部长");

            GgkzUserInfoServiceImpl ggkzUserInfoServiceImpl = (GgkzUserInfoServiceImpl) SpringContextHolder.getBean("ggkzUserInfoServiceImpl");
            Page page = ggkzUserInfoServiceImpl.selectUserByPostFilterPage(ggkzUserInfoQuery);

            List<GgkzUserInfo> rstList = (List<GgkzUserInfo>) page.getData();
            for (GgkzUserInfo item : rstList)
            {
                SysDict sysDict = new SysDict();
                sysDict.setDictTypeCode("post");
                sysDict.setDictCode(item.getUserId());
                sysDict.setDictName(item.getName());

                dictList.add(sysDict);
            }
        }
        else if ("workshop".equals(dictType))
        {
//            MWorkshopServiceImpl mWorkshopServiceImpl = (MWorkshopServiceImpl) SpringContextHolder.getBean("MWorkshopServiceImpl");
            MWorkshopServiceImpl mWorkshopServiceImpl = (MWorkshopServiceImpl) SpringContextHolder.getBean(MWorkshopServiceImpl.class);
            List<MWorkshop> rstList = mWorkshopServiceImpl.selectAll();
            for (MWorkshop item : rstList)
            {
                SysDict sysDict = new SysDict();
                sysDict.setDictTypeCode("post");
                sysDict.setDictCode(item.getWsCd());
                sysDict.setDictName(item.getWsNm());

                dictList.add(sysDict);
            }
        }
      
        return dictList;
    }
    
    /**
     * 根据机构id和字典类型获取字典列表
     * 
     * @param orgId
     * @param dictType
     * @return
     */
    @SuppressWarnings("unchecked")
    @Cacheable(cacheName = "DEFAULT_CACHE")
    public List<SysDict> getDictByTypeanden(String dictType,String decid)
    {
        List<SysDict> dictList = new ArrayList<SysDict>();
        if (StringUtils.isEmpty(dictType))
        {
            return dictList;
        }
        if ("post".equals(dictType))
        {
            GgkzPostInfoDaoImpl ggkzPostInfoDaoImpl = (GgkzPostInfoDaoImpl) SpringContextHolder.getBean("ggkzPostInfoDaoImpl");
            List<GgkzPostInfo> rstList = ggkzPostInfoDaoImpl.selectAll();
            for (GgkzPostInfo item : rstList)
            {
                SysDict sysDict = new SysDict();
                sysDict.setDictTypeCode("post");
                sysDict.setDictCode(item.getPostId());
                sysDict.setDictName(item.getPostName());

                dictList.add(sysDict);
            }
        }
        else if ("depart".equals(dictType))
        {
            GgkzDepartInfoDaoImpl ggkzDepartInfoDaoImpl = (GgkzDepartInfoDaoImpl) SpringContextHolder.getBean("ggkzDepartInfoDaoImpl");
            GgkzDepartInfo  entity = new  GgkzDepartInfo();
            entity.setDepdns(decid);
            List<GgkzDepartInfo> rstList = ggkzDepartInfoDaoImpl.selectByEntity(entity);
            for (GgkzDepartInfo item : rstList)
            {
                SysDict sysDict = new SysDict();
                sysDict.setDictTypeCode("post");
                sysDict.setDictCode(item.getDepdns());
                sysDict.setDictName(item.getDepartName());

                dictList.add(sysDict);
            }
        }
        else if ("headUser".equals(dictType))
        {
            GgkzUserInfoQuery ggkzUserInfoQuery = new GgkzUserInfoQuery();
            ggkzUserInfoQuery.setUserState("Y");
            ggkzUserInfoQuery.setPostName(decid);

            GgkzUserInfoServiceImpl ggkzUserInfoServiceImpl = (GgkzUserInfoServiceImpl) SpringContextHolder.getBean("ggkzUserInfoServiceImpl");
            Page page = ggkzUserInfoServiceImpl.selectUserByPostFilterPage(ggkzUserInfoQuery);

            List<GgkzUserInfo> rstList = (List<GgkzUserInfo>) page.getData();
            for (GgkzUserInfo item : rstList)
            {
                SysDict sysDict = new SysDict();
                sysDict.setDictTypeCode("post");
                sysDict.setDictCode(item.getUserId());
                sysDict.setDictName(item.getName());

                dictList.add(sysDict);
            }
        }
        else if ("workshop".equals(dictType))
        {
//            MWorkshopServiceImpl mWorkshopServiceImpl = (MWorkshopServiceImpl) SpringContextHolder.getBean("MWorkshopServiceImpl");
            MWorkshopServiceImpl mWorkshopServiceImpl = (MWorkshopServiceImpl) SpringContextHolder.getBean(MWorkshopServiceImpl.class);
            List<MWorkshop> rstList = mWorkshopServiceImpl.selectAll();
            for (MWorkshop item : rstList)
            {
                SysDict sysDict = new SysDict();
                sysDict.setDictTypeCode("post");
                sysDict.setDictCode(item.getWsCd());
                sysDict.setDictName(item.getWsNm());

                dictList.add(sysDict);
            }
        }
        else if ("worktype".equals(dictType))
        {
//            MWorkshopServiceImpl mWorkshopServiceImpl = (MWorkshopServiceImpl) SpringContextHolder.getBean("MWorkshopServiceImpl");
        	ExWorkTypeServiceImpl mWorkshopServiceImpl = (ExWorkTypeServiceImpl) SpringContextHolder.getBean(ExWorkTypeServiceImpl.class);
        	ExWorkType  entity = new  ExWorkType();
            entity.setWork_ws_cd(decid);

            List<ExWorkType> rstList = (List<ExWorkType>) mWorkshopServiceImpl.selectEntity(entity);
            for (ExWorkType item : rstList)
            {
                SysDict sysDict = new SysDict();
                sysDict.setDictTypeCode("post");
                sysDict.setDictCode(item.getWorkCd());
                sysDict.setDictName(item.getWorkNm());

                dictList.add(sysDict);
            }
        }
        return dictList;
    }
}
