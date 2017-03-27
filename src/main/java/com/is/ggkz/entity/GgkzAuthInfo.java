package com.is.ggkz.entity;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.base.mybatis.AbstractBaseEntity;

/**
 * 
 * @ClassName: GgkzAuthInfo
 * @Description: GgkzAuthInfo表的对应的java对象，该文件为工具自动生成，请勿手工修改!
 * @author 
 * @date 2013-02-27 14:19:22
 * 
 */
public class GgkzAuthInfo extends AbstractBaseEntity
{

    private static final long  serialVersionUID   = 1L;

    /* 权限Id */
    public static final String ALIAS_AUTH_ID      = "authId";

    /* 权限名称 */
    public static final String ALIAS_AUTH_NAME    = "authName";

    /* 简称 */
    public static final String ALIAS_SHORT_NAME   = "shortName";

    /* 备注 */
    public static final String ALIAS_NOTE         = "note";

    /* 操作时间 */
    public static final String ALIAS_OPER_TIME    = "operTime";

    /* 操作用户Id */
    public static final String ALIAS_OPER_USER_ID = "operUserId";

    /* 权限Id */
    private String             authId;

    /* 权限名称 */
    private String             authName;

    /* 简称 */
    private String             shortName;

    /* 备注 */
    private String             note;

    /* 操作时间 */
    private Date               operTime;

    /* 操作用户Id */
    private String             operUserId;

    //extend
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

    public String getShortName()
    {
        return this.shortName;
    }

    public void setShortName(String shortName)
    {
        this.shortName = shortName;
    }

    public String getNote()
    {
        return this.note;
    }

    public void setNote(String note)
    {
        this.note = note;
    }

    public Date getOperTime()
    {
        return this.operTime;
    }

    public void setOperTime(Date operTime)
    {
        this.operTime = operTime;
    }

    public String getOperUserId()
    {
        return this.operUserId;
    }

    public void setOperUserId(String operUserId)
    {
        this.operUserId = operUserId;
    }

    /* 关联对象 */
    private List<GgkzAuthResource> ggkzAuthResources;

    public List<GgkzAuthResource> getGgkzAuthResources()
    {
        return this.ggkzAuthResources;
    }

    public void setGgkzAuthResources(List<GgkzAuthResource> ggkzAuthResources)
    {
        this.ggkzAuthResources = ggkzAuthResources;
    }

    private List<GgkzRoleAuth> ggkzRoleAuths;

    public List<GgkzRoleAuth> getGgkzRoleAuths()
    {
        return this.ggkzRoleAuths;
    }

    public void setGgkzRoleAuths(List<GgkzRoleAuth> ggkzRoleAuths)
    {
        this.ggkzRoleAuths = ggkzRoleAuths;
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
        if (obj instanceof GgkzAuthInfo)
        {
            GgkzAuthInfo that = (GgkzAuthInfo) obj;
            if(that.getAuthId()==null||"".equals(that.getAuthId().trim()))
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
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("authId", getAuthId()).append("authName", getAuthName())
                .append("shortName", getShortName()).append("note", getNote()).append("operTime", getOperTime())
                .append("operUserId", getOperUserId()).toString();
    }
}