package com.is.utils.authUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springside.modules.utils.spring.SpringContextHolder;

import com.is.ggkz.dao.GgkzAuthMobileResourceDaoImpl;

/**
 * 终端权限处理类
 * 
 * @author zjm
 * 
 */
public class MobileAuth
{

    private MobileAuth()
    {
    }

    /**
     * 根据个人的角色，查找该角色所拥有的终端访问资源编号,无资源返回初始化map
     * 
     * @param roles 为空或者null返回空map
     * @return key：resourceId value：resourceId
     */
    public static Map<String, String> mobileAuthResourceByRoles(List<String> roles)
    {
        Map<String, String> authResourceMap = new HashMap<String, String>();
        GgkzAuthMobileResourceDaoImpl ggkzAuthMobileResourceDaoIml = (GgkzAuthMobileResourceDaoImpl) SpringContextHolder
                .getBean("ggkzAuthMobileResourceDaoImpl");
        List<String> authResource = ggkzAuthMobileResourceDaoIml.selectAuthResourceByRoles(roles);
        if (authResource == null)
        {
            return authResourceMap;
        }
        for (int i = 0; i < authResource.size(); i++)
        {

            if (authResource.get(i) != null)
            {
                String[] strss = authResource.get(i).split("[-]");
                authResourceMap.put(strss[0], strss[1]);
            }

        }
        return authResourceMap;
    }
}
