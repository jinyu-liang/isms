package com.is.ggkz.entity;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.base.mybatis.AbstractBaseEntity;

/**
 * 
 * @ClassName: GgkzAuthMobileInfo
 * @Description: GgkzAuthMobileInfo表的对应的java对象，该文件为工具自动生成，请勿手工修改!
 * @author 
 * @date 2013-02-27 14:19:22
 * 
 */
public class GgkzAuthMobileInfo extends AbstractBaseEntity
{

    private static final long  serialVersionUID = 1L;

    /* 权限Id */
    public static final String ALIAS_AUTH_ID    = "authId";

    /* 权限名称 */
    public static final String ALIAS_AUTH_NAME  = "authName";

    /* 权限Id */
    private String             authId;

    /* 权限名称 */
    private String             authName;

    // extend
    // 权限授权资源ID，用于展现与接收资源树选择的资源标识。
    private String             authResourceIds;

    public String getAuthResourceIds()
    {
        return authResourceIds;
    }

    public void setAuthResourceIds(String authResourceIds)
    {
        this.authResourceIds = authResourceIds;
    }

    public String getAuthId()
    {
        return this.authId;
    }

    public void setAuthId(String authId)
    {
        this.authId = authId;
    }

    public String getAuthName()
    {
        return this.authName;
    }

    public void setAuthName(String authName)
    {
        this.authName = authName;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (this.getAuthId() == null || "".equals(this.getAuthId().trim()))
        {
            return false;
        }
        if (obj instanceof GgkzAuthMobileInfo)
        {
            GgkzAuthMobileInfo that = (GgkzAuthMobileInfo) obj;
            if (that.getAuthId() == null || "".equals(that.getAuthId().trim()))
            {
                return false;
            }
            return authId.equals(that.getAuthId());
        }
        else
        {
            return false;
        }
    }

    /**
     * toString方法
     */
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("authId", getAuthId()).append("authName", getAuthName()).toString();
    }
}