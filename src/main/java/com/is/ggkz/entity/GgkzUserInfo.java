package com.is.ggkz.entity;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.base.mybatis.AbstractBaseEntity;

/**
 * 
 * @ClassName: GgkzUserInfo
 * @Description: GgkzUserInfo表的对应的java对象，该文件为工具自动生成，请勿手工修改!
 * @author 
 * @date 2013-02-27 14:18:50
 * 
 */
public class GgkzUserInfo extends AbstractBaseEntity
{

    private static final long  serialVersionUID         = 1L;

    /* 用户Id */
    public static final String ALIAS_USER_ID            = "userId";

    /* 机构ID */
    public static final String ALIAS_ORG_ID             = "orgId";

    /* 登录名 */
    public static final String ALIAS_LOGIN_NAME         = "loginName";

    /* 工作证号 */
    public static final String ALIAS_WORK_CODE          = "workCode";

    /* 姓名 */
    public static final String ALIAS_NAME               = "name";

    /* 密码 */
    public static final String ALIAS_PASSWORD           = "password";

    /* 性别 */
    public static final String ALIAS_SEX                = "sex";

    /* 用户状态 */
    public static final String ALIAS_USER_STATE         = "userState";

    /* 部门代码 */
    public static final String ALIAS_DEPART_ID          = "departId";

    /* 证件类型 */
    public static final String ALIAS_CERTIFICATE_TYPE   = "certificateType";

    /* 证件号码 */
    public static final String ALIAS_CERTIFICATE_CODE   = "certificateCode";

    /* 出生日期 */
    public static final String ALIAS_BIRTH_DATE         = "birthDate";

    /* 现居住地址 */
    public static final String ALIAS_NOW_RESIDE_ADDRESS = "nowResideAddress";

    /* email */
    public static final String ALIAS_EMAIL              = "email";

    /* 办公电话 */
    public static final String ALIAS_OFFICETEL          = "officeTel";

    /* 移动电话 */
    public static final String ALIAS_MOBILETEL          = "mobileTel";

    /* 移动电话 ID*/
    public static final String ALIAS_MOBILEID           = "mobileId";

    /* 移动电话 SN*/
    public static final String ALIAS_MOBILESN           = "mobileSn";

    /* 职位 SN*/
    public static final String ALIAS_POST               = "post";

    /* 所属区域部长id*/
    public static final String ALIAS_HEADUSERCD         = "headUserCd";

    /* 创建时间*/
    public static final String ALIAS_CREATETM           = "createTm";

    /* 更新时间*/
    public static final String ALIAS_UPDATETM           = "updateTm";

    /* 用户Id */
    private String             userId;

    /* 机构ID */
    private String             orgId;

    /* 登录名 */
    private String             loginName;

    /* 工作证号 */
    private String             workCode;

    /* 姓名 */
    private String             name;

    /* 密码 */
    private String             password;

    /* 性别 */
    private String             sex;

    /* 用户状态 */
    private String             userState;

    /* 部门代码 */
    private String             departId;

    /* 证件类型 */
    private String             certificateType;

    /* 证件号码 */
    private String             certificateCode;

    /* 出生日期 */
    private Date               birthDate;

    /* 现居住地址 */
    private String             nowResideAddress;

    /* 邮编 */
    private String             postcode;

    private String             newPassword;

    private String             email;

    private String             officeTel;

    private String             mobileTel;

    private String             mobileId;

    private String             mobileSn;

    private String             post;

    private String             headUserCd;

    private Date               createTm;

    private Date               updateTm;

    private boolean            checkJMSException        = false;

    public String getNewPassword()
    {
        return newPassword;
    }

    public void setNewPassword(String newPassword)
    {
        this.newPassword = newPassword;
    }

    public String getUserId()
    {
        return this.userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getOrgId()
    {
        return this.orgId;
    }

    public void setOrgId(String orgId)
    {
        this.orgId = orgId;
    }

    public String getLoginName()
    {
        return this.loginName;
    }

    public void setLoginName(String loginName)
    {
        this.loginName = loginName;
    }

    public String getWorkCode()
    {
        return this.workCode;
    }

    public void setWorkCode(String workCode)
    {
        this.workCode = workCode;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getPassword()
    {
        return this.password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getSex()
    {
        return this.sex;
    }

    public void setSex(String sex)
    {
        this.sex = sex;
    }

    public String getUserState()
    {
        return this.userState;
    }

    public void setUserState(String userState)
    {
        this.userState = userState;
    }

    public String getDepartId()
    {
        return this.departId;
    }

    public void setDepartId(String departId)
    {
        this.departId = departId;
    }

    public String getCertificateType()
    {
        return this.certificateType;
    }

    public void setCertificateType(String certificateType)
    {
        this.certificateType = certificateType;
    }

    public String getCertificateCode()
    {
        return this.certificateCode;
    }

    public void setCertificateCode(String certificateCode)
    {
        this.certificateCode = certificateCode;
    }

    public Date getBirthDate()
    {
        return this.birthDate;
    }

    public void setBirthDate(Date birthDate)
    {
        this.birthDate = birthDate;
    }

    public String getNowResideAddress()
    {
        return this.nowResideAddress;
    }

    public void setNowResideAddress(String nowResideAddress)
    {
        this.nowResideAddress = nowResideAddress;
    }

    public String getPostcode()
    {
        return this.postcode;
    }

    public void setPostcode(String postcode)
    {
        this.postcode = postcode;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getOfficeTel()
    {
        return officeTel;
    }

    public void setOfficeTel(String officeTel)
    {
        this.officeTel = officeTel;
    }

    public String getMobileTel()
    {
        return mobileTel;
    }

    public void setMobileTel(String mobileTel)
    {
        this.mobileTel = mobileTel;
    }

    public String getMobileId()
    {
        return mobileId;
    }

    public void setMobileId(String mobileId)
    {
        this.mobileId = mobileId;
    }

    public String getMobileSn()
    {
        return mobileSn;
    }

    public void setMobileSn(String mobileSn)
    {
        this.mobileSn = mobileSn;
    }

    public String getPost()
    {
        return post;
    }

    public void setPost(String post)
    {
        this.post = post;
    }

    public String getHeadUserCd()
    {
        return headUserCd;
    }

    public void setHeadUserCd(String headUserCd)
    {
        this.headUserCd = headUserCd;
    }

    public Date getCreateTm()
    {
        return createTm;
    }

    public void setCreateTm(Date createTm)
    {
        this.createTm = createTm;
    }

    public Date getUpdateTm()
    {
        return updateTm;
    }

    public void setUpdateTm(Date updateTm)
    {
        this.updateTm = updateTm;
    }

    public boolean isCheckJMSException()
    {
        return checkJMSException;
    }

    public void setCheckJMSException(boolean checkJMSException)
    {
        this.checkJMSException = checkJMSException;
    }

    /* 关联对象 */
    private List<GgkzUserRole> ggkzUserRoles;

    /** 关联角色对象 */
    private List<GgkzRoleInfo> ggzkRoleBeans;

    public List<GgkzRoleInfo> getGgzkRoleBeans()
    {
        return ggzkRoleBeans;
    }

    public void setGgzkRoleBeans(List<GgkzRoleInfo> ggzkRoleBeans)
    {
        this.ggzkRoleBeans = ggzkRoleBeans;
    }

    public List<GgkzUserRole> getGgkzUserRoles()
    {
        return this.ggkzUserRoles;
    }

    public void setGgkzUserRoles(List<GgkzUserRole> ggkzUserRoles)
    {
        this.ggkzUserRoles = ggkzUserRoles;
    }

    /**
     * toString方法
     */
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("userId", getUserId()).append("orgId", getOrgId())
                .append("loginName", getLoginName()).append("workCode", getWorkCode()).append("name", getName()).append("password", getPassword())
                .append("sex", getSex()).append("userState", getUserState()).append("departId", getDepartId())
                .append("certificateType", getCertificateType()).append("certificateCode", getCertificateCode()).append("birthDate", getBirthDate())
                .append("nowResideAddress", getNowResideAddress()).append("postcode", getPostcode()).append("post", getPost())
                .append("headUserCd", getHeadUserCd()).toString();
    }
}