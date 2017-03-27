package com.is.ggkz.entity;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.base.mybatis.AbstractBaseEntity;

/**
 * 
 * @ClassName: GgkzRoleInfo
 * @Description: GgkzRoleInfo表的对应的java对象，该文件为工具自动生成，请勿手工修改!
 * @author 
 * @date 2013-02-27 14:19:00
 * 
 */
public class GgkzRoleInfo extends AbstractBaseEntity {

	private static final long serialVersionUID = 1L;

	/* 角色Id */
	public static final String ALIAS_ROLE_ID = "roleId";

	/* 角色名称 */
	public static final String ALIAS_ROLE_NAME = "roleName";

	/* 备注 */
	public static final String ALIAS_NOTE = "note";

	/* 操作时间 */
	public static final String ALIAS_OPER_TIME = "operTime";

	/* 操作用户Id */
	public static final String ALIAS_OPER_USER_ID = "operUserId";

	/* 角色Id */
	private String roleId;

	/* 角色名称 */
	private String roleName;

	/* 备注 */
	private String note;

	/* 操作时间 */
	private Date operTime;

	/* 操作用户Id */
	private String operUserId;
	
	/** 关联的权限对象 */
	private List<GgkzAuthInfo> ggkzAuthInfos;

	public List<GgkzAuthInfo> getGgkzAuthInfos() {
		return ggkzAuthInfos;
	}

	public void setGgkzAuthInfos(List<GgkzAuthInfo> ggkzAuthInfos) {
		this.ggkzAuthInfos = ggkzAuthInfos;
	}

	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Date getOperTime() {
		return this.operTime;
	}

	public void setOperTime(Date operTime) {
		this.operTime = operTime;
	}

	public String getOperUserId() {
		return this.operUserId;
	}

	public void setOperUserId(String operUserId) {
		this.operUserId = operUserId;
	}

	/* 关联对象 */
	private List<GgkzRoleAuth> ggkzRoleAuths;

	public List<GgkzRoleAuth> getGgkzRoleAuths() {
		return this.ggkzRoleAuths;
	}

	public void setGgkzRoleAuths(List<GgkzRoleAuth> ggkzRoleAuths) {
		this.ggkzRoleAuths = ggkzRoleAuths;
	}

	private List<GgkzUserRole> ggkzUserRoles;

	public List<GgkzUserRole> getGgkzUserRoles() {
		return this.ggkzUserRoles;
	}

	public void setGgkzUserRoles(List<GgkzUserRole> ggkzUserRoles) {
		this.ggkzUserRoles = ggkzUserRoles;
	}

	/**
	 * toString方法
	 */
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("roleId", getRoleId())
				.append("roleName", getRoleName()).append("note", getNote())
				.append("operTime", getOperTime())
				.append("operUserId", getOperUserId()).toString();
	}
}