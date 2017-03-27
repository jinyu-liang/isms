package com.is.ggkz.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.base.security.SpringSecurityUtils;
import com.is.ggkz.dao.GgkzAuthInfoDaoImpl;
import com.is.ggkz.dao.GgkzAuthResourceDaoImpl;
import com.is.ggkz.entity.GgkzAuthInfo;
import com.is.ggkz.entity.GgkzAuthResource;
import com.is.ggkz.entity.ext.UserDetail;

/**
 * 
 * @ClassName: GgkzAuthInfoServiceImpl
 * @Description: GgkzAuthInfo表对应的服务类
 * @author 
 * @date 2013-02-27 14:19:23 *
 */
// Spring Bean的标识.
@Component
// 默认将类中的所有函数纳入事务管理.
@Transactional
public class GgkzAuthInfoServiceImpl
{
    @SuppressWarnings("unused")
    private static Logger          logger = LoggerFactory.getLogger(GgkzAuthInfoServiceImpl.class);

    public GgkzAuthInfoDaoImpl     ggkzAuthInfoDaoImpl;

    // 权限分配资源Dao
    public GgkzAuthResourceDaoImpl ggkzAuthResourceDaoImpl;

    @Autowired
    public void setGgkzAuthResourceDaoImpl(GgkzAuthResourceDaoImpl ggkzAuthResourceDaoImpl)
    {
        this.ggkzAuthResourceDaoImpl = ggkzAuthResourceDaoImpl;
    }

    @Autowired
    public void setGgkzAuthInfoDaoImpl(GgkzAuthInfoDaoImpl ggkzAuthInfoDaoImpl)
    {
        this.ggkzAuthInfoDaoImpl = ggkzAuthInfoDaoImpl;
    }

    /**
     * 添加权限
     * 
     * @author 
     * @param auth
     */
    public int saveAuth(GgkzAuthInfo auth)
    {
        if (auth != null)
        {
            auth.setOperTime(new Date());
            auth.setAuthId(Math.random() * 1000 + "");// 暂时用这种方式替代
        }

        List<GgkzAuthResource> temp = assembleAuthResource(auth);
        int res = ggkzAuthInfoDaoImpl.insert(auth);
        if (temp != null)
        {
            ggkzAuthResourceDaoImpl.batchInsert(temp);
        }
        return res;
    }

    /**
     * 组装权限资源对象list
     * 
     * @param auth
     *            权限对象
     * @return 权限资源对象列表
     * @author 
     * 
     */
    private List<GgkzAuthResource> assembleAuthResource(GgkzAuthInfo auth)
    {

        if (auth != null && auth.getAuthResourceIds() != null && !auth.getAuthResourceIds().equals(""))
        {
            String[] resourceIds = null;
            resourceIds = auth.getAuthResourceIds().split(",");

            // 组装authResource对象列表
            List<GgkzAuthResource> temp = new ArrayList<GgkzAuthResource>();
            for (int i = 0; i < resourceIds.length; i++)
            {
                GgkzAuthResource authResource = new GgkzAuthResource();
                authResource.setAuthId(auth.getAuthId());
                authResource.setResourceId(resourceIds[i]);
                UserDetail sessionUser = (UserDetail) SpringSecurityUtils.getCurrentUser();
                if (sessionUser != null)
                {
                    authResource.setOperUserId(sessionUser.getUserId());// 从登录信息中取得登录用户。
                }
                authResource.setOperTime(new Date());
                temp.add(authResource);
            }
            if (temp.size() > 0)
            {
                return temp;
            }
            else
            {
                return null;
            }
        }
        else
        {
            return null;
        }
    }

    /**
     * 查询一个权限
     * 
     * @author 
     * @param auth
     * @return
     */
    public GgkzAuthInfo selectOneByEntity(GgkzAuthInfo auth)
    {
        return ggkzAuthInfoDaoImpl.selectOneByEntity(auth);
    }

    /**
     * 修改一个权限
     * 
     * @author 
     * @param auth
     */
    public int updateAuth(GgkzAuthInfo auth)
    {
        // 修改权限本身信息。
        int res = ggkzAuthInfoDaoImpl.updateByEntity(auth);
        List<GgkzAuthResource> temp = this.assembleAuthResource(auth);
        GgkzAuthResource resource = new GgkzAuthResource();
        // 删除原有资源
        batchDelete(resource);
        // 重新建立权限与资源关系。
        if (temp != null)
        {
            ggkzAuthResourceDaoImpl.batchInsert(temp);
        }
        return res;
    }

    /**
     * 查询权限列表
     * 
     * @return
     */
    public List<GgkzAuthInfo> selectAuthInfoList()
    {
        return ggkzAuthInfoDaoImpl.selectAll();
    }

    /**
     * 根据Id查询权限对象
     * 
     * @param authId
     * @return
     */
    public GgkzAuthInfo getAuthInfo(String authId)
    {
        GgkzAuthInfo info = new GgkzAuthInfo();
        info.setAuthId(authId);
        return selectOneByEntity(info);
    }

    /**
     * 根据权限Id批量删除权限资源
     * 
     * @author 
     * @param resources
     * @return
     */
    public int batchDelete(GgkzAuthResource resource)
    {
        return ggkzAuthResourceDaoImpl.batchDelete(resource);
    }
}
