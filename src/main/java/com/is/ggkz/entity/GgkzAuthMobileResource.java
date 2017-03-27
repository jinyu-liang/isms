package com.is.ggkz.entity;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.base.mybatis.AbstractBaseEntity;

/**
 * 
 * @ClassName: GgkzAuthMobileResource
 * @Description: GgkzAuthMobileResource表的对应的java对象，该文件为工具自动生成，请勿手工修改!
 * @author 
 * @date 2013-02-27 14:19:32
 * 
 */
public class GgkzAuthMobileResource extends AbstractBaseEntity {

	private static final long serialVersionUID = 1L;

	/* 权限Id */
	public static final String ALIAS_AUTH_ID = "authId";

	/* 资源Id */
	public static final String ALIAS_RESOURCE_ID = "resourceId";

	/* 权限Id */
	private String authId;

	/* 资源Id */
	private String resourceId;

	public String getAuthId() {
		return this.authId;
	}

	public void setAuthId(String authId) {
		this.authId = authId;
	}

	public String getResourceId() {
		return this.resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	/**
	 * toString方法
	 */
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("authId", getAuthId())
				.append("resourceId", getResourceId())

				.toString();
	}
}