package com.is.ggkz.entity;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.base.mybatis.AbstractBaseEntity;

/**
 * 
 * @ClassName: GgkzRoleMobileAuth
 * @Description: GgkzRoleMobileAuth表的对应的java对象，该文件为工具自动生成，请勿手工修改!
 * @author 
 * @date 2013-02-27 14:19:30
 * 
 */
public class GgkzRoleMobileAuth extends AbstractBaseEntity {

	private static final long serialVersionUID = 1L;

	/* 角色Id */
	public static final String ALIAS_ROLE_ID = "roleId";

	/* 权限Id */
	public static final String ALIAS_AUTH_ID = "authId";

	/* 角色Id */
	private String roleId;

	/* 权限Id */
	private String authId;

	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getAuthId() {
		return this.authId;
	}

	public void setAuthId(String authId) {
		this.authId = authId;
	}

	/**
	 * toString方法
	 */
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("roleId", getRoleId()).append("authId", getAuthId())
				.toString();
	}
}