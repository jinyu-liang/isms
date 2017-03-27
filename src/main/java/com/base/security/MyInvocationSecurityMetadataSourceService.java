package com.base.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.AntUrlPathMatcher;
import org.springframework.security.web.util.UrlMatcher;
import org.springframework.stereotype.Service;
import org.springside.modules.utils.spring.SpringContextHolder;

import com.is.pretrst.service.DProgressReportServiceImpl;

/** */
/**
 * 最核心的地方，就是提供某个资源对应的权限定义，即getAttributes方法返回的结果。 此类在初始化时，应该取到所有资源及其对应角色的定义。
 * 
 */
@Service
public class MyInvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource
{

    private UrlMatcher                                      urlMatcher  = new AntUrlPathMatcher();

    private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

    @Autowired
    private DProgressReportServiceImpl                      dprogressReportServiceImpl;

    public MyInvocationSecurityMetadataSourceService()
    {
        loadResourceDefine();
    }

    /**
     * 加载系统资源
     */
    @SuppressWarnings("unchecked")
    private void loadResourceDefine()
    {
        SqlSessionFactory sessionFactory = (SqlSessionFactory) SpringContextHolder.getBean("sqlSessionFactory");
        SqlSession session = sessionFactory.openSession();

        // 在Web服务器启动时，提取系统中的所有权限。
        List<String> authNameList = session.selectList("GgkzAuthInfoExt.selectAuthNameList");
        // 应当是资源为key， 权限为value。 资源通常为url， 权限就是那些以ROLE_为前缀的角色。
        // 一个资源可以由多个权限来访问。 
        resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
        if (authNameList != null && authNameList.size() > 0)
        {
            for (String auth : authNameList)
            {
                ConfigAttribute ca = new SecurityConfig(auth);
                List<String> resourceUrlList = session.selectList("GgkzResourceInfoExt.selectResourceNameList", auth);
                if (resourceUrlList != null && resourceUrlList.size() > 0)
                {
                    for (String res : resourceUrlList)
                    {
                        String url = res;

                        // 判断资源文件和权限的对应关系，如果已经存在相关的资源url，则要通过该url为key提取出权限集合，将权限增加到权限集合中
                        if (resourceMap.containsKey(url))
                        {

                            Collection<ConfigAttribute> value = resourceMap.get(url);
                            value.add(ca);
                            resourceMap.put(url, value);
                        }
                        else
                        {
                            Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
                            atts.add(ca);
                            resourceMap.put(url, atts);
                        }

                    }
                }
            }
        }
        if (session != null)
        {
            try
            {
                session.commit();
                session.close();
            }
            catch (Exception e)
            {
            }
        }
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes()
    {

        return null;
    }

    // 根据URL，找到相关的权限配置。
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException
    {

        // object 是一个URL，被用户请求的url。
        String url = ((FilterInvocation) object).getRequestUrl();
        // 截掉首位的"/"字符串
        url = url.substring(1);
        int firstQuestionMarkIndex = url.indexOf("?");

        if (firstQuestionMarkIndex != -1)
        {
            url = url.substring(0, firstQuestionMarkIndex);
        }

        Iterator<String> ite = resourceMap.keySet().iterator();

        while (ite.hasNext())
        {
            String resURL = ite.next();

            if (urlMatcher.pathMatchesUrl(url, resURL))
            {

                return resourceMap.get(resURL);
            }
        }

        return null;
    }

    @Override
    public boolean supports(Class<?> arg0)
    {

        return true;
    }

}