package com.is.ggkz.entity.query;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.is.ggkz.entity.GgkzUserInfo;

/**
 *
 * @ClassName: GgkzUserInfo
 * @Description: GgkzUserInfo表的对应的查询对象，增加Date/Double/int型字段的区间查询属性字段
 * @author 
 * @date 2013-02-27 14:18:50
 *
 */

public class GgkzUserInfoQuery extends GgkzUserInfo
{

    private static final long serialVersionUID = 1L;

    /**
     * Date字段：birthDate  的查询条件
     */

    /* 查询条件：BirthDate  的开始区间  */
    private Date              birthDateStart;

    private Date              createTmStart;

    private Date              createTmEnd;

    private Date              updateTmStart;

    private Date              updateTmEnd;

    private String            postName;

    private String            excludeUser;

    public Date getBirthDateStart()
    {
        return birthDateStart;
    }

    public void setBirthDateStart(Date birthDateStart)
    {
        this.birthDateStart = birthDateStart;
    }

    /*  查询条件：birthDate  的结束区间     */
    private Date birthDateEnd;

    public Date getBirthDateEnd()
    {
        return birthDateEnd;
    }

    public void setBirthDateEnd(Date birthDateEnd)
    {
        this.birthDateEnd = birthDateEnd;
    }

    public Date getCreateTmStart()
    {
        return createTmStart;
    }

    public void setCreateTmStart(Date createTmStart)
    {
        this.createTmStart = createTmStart;
    }

    public Date getCreateTmEnd()
    {
        return createTmEnd;
    }

    public void setCreateTmEnd(Date createTmEnd)
    {
        this.createTmEnd = createTmEnd;
    }

    public Date getUpdateTmStart()
    {
        return updateTmStart;
    }

    public void setUpdateTmStart(Date updateTmStart)
    {
        this.updateTmStart = updateTmStart;
    }

    public Date getUpdateTmEnd()
    {
        return updateTmEnd;
    }

    public void setUpdateTmEnd(Date updateTmEnd)
    {
        this.updateTmEnd = updateTmEnd;
    }

    public String getPostName()
    {
        return postName;
    }

    public void setPostName(String postName)
    {
        this.postName = postName;
    }

    public String getExcludeUser()
    {
        return excludeUser;
    }

    public void setExcludeUser(String excludeUser)
    {
        this.excludeUser = excludeUser;
    }

    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("userId", getUserId()).append("orgId", getOrgId())
                .append("loginName", getLoginName()).append("workCode", getWorkCode()).append("name", getName()).append("password", getPassword())
                .append("sex", getSex()).append("userState", getUserState()).append("departId", getDepartId())
                .append("certificateType", getCertificateType()).append("certificateCode", getCertificateCode()).append("birthDate", getBirthDate())
                .append("birthDateStart", getBirthDateStart()).append("birthDateEnd", getBirthDateEnd())
                .append("nowResideAddress", getNowResideAddress()).append("postcode", getPostcode()).append("post", getPost())
                .append("headUserCd", getHeadUserCd()).toString();
    }

}