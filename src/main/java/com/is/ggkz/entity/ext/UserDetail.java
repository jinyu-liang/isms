package com.is.ggkz.entity.ext;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import com.is.ggkz.entity.GgkzResourceInfo;
import com.is.utils.StringUtils;

/**
 * 
 * @ClassName: GgkzUserInfo
 * @Description: GgkzUserInfo表的对应的java对象，该文件为工具自动生成，请勿手工修改!
 * @author 
 * @date 2013-02-27 14:18:50
 * 
 */
public class UserDetail implements UserDetails, CredentialsContainer
{

    private static final long           serialVersionUID = 1L;

    /* 用户Id */
    private String                      userId;

    /* 机构ID */
    private String                      orgId;

    /* 登录名 */
    private String                      loginName;

    /* 工作证号 */
    private String                      workCode;

    /* 密码 */
    private String                      password;

    /* 性别 */
    private String                      sex;

    /* 用户状态 */
    private String                      userState;

    /* 部门代码 */
    private String                      departId;

    /* 职位代码 */
    private String                      post;

    /* 证件类型 */
    private String                      certificateType;

    /* 证件号码 */
    private String                      certificateCode;

    /* 出生日期 */
    private Date                        birthDate;

    /* 现居住地址 */
    private String                      nowResideAddress;

    /* 邮编 */
    private String                      postcode;

    /** 菜单资源 */
    private List<GgkzResourceInfo>      resource;

    /** 登陆时间 */
    private String                      loginTime;

    private final String                username;

    private final Set<GrantedAuthority> authorities;

    private final boolean               accountNonExpired;

    private final boolean               accountNonLocked;

    private final boolean               credentialsNonExpired;

    private final boolean               enabled;

    public String getUserId()
    {
        return this.userId;
    }

    public String getOrgId()
    {
        return this.orgId;
    }

    public String getLoginName()
    {
        return this.loginName;
    }

    public String getWorkCode()
    {
        return this.workCode;
    }

    public String getPassword()
    {
        return this.password;
    }

    public String getSex()
    {
        return this.sex;
    }

    public String getUserState()
    {
        return this.userState;
    }

    public String getDepartId()
    {
        return this.departId;
    }

    public String getCertificateType()
    {
        return this.certificateType;
    }

    public String getCertificateCode()
    {
        return this.certificateCode;
    }

    public Date getBirthDate()
    {
        return this.birthDate;
    }

    public String getNowResideAddress()
    {
        return this.nowResideAddress;
    }

    public String getPostcode()
    {
        return this.postcode;
    }

    public String getLoginTime()
    {
        return loginTime;
    }

    public Collection<GrantedAuthority> getAuthorities()
    {
        return authorities;
    }

    public boolean isAccountNonExpired()
    {
        return accountNonExpired;
    }

    public boolean isAccountNonLocked()
    {
        return accountNonLocked;
    }

    public boolean isCredentialsNonExpired()
    {
        return credentialsNonExpired;
    }

    public boolean isEnabled()
    {
        return enabled;
    }

    /**
     * 构造方法初始化
     * 
     * @param password
     * @param username
     * @param userId
     * @param loginName
     * @param loginIp
     * @param loginTime
     * @param authorities
     * @param accountNonExpired
     * @param accountNonLocked
     * @param credentialsNonExpired
     * @param enabled
     */
    public UserDetail(String password, String username, String userId, final String loginName, String post, String loginTime,
            List<GgkzResourceInfo> resource, final GrantedAuthority[] authorities, final boolean accountNonExpired, final boolean accountNonLocked,
            final boolean credentialsNonExpired, final boolean enabled,String departId)
    {
        if (StringUtils.isEmpty(password) || StringUtils.isEmpty(userId) || StringUtils.isEmpty(username) || StringUtils.isEmpty(loginName))
            throw new IllegalArgumentException("Cannot pass null or empty values to constructor");
        this.password = password;
        this.username = username;
        this.loginName = loginName;
        this.departId = departId;
        this.userId = userId;
        this.post = post;
        this.loginTime = loginTime;
        this.resource = resource;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.enabled = enabled;
        this.authorities = Collections.unmodifiableSet(sortAuthorities(authorities));
    }

    private static SortedSet<GrantedAuthority> sortAuthorities(GrantedAuthority[] authorities)
    {
        Assert.notNull(authorities, "Cannot pass a null GrantedAuthority collection");
        SortedSet<GrantedAuthority> sortedAuthorities = new TreeSet<GrantedAuthority>(new AuthorityComparator());

        for (GrantedAuthority grantedAuthority : authorities)
        {
            Assert.notNull(grantedAuthority, "GrantedAuthority list cannot contain any null elements");
            sortedAuthorities.add(grantedAuthority);
        }

        return sortedAuthorities;
    }

    private static class AuthorityComparator implements Comparator<GrantedAuthority>, Serializable
    {
        private static final long serialVersionUID = 1L;

        public int compare(GrantedAuthority g1, GrantedAuthority g2)
        {
            if (g2.getAuthority() == null)
            {
                return -1;
            }

            if (g1.getAuthority() == null)
            {
                return 1;
            }

            return g1.getAuthority().compareTo(g2.getAuthority());
        }
    }

    @Override
    public boolean equals(Object rhs)
    {
        if (rhs instanceof UserDetail)
            return username.equals(((UserDetail) rhs).username);
        return false;
    }

    @Override
    public int hashCode()
    {
        return username.hashCode();
    }

    @Override
    public void eraseCredentials()
    {
        password = null;
    }

    @Override
    public String getUsername()
    {
        return username;
    }

    public List<GgkzResourceInfo> getResource()
    {
        return resource;
    }

    public String getPost()
    {
        return post;
    }

}